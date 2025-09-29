package TestRailAPI;

import static Resources.Property.*;
import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Sends results dynamically to TestRail for all scenarios.
 */
public class TestRailResultUpdater {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRailResultUpdater.class);
    private final APIClient client;
    private final TestRailTestCaseAPI testCaseAPI;
    private final List<HashMap<String, Object>> testResults;
    private static final int ASSIGNED_USER_ID = Integer.parseInt(TESTRAIL_USERID.toString());

    public TestRailResultUpdater() {
        client = new APIClient(TESTRAIL_URL.toString());
        client.setUser(TESTRAIL_USERNAME.toString());
        client.setPassword(TESTRAIL_PASSWORD.toString());
        testCaseAPI = TestRailTestCaseAPI.getInstance();
        testResults = new ArrayList<>();
        LOGGER.info("Initialized TestRailResultUpdater");
    }

    public int createTestRun(String runName) {
        try {
            int projectId = Integer.parseInt(TESTRAIL_PROJECTID.toString());
            int suiteId = Integer.parseInt(TESTRAIL_SUITEID.toString());

            HashMap<String, Object> runData = new HashMap<>();
            runData.put("suite_id", suiteId);
            runData.put("name", runName);
            runData.put("project_id", projectId);
            runData.put("assignedto_id", ASSIGNED_USER_ID);
            runData.put("description", "Automated run by " + TESTRAIL_USERNAME);

            HashMap<String, Object> response = (HashMap<String, Object>) client.sendPost("add_run/" + projectId, runData);

            System.out.println("TestRail API response for createTestRun: " + response);

            Object idObj = response.get("id");
            if (idObj == null) {
                throw new RuntimeException("No run ID returned from TestRail API. Check project/suite IDs and permissions.");
            }

            int runId = idObj instanceof Long ? ((Long) idObj).intValue() : (Integer) idObj;
            LOGGER.info("Created TestRail test run: {} (ID: {})", runName, runId);

            return runId;

        } catch (IOException | APIException e) {
            LOGGER.error("Failed to create TestRail test run", e);
            throw new RuntimeException(e);
        }
    }

    public void addTestResult(int runId, String testCaseId, String status) {
        int statusId = "passed".equalsIgnoreCase(status) ? 1 : 5;
        HashMap<String, Object> result = new HashMap<>();
        result.put("case_id", parseCaseId(testCaseId));
        result.put("status_id", statusId);
        result.put("comment", "Automated result: " + status);
        testResults.add(result);
        LOGGER.info("Added TestRail result to local list: {} -> {}", testCaseId, status);
    }

    public void addAttachmentToResult(int runId, String testCaseId, String filePath) {
        if ("UnknownTC".equals(testCaseId)) return;
        try {
            HashMap<String, Object> data = new HashMap<>();
            data.put("attachment", new File(filePath));
            Object response = client.sendPost("add_attachment_to_result/" + runId + "/" + parseCaseId(testCaseId), data);
            LOGGER.info("Added screenshot attachment for {}. API response: {}", testCaseId, response);
        } catch (IOException | APIException e) {
            LOGGER.error("Failed to add attachment to TestRail for {}", testCaseId, e);
        }
    }

    public void sendTestResults(int runId) {
        if (testResults.isEmpty()) {
            System.out.println("No test results to send to TestRail. testResults list is empty.");
            return;
        }

        System.out.println("Sending " + testResults.size() + " result(s) to TestRail run " + runId);
        System.out.println("Payload: " + testResults);

        try {
            HashMap<String, Object> data = new HashMap<>();
            data.put("results", new ArrayList<>(testResults));
            Object response = client.sendPost("add_results_for_cases/" + runId, data);
            System.out.println("TestRail API response for add_results_for_cases: " + response);

            testResults.clear();
            LOGGER.info("Sent results for run {}", runId);
        } catch (IOException | APIException e) {
            LOGGER.error("Failed to send TestRail results", e);
        }
    }

    private int parseCaseId(String testCaseId) {
        return Integer.parseInt(testCaseId.replaceAll("\\D", ""));
    }
}
