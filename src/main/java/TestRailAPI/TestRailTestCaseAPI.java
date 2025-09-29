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

public class TestRailTestCaseAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRailTestCaseAPI.class);
    private static final String FEATURE_PATH = "src/test/java/resources/";
    private static final Map<String, String> TEST_CASE_MAPPING = new HashMap<>();
    private static volatile TestRailTestCaseAPI instance;

    private TestRailTestCaseAPI() {
        LOGGER.info("Initialized TestRailTestCaseAPI");
        loadTestCaseMapping();
    }

    public static TestRailTestCaseAPI getInstance() {
        if (instance == null) {
            synchronized (TestRailTestCaseAPI.class) {
                if (instance == null) {
                    instance = new TestRailTestCaseAPI();
                }
            }
        }
        return instance;
    }

    private void loadTestCaseMapping() {
        try (Stream<Path> paths = Files.walk(Paths.get(FEATURE_PATH))) {
            paths.filter(path -> path.toString().endsWith(".feature"))
                    .forEach(this::mapFeatureFile);
            LOGGER.info("Loaded {} dynamic test case mappings", TEST_CASE_MAPPING.size());
        } catch (IOException e) {
            LOGGER.error("Failed to load test case mappings: {}", e.getMessage(), e);
            throw new RuntimeException("Test case mapping failed", e);
        }
    }

    private void mapFeatureFile(Path featureFile) {
        String fileName = featureFile.getFileName().toString();
        if (!fileName.startsWith("TestCase_")) return;

        try {
            String testCaseId = fileName.replace("TestCase_", "").replace(".feature", "");
            String webTcId = "C" + testCaseId;
            String featureName = fileName.replace(".feature", "").toLowerCase();

            Files.lines(featureFile)
                    .filter(line -> line.trim().startsWith("Scenario"))
                    .forEach(line -> {
                        String scenarioName = line.replaceFirst("Scenario:\\s*", "")
                                .trim()
                                .replaceAll("\\s+", "_")
                                .replaceAll("[^a-zA-Z0-9_]", "") // remove special chars
                                .toLowerCase();
                        String key = featureName + ":" + scenarioName;
                        TEST_CASE_MAPPING.put(webTcId, key);
                        LOGGER.info("Mapped TestRail {} -> {}", webTcId, key);
                    });
        } catch (IOException e) {
            LOGGER.error("Error reading feature file {}: {}", fileName, e.getMessage());
        }
    }

    public String getTestCaseIdForScenario(String featureName, String scenarioName) {
        // Normalize scenario name the same way as mapping
        String normalizedScenario = scenarioName.trim()
                .replaceAll("\\s+", "_")
                .replaceAll("[^a-zA-Z0-9_]", "")
                .toLowerCase();
        String key = featureName.toLowerCase() + ":" + normalizedScenario;

        String result = TEST_CASE_MAPPING.entrySet().stream()
                .filter(entry -> entry.getValue().equals(key))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("UnknownTC");

        if ("UnknownTC".equals(result)) {
            LOGGER.warn("No TestRail mapping found for featureScenario: {}", key);
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
