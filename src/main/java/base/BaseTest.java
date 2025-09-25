package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.DriverManager;
import java.io.IOException;
import java.util.Properties;

import static Resources.Property.*;
import base.DesiredCapabilitiesConfig;

public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private Properties prop;

    public BaseTest() {
    }

    public WebDriver getDriver() {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            LOGGER.error("WebDriver is null for the current thread");
            throw new IllegalStateException("WebDriver is null for the current thread");
        }
        return driver;
    }

    public WebDriver setup() throws IOException {
        try {
            WebDriver driver = initializeDriver();
            if (driver == null) {
                throw new IllegalStateException("initializeDriver() returned null");
            }
            DriverManager.setDriver(driver);
            LOGGER.info("WebDriver selected: {}", driver);
            LOGGER.info("Starting the test");
            initElements();
            return driver;
        } catch (Exception e) {
            LOGGER.error("Failed to set up WebDriver: {}", e.getMessage(), e);
            throw new RuntimeException("WebDriver setup failed", e);
        }
    }

    private WebDriver initializeDriver() {
        String browserName = BROWSER_NAME.toString(); // Assuming BROWSER_NAME is correctly defined somewhere
        if (browserName == null || browserName.trim().isEmpty()) {
            LOGGER.error("browserName property is missing or empty");
            throw new IllegalArgumentException("browserName property is missing or empty");
        }

        LOGGER.info("Initializing WebDriver with browser: {}", browserName);

        // Create an instance of DesiredCapabilitiesConfig to access getDriver method
        DesiredCapabilitiesConfig capabilitiesConfig = new DesiredCapabilitiesConfig();
        return capabilitiesConfig.getDriver(browserName); // Call getDriver() from DesiredCapabilitiesConfig
    }

    private void initElements() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            PageFactory.initElements(driver, this);
            LOGGER.info("Initialized WebElements for PageFactory");
        } else {
            LOGGER.warn("Cannot initialize WebElements - WebDriver is null");
        }
    }

    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            LOGGER.info("Closing browser and ending test session");
            try {
                driver.quit();
            } catch (Exception e) {
                LOGGER.error("Error while quitting WebDriver: {}", e.getMessage(), e);
            } finally {
                DriverManager.removeDriver();
                LOGGER.info("WebDriver removed and session closed");
            }
        } else {
            LOGGER.warn("WebDriver is null, nothing to tear down");
        }
    }
}
