package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import base.BaseTest;
import TestRailAPI.TestRailResultUpdater;
import TestRailAPI.TestRailTestCaseAPI;

import java.io.File;
import java.io.IOException;

public class Hooks {
    private final BaseTest baseTest = new BaseTest();
    private TestRailTestCaseAPI testRailAPI;
    private TestRailResultUpdater resultUpdater;
    private int testRunId;

    @BeforeSuite //very first testrail
    public void setupSuite() {
        try {
            testRailAPI = TestRailTestCaseAPI.getInstance();
            resultUpdater = new TestRailResultUpdater();
            testRunId = resultUpdater.createTestRun();
        } catch (IOException e) {
            resultUpdater = null;
        } catch (Exception e) {
            resultUpdater = null;
        }
    }

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) throws IOException {
        WebDriver driver = baseTest.setup();
        if (driver == null) {
            throw new IllegalStateException("BaseTest.setup() returned null WebDriver");
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                captureScreenshot(scenario);
            }
            if (resultUpdater != null) {
                resultUpdater.addTestResult(scenario);
            }
        } catch (Exception e) {
            // Silently handle errors to avoid disrupting teardown
        } finally {
            baseTest.tearDown();
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        if (resultUpdater != null) {
            try {
                resultUpdater.sendTestResults(testRunId);
            } catch (Exception e) {
                // Silently handle errors
            }
        }
    }

    private String getShortFeatureName(String uri) {
        String fileName = uri.substring(uri.lastIndexOf("/") + 1);
        return fileName.replace(".feature", "").toLowerCase();
    }

    private String getScenarioName(Scenario scenario) {
        return "S" + scenario.getLine();
    }

    private void captureScreenshot(Scenario scenario) {
        WebDriver driver = baseTest.getDriver();
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

            String featureName = getShortFeatureName(scenario.getUri().toString());
            String scenarioName = getScenarioName(scenario);
            String testCaseId = testRailAPI != null ? testRailAPI.getTestCaseIdForScenario(featureName, scenarioName) : "UnknownTC";
            String screenshotDir = "target/screenshots/" + testCaseId + "/" + scenarioName;
            String screenshotPath = screenshotDir + "/screenshot.png";

            try {
                FileUtils.forceMkdir(new File(screenshotDir));
                FileUtils.copyFile(screenshot, new File(screenshotPath));
            } catch (IOException e) {
                // Silently handle errors
            }
        }
    }
}