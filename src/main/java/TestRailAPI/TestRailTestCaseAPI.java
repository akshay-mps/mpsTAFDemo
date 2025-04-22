package TestRailAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Manages TestRail test case mappings for Cucumber scenarios.
 */
public class TestRailTestCaseAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRailTestCaseAPI.class);
    private static final String FEATURE_PATH = "src/test/java/resources/";
    private static final Map<String, String> TEST_CASE_MAPPING = new HashMap<>();
    private static TestRailTestCaseAPI instance;
    private static boolean testCasesMapped = false;

    private TestRailTestCaseAPI() {
        LOGGER.info("Initialized TestRailTestCaseAPI");
    }

    public static synchronized TestRailTestCaseAPI getInstance() {
        if (instance == null) {
            instance = new TestRailTestCaseAPI();
            instance.loadTestCaseMapping();
        }
        return instance;
    }

    /**
     * Loads test case mappings from generated feature files.
     */
    private void loadTestCaseMapping() {
        if (!testCasesMapped) {
            try (Stream<Path> paths = Files.walk(Paths.get(FEATURE_PATH))) {
                paths.filter(path -> path.toString().endsWith(".feature"))
                        .forEach(path -> {
                            String fileName = path.getFileName().toString();
                            if (fileName.startsWith("TestCase_")) {
                                try {
                                    String testCaseId = fileName.replace("TestCase_", "").replace(".feature", "");
                                    String webTcId = "WebTC" + testCaseId;
                                    String featureScenario = "testcase_" + testCaseId + "/S" + testCaseId;
                                    TEST_CASE_MAPPING.put(webTcId, featureScenario);
                                } catch (Exception e) {
                                    LOGGER.error("Failed to process feature file {}: {}", fileName, e.getMessage());
                                }
                            }
                        });
                testCasesMapped = true;
                LOGGER.info("Loaded {} test case mappings", TEST_CASE_MAPPING.size());
            } catch (IOException e) {
                LOGGER.error("Failed to load test case mappings: {}", e.getMessage(), e);
                throw new RuntimeException("Test case mapping failed", e);
            }
        }
    }

    public String getTestCaseIdForScenario(String featureName, String scenarioName) {
        String featureScenario = featureName + "/" + scenarioName;
        String result = TEST_CASE_MAPPING.entrySet().stream()
                .filter(entry -> entry.getValue().equals(featureScenario))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("UnknownTC");
        if ("UnknownTC".equals(result)) {
            LOGGER.warn("No mapping found for featureScenario: {}", featureScenario);
        }
        return result;
    }

    public Map<String, String> getTestCaseMapping() {
        return new HashMap<>(TEST_CASE_MAPPING);
    }

    public static void addTestCaseMapping(String webTcId, String featureScenario) {
        TEST_CASE_MAPPING.put(webTcId, featureScenario);
    }
}