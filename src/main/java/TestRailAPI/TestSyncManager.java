package TestRailAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.stream.Collectors;

/**
 * Synchronizes test cases from TestRail and generates feature files.
 */
public class TestSyncManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestSyncManager.class);
    private static final String FEATURE_PATH = "src/test/java/resources/";
    private static String testRailUrl;
    private static String username;
    private static String apiKey;
    private static String projectId;
    private static String suiteId;

    @BeforeSuite
    public void syncTestCases() throws IOException {
        LOGGER.info("Starting TestRail test case synchronization");
        try {
            Properties config = loadConfig();
            testRailUrl = config.getProperty("testrail.url");
            username = config.getProperty("testrail.username");
            apiKey = config.getProperty("testrail.apiKey");
            projectId = config.getProperty("testrail.projectId");
            suiteId = config.getProperty("testrail.suiteId");

            if (testRailUrl == null || username == null || apiKey == null || projectId == null || suiteId == null) {
                LOGGER.error("Missing configuration: url={}, user={}, apiKey={}, projectId={}, suiteId={}",
                        testRailUrl, username, apiKey, projectId, suiteId);
                throw new IllegalStateException("TestRail configuration is incomplete");
            }

            Files.createDirectories(Paths.get(FEATURE_PATH));
            List<TestCase> testCases = fetchTestCases();
            LOGGER.info("Downloaded {} test cases", testCases.size());

            for (TestCase testCase : testCases) {
                if (testCase.testrailBddScenario != null && !testCase.testrailBddScenario.isEmpty()) {
                    generateFeatureFile(testCase);
                    String webTcId = "WebTC" + testCase.id;
                    String featureScenario = "testcase_" + testCase.id + "/S" + testCase.id;
                    TestRailTestCaseAPI.addTestCaseMapping(webTcId, featureScenario);
                } else {
                    LOGGER.warn("Skipping TestCase {} ({}): Missing or empty custom_testrail_bdd_scenario",
                            testCase.id, testCase.title);
                }
            }
            LOGGER.info("Generated feature files");
        } catch (Exception e) {
            LOGGER.error("Test case synchronization failed: {}", e.getMessage(), e);
            throw e;
        }
    }

    @AfterSuite
    public void cleanUpFeatureFiles() throws IOException {
        LOGGER.info("Cleaning up feature files in {}", FEATURE_PATH);
        Files.walk(Paths.get(FEATURE_PATH))
                .filter(path -> path.toString().endsWith(".feature"))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        LOGGER.error("Failed to delete feature file {}: {}", path, e.getMessage());
                    }
                });
        LOGGER.info("Feature file cleanup completed");
    }

    private List<TestCase> fetchTestCases() throws IOException {
        String endpoint = testRailUrl + "/index.php?/api/v2/get_cases/" + projectId + "&suite_id=" + suiteId;
        String auth = Base64.getEncoder().encodeToString((username + ":" + apiKey).getBytes());

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Basic " + auth);
        conn.setRequestProperty("Content-Type", "application/json");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            String errorResponse = errorReader.lines().collect(Collectors.joining());
            LOGGER.error("TestRail API call failed with code {}: {}", responseCode, errorResponse);
            throw new IOException("TestRail API call failed: " + responseCode + " - " + errorResponse);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = reader.lines().collect(Collectors.joining());

        List<TestCase> testCases = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode cases = root.get("cases");
            if (cases == null || !cases.isArray()) {
                LOGGER.error("No test cases found in response");
                return testCases;
            }
            for (JsonNode caseNode : cases) {
                TestCase testCase = new TestCase();
                testCase.id = caseNode.get("id").asInt();
                testCase.title = caseNode.get("title").asText();
                testCase.suiteId = caseNode.get("suite_id").asInt();
                if (caseNode.has("custom_testrail_bdd_scenario")) {
                    JsonNode bddArray = mapper.readTree(caseNode.get("custom_testrail_bdd_scenario").asText());
                    if (bddArray.isArray() && bddArray.size() > 0 && bddArray.get(0).has("content")) {
                        testCase.testrailBddScenario = bddArray.get(0).get("content").asText();
                    }
                }
                testCases.add(testCase);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to parse JSON response: {}", e.getMessage(), e);
            throw new IOException("JSON parsing failed", e);
        }

        return testCases;
    }

    private void generateFeatureFile(TestCase testCase) throws IOException {
        int testCaseId = testCase.id;
        String gherkinContent = testCase.testrailBddScenario;
        if (gherkinContent == null || gherkinContent.trim().isEmpty()) {
            LOGGER.error("Invalid Gherkin content for TestCase {}: {}", testCaseId, testCase.title);
            throw new IOException("Invalid Gherkin content for TestCase " + testCaseId);
        }
        StringBuilder featureContent = new StringBuilder();
        featureContent.append("@TestRailID-").append(testCaseId).append("\n");
        featureContent.append(gherkinContent);
        String fileName = FEATURE_PATH + "TestCase_" + testCaseId + ".feature";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(featureContent.toString());
            LOGGER.info("Generated feature file: {}", fileName);
        } catch (IOException e) {
            LOGGER.error("Failed to generate feature file for TestCase {}: {}", testCaseId, e.getMessage());
            throw e;
        }
    }

    private Properties loadConfig() throws IOException {
        Properties config = new Properties();
        String configPath = "src/config/config.properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            config.load(fis);
        } catch (IOException e) {
            LOGGER.error("Failed to load config.properties from {}: {}", configPath, e.getMessage());
            throw e;
        }
        return config;
    }

    private static class TestCase {
        int id;
        String title;
        int suiteId;
        String testrailBddScenario;
    }
}