package TestRailAPI;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Updates TestRail with test results for a test run.
 */
public class TestRailResultUpdater {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRailResultUpdater.class);
    private final APIClient client;
    private final TestRailTestCaseAPI testCaseAPI;
    private final List<HashMap<String, Object>> testResults;

    public TestRailResultUpdater() throws IOException {
        Properties config = loadConfig();
        String url = config.getProperty("testrail.url");
        String username = config.getProperty("testrail.username");
        String apiKey = config.getProperty("testrail.apiKey");
        this.client = new APIClient(url);
        this.client.setUser(username);
        this.client.setPassword(apiKey);
        this.testCaseAPI = TestRailTestCaseAPI.getInstance();
        this.testResults = new ArrayList<>();
        LOGGER.info("Initialized TestRailResultUpdater");
    }

    public int createTestRun() {
        try {
            Properties config = loadConfig();
            int projectId = Integer.parseInt(config.getProperty("testrail.projectId"));
            int suiteId = Integer.parseInt(config.getProperty("testrail.suiteId"));
            String runName = "Test Run " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            HashMap<String, Object> runData = new HashMap<>();
            runData.put("suite_id", suiteId);
            runData.put("name", runName);
            runData.put("project_id", projectId);
            HashMap<String, Object> response = (HashMap<String, Object>) client.sendPost("add_run/" + projectId, runData);
            int runId = (Integer) response.get("id");
            LOGGER.info("Created TestRail test run: {} (ID: {})", runName, runId);
            return runId;
        } catch (IOException | APIException e) {
            LOGGER.error("Failed to create TestRail test run: {}", e.getMessage(), e);
            throw new RuntimeException("TestRail test run creation failed", e);
        }
    }

    public void addTestResult(Scenario scenario) {
        String featureName = getShortFeatureName(scenario.getUri().toString());
        String scenarioName = getScenarioName(scenario);
        String testCaseId = testCaseAPI.getTestCaseIdForScenario(featureName, scenarioName);

        int caseId = parseCaseIdFromTestCaseId(testCaseId);
        if (caseId == -1) {
            LOGGER.warn("Skipping TestRail result update for unmapped scenario: {}", scenario.getName());
            return;
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("case_id", caseId);
        result.put("status_id", scenario.isFailed() ? 5 : 1); // 5 = Failed, 1 = Passed
        result.put("comment", scenario.isFailed() ? "Failed: " + scenario.getName() : "Passed: " + scenario.getName());
        testResults.add(result);
        LOGGER.info("Added result for TestRail case ID {}: {}", caseId, result.get("status_id").equals(1) ? "Passed" : "Failed");
    }

    public void sendTestResults(int runId) {
        if (testResults.isEmpty()) {
            LOGGER.warn("No test results to send to TestRail");
            return;
        }

        try {
            HashMap<String, Object> data = new HashMap<>();
            data.put("results", testResults);
            client.sendPost("add_results_for_cases/" + runId, data);
            LOGGER.info("Successfully sent {} test results to TestRail run ID {}", testResults.size(), runId);
            testResults.clear();
        } catch (IOException | APIException e) {
            LOGGER.error("Failed to send test results to TestRail for run ID {}: {}", runId, e.getMessage(), e);
            throw new RuntimeException("TestRail result update failed", e);
        }
    }

    private Properties loadConfig() throws IOException {
        Properties config = new Properties();
        String configPath = "src/main/config/config.properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            config.load(fis);
            LOGGER.info("Loaded configuration from: {}", configPath);
        } catch (IOException e) {
            LOGGER.error("Failed to load config.properties from {}: {}", configPath, e.getMessage(), e);
            throw e;
        }
        return config;
    }

    private String getShortFeatureName(String uri) {
        String fileName = uri.substring(uri.lastIndexOf("/") + 1);
        return fileName.replace(".feature", "").toLowerCase();
    }

    private String getScenarioName(Scenario scenario) {
        return "S" + scenario.getLine();
    }

    private int parseCaseIdFromTestCaseId(String testCaseId) {
        String featureScenario = testCaseAPI.getTestCaseMapping().get(testCaseId);
        if (featureScenario == null) {
            LOGGER.error("No mapping found for test case ID: {}", testCaseId);
            return -1;
        }
        String caseIdStr = featureScenario.substring(featureScenario.lastIndexOf("S") + 1);
        try {
            return Integer.parseInt(caseIdStr);
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid case ID format in featureScenario: {}", featureScenario);
            return -1;
        }
    }
}