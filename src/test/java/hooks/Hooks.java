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
import utility.DriverManager;

import java.io.File;
import java.io.IOException;

public class Hooks {
    private final BaseTest baseTest = new BaseTest();
    private TestRailTestCaseAPI testRailAPI;
    private TestRailResultUpdater resultUpdater;
    private int testRunId;

    @BeforeSuite
    public void setupSuite() {
        System.out.println("Starting setupSuite");
        try {
            testRailAPI = TestRailTestCaseAPI.getInstance();
            resultUpdater = new TestRailResultUpdater();
            testRunId = resultUpdater.createTestRun();
            System.out.println("TestRail test run created: " + testRunId);
        } catch (IOException e) {
            System.err.println("IOException in setupSuite: " + e.getMessage());
            resultUpdater = null;
        } catch (Exception e) {
            System.err.println("Exception in setupSuite: " + e.getMessage());
            resultUpdater = null;
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) throws IOException {
        System.out.println("Starting beforeScenario: " + scenario.getName());
        WebDriver driver = baseTest.setup();
        if (driver == null) {
            throw new IllegalStateException("BaseTest.setup() returned null WebDriver");
        }
        System.out.println("WebDriver initialized for: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("Starting afterScenario: " + scenario.getName());
        try {
            if (scenario.isFailed()) {
                System.out.println("Capturing screenshot for failed scenario");
                captureScreenshot(scenario);
            }
            if (resultUpdater != null) {
                System.out.println("Adding test result to TestRail");
                resultUpdater.addTestResult(scenario);
            }
        } catch (Exception e) {
            System.err.println("Error in afterScenario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Tearing down WebDriver");
            baseTest.tearDown();
            System.out.println("Finished afterScenario: " + scenario.getName());
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("Starting tearDownSuite");
        if (resultUpdater != null) {
            try {
                resultUpdater.sendTestResults(testRunId);
                System.out.println("TestRail results sent for run: " + testRunId);
            } catch (Exception e) {
                System.err.println("Error in tearDownSuite: " + e.getMessage());
                e.printStackTrace();
            }
        }
        // Ensure all WebDrivers are closed
        DriverManager.removeDriver();
        System.out.println("Finished tearDownSuite");
    }

    private String getShortFeatureName(String uri) {
        String fileName = uri.substring(uri.lastIndexOf("/") + 1);
        return fileName.replace(".feature", "").toLowerCase();
    }

    private String getScenarioName(Scenario scenario) {
        return "S" + scenario.getLine();
    }

    private void captureScreenshot(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
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
                System.out.println("Screenshot saved: " + screenshotPath);
            } catch (IOException e) {
                System.err.println("Error saving screenshot: " + e.getMessage());
            }
        } else {
            System.err.println("WebDriver is null or does not support screenshots");
        }
    }
}