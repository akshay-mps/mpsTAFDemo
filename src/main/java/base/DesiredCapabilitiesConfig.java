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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DesiredCapabilitiesConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DesiredCapabilitiesConfig.class);

    public WebDriver getDriver(String browserName) {
        if (browserName == null || browserName.trim().isEmpty()) {
            LOGGER.error("browserName is null or empty");
            throw new IllegalArgumentException("browserName is null or empty");
        }

        LOGGER.info("Setting up WebDriver for browser: {}", browserName);
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
        WebDriverManager.edgedriver().driverVersion("126.0.2592.102").setup(); // Replace with a known version
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