package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.URL;
import java.time.LocalDateTime;


import static Resources.Property.*;

public class DesiredCapabilitiesConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DesiredCapabilitiesConfig.class);

    public WebDriver getDriver(String browserName, String runEnv) {
        if (browserName == null || browserName.trim().isEmpty()) {
            LOGGER.error("browserName is null or empty");
            throw new IllegalArgumentException("browserName is null or empty");
        }

        String executionEnv = SAUCELABS_ENABLED.toString(); // check if saucelab is enabled
        LOGGER.info("Execution environment: {}", executionEnv);

        if ("yes".equalsIgnoreCase(runEnv)) {
            return setupSauceDriver(browserName);
        } else {
            LOGGER.info("Setting up LOCAL WebDriver for browser: {}", browserName);
            switch (browserName.toLowerCase()) {
                case "chrome":
                    return setupChromeDriver();
                case "firefox":
                    return setupFirefoxDriver();
                case "edge":
                    return setupEdgeDriver();
                case "safari":
                    return setupSafariDriver();
                default:
                    LOGGER.error("Unsupported browser: {}", browserName);
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }
        }
    }

    private WebDriver setupSauceDriver(String browserName) {
        try {
            if (SAUCELABS_USERNAME == null || SAUCELABS_ACCESS_KEY == null || SAUCELABS_REMOTE_URL == null) {
                throw new IllegalStateException("SauceLabs credentials/URL is not set!");
            }

            String sauceUrl = "https://" + SAUCELABS_USERNAME + ":" + SAUCELABS_ACCESS_KEY + SAUCELABS_REMOTE_URL;

            MutableCapabilities sauceOptions = new MutableCapabilities();

            String buildName = SAUCELABS_BUILD_NAME + "_" +
                    java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            sauceOptions.setCapability("build", buildName);

            sauceOptions.setCapability("name", "Scenario Execution - " + browserName);

            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", browserName);
            capabilities.setCapability("browserVersion", "latest");
            capabilities.setCapability("platformName", "Windows 11");
            capabilities.setCapability("sauce:options", sauceOptions);

            LOGGER.info("Launching tests on Sauce Labs with browser: {}, build: {}", browserName, buildName);
            return new RemoteWebDriver(new URL(sauceUrl), capabilities);

        } catch (Exception e) {
            LOGGER.error("Failed to initialize SauceLabs driver: {}", e.getMessage(), e);
            throw new RuntimeException("SauceLabs driver setup failed", e);
        }
    }


    private WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("--disk-cache-size=0");
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--dns-prefetch-disable");
        return new ChromeDriver(chromeOptions);
    }

    private WebDriver setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        firefoxOptions.addArguments("-private");
        firefoxOptions.addPreference("browser.startup.homepage_override.mstone", "ignore");
        firefoxOptions.addPreference("browser.cache.disk.enable", false);
        firefoxOptions.addPreference("network.dns.disablePrefetch", true);
        firefoxOptions.addPreference("security.certerror.hideAddException", true);
        firefoxOptions.addPreference("extensions.enabledScopes", 0);
        return new FirefoxDriver(firefoxOptions);
    }

    private WebDriver setupEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        edgeOptions.addArguments("--inprivate");
        edgeOptions.addArguments("start-maximized");
        edgeOptions.addArguments("disable-infobars");
        edgeOptions.addArguments("--disable-gpu");
        edgeOptions.addArguments("ignore-certificate-errors");
        edgeOptions.addArguments("--disk-cache-size=0");
        edgeOptions.addArguments("enable-automation");
        edgeOptions.addArguments("--no-sandbox");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--dns-prefetch-disable");
        return new EdgeDriver(edgeOptions);
    }

    private WebDriver setupSafariDriver() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability("safari.cleanSession", true);
        safariOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        LOGGER.info("SafariDriver has limited support for arguments like disable-infobars, disable-gpu, or no-sandbox");
        return new SafariDriver(safariOptions);
    }
}
