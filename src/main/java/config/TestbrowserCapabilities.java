package config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestbrowserCapabilities {
    private static final Logger LOG = LoggerFactory.getLogger(TestbrowserCapabilities.class);

    public static Capabilities getBrowserCapabilities(String browserName) {
        Capabilities capabilities = null;

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--remote-debugging-port=9222");
                capabilities = chromeOptions;
                LOG.info("Configured ChromeOptions with normal (non-headless) and incognito modes");
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("browserName", "firefox");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                firefoxOptions.addArguments("--disable-gpu");
                capabilities = firefoxOptions;
                LOG.info("Configured FirefoxOptions");
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("browserName", "edge");
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-extensions");
                capabilities = edgeOptions;
                LOG.info("Configured EdgeOptions");
                break;
            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setCapability("browserName", "safari");
                capabilities = safariOptions;
                LOG.info("Configured SafariOptions");
                break;
            default:
                LOG.error("Unsupported browser: {}", browserName);
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        return capabilities;
    }
}