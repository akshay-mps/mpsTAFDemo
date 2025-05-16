package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.DriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private Properties prop;

    public BaseTest() {
        // Properties initialized in setup
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
            loadProperties();
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

    private void loadProperties() throws IOException {
        prop = new Properties();
        String filePath = "src/config/config.properties";
        LOGGER.info("Attempting to load configuration from: {}", filePath);
        try (FileInputStream config = new FileInputStream(filePath)) {
            prop.load(config);
            LOGGER.info("Successfully loaded configuration from: {}", filePath);
        } catch (IOException e) {
            LOGGER.error("Failed to load configuration file: {}", filePath, e);
            throw new IOException("Failed to load configuration file: " + filePath, e);
        }
    }

    private WebDriver initializeDriver() {
        String browserName = prop.getProperty("browserName");
        if (browserName == null || browserName.trim().isEmpty()) {
            LOGGER.error("browserName property is missing or empty in config.properties");
            throw new IllegalArgumentException("browserName property is missing or empty");
        }
        LOGGER.info("Initializing WebDriver for browser: {}", browserName);

        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                LOGGER.error("Unsupported browser: {}", browserName);
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        return driver;
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