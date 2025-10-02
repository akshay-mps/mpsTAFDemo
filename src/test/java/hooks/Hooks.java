package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import base.BaseTest;
import TestRailAPI.TestRailResultUpdater;
import TestRailAPI.TestRailTestCaseAPI;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import utility.DriverManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Resources.Property.SAUCELABS_ENABLED;
import static Resources.Property.TEST_RAIL_FLAG;

public class Hooks {

    private BaseTest baseTest;
    private static TestRailResultUpdater resultUpdater;
    private static TestRailTestCaseAPI testRailAPI;
    private static int testRunId;

    // -------------------- BEFORE SUITE --------------------
    @Before(order = 0)
    public void setupSuite() {
        baseTest = new BaseTest();
        if (System.getProperty("SAUCE_BUILD_NAME") == null) {
            String buildName = "Automation Build" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            System.setProperty("SAUCELABS_BUILD_NAME", buildName);
            System.out.println("=== SauceLabs Build Created: " + buildName + " ===");
        }

        if (Boolean.parseBoolean(TEST_RAIL_FLAG.toString())) {
            if (resultUpdater == null) {
                resultUpdater = new TestRailResultUpdater();
                testRailAPI = TestRailTestCaseAPI.getInstance();

                String runName = "Automated Run - " +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

                testRunId = resultUpdater.createTestRun(runName);
                System.out.println("=== TestRail Run Created: " + testRunId + " ===");
            }
        }
    }


    // -------------------- BEFORE SCENARIO --------------------
    @Before(order = 1)
    public void beforeScenario(Scenario scenario) throws IOException {
        if (baseTest == null) baseTest = new BaseTest();

        if (DriverManager.getDriver() == null) {

            // Include example values for Scenario Outline iterations
            if (scenario.getSourceTagNames() != null && !scenario.getSourceTagNames().isEmpty()) {
            }
            WebDriver driver = baseTest.setup();  // initializes WebDriver from BaseTest
            DriverManager.setDriver(driver);
        }
    }

    // -------------------- AFTER SCENARIO --------------------
    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        String screenshotPath = null;



        try {
            // Capture screenshot if scenario failed
            if (driver != null) {
                screenshotPath = captureScreenshot(scenario, driver);

                // Update Sauce Labs job status if running on Sauce
                if ("yes".equalsIgnoreCase(SAUCELABS_ENABLED.toString()) && driver instanceof JavascriptExecutor) {
                    String result = scenario.isFailed() ? "failed" : "passed";
                    ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + result);
                    System.out.println("=== Sauce Labs job marked as " + result + " ===");
                }
            }

            // Update TestRail
            if (Boolean.parseBoolean(TEST_RAIL_FLAG.toString()) && resultUpdater != null && testRailAPI != null) {
                String featureName = getShortFeatureName(scenario.getUri().toString());
                String scenarioName = scenario.getName();
                String testCaseId = testRailAPI.getTestCaseIdForScenario(featureName, scenarioName);

                if (!"UnknownTC".equals(testCaseId)) {
                    resultUpdater.addTestResult(testRunId, testCaseId, scenario.isFailed() ? "failed" : "passed");

                    if (screenshotPath != null) {
                        resultUpdater.addAttachmentToResult(testRunId, testCaseId, screenshotPath);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up WebDriver
            if (driver != null) {
                try { driver.quit(); } catch (Exception e) { e.printStackTrace(); }
            }
            DriverManager.removeDriver();
        }

        System.out.println("=== After Scenario completed: " + scenario.getName() + " ===");
    }

    // -------------------- AFTER SUITE --------------------
    @After(order = 0)
    public void tearDownSuite() {
        if (Boolean.parseBoolean(TEST_RAIL_FLAG.toString()) && resultUpdater != null) {
            resultUpdater.sendTestResults(testRunId);
            System.out.println("=== TestRail results sent for run: " + testRunId + " ===");
        }
        System.out.println("=== After Suite: All scenarios completed ===");
    }

    // -------------------- HELPER METHODS --------------------
    private String captureScreenshot(Scenario scenario, WebDriver driver) {
        try {
            Screenshot screenshot = new AShot().takeScreenshot(driver);
            BufferedImage image = screenshot.getImage();

            // Attach to Allure
            ByteArrayInputStream bis;
            try (var baos = new java.io.ByteArrayOutputStream()) {
                ImageIO.write(image, "png", baos);
                bis = new ByteArrayInputStream(baos.toByteArray());
            }
            Allure.addAttachment(scenario.getName() + "_screenshot", "image/png", bis, ".png");

            // Save locally
            String featureName = getShortFeatureName(scenario.getUri().toString());
            String scenarioName = scenario.getName().replaceAll("\\s+", "_");
            String testCaseId = testRailAPI.getTestCaseIdForScenario(featureName, scenarioName);
            String screenshotDir = "target/screenshots/" + testCaseId + "/" + scenarioName;
            FileUtils.forceMkdir(new File(screenshotDir));
            String screenshotPath = screenshotDir + "/screenshot.png";
            ImageIO.write(image, "png", new File(screenshotPath));

            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getShortFeatureName(String uri) {
        return uri.substring(uri.lastIndexOf("/") + 1)
                .replace(".feature", "")
                .toLowerCase();
    }
}
