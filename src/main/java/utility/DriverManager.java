package utility;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);

    public static WebDriver getDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver == null) {
            LOG.warn("WebDriver is null for the current thread");
            // Don't throw exception to allow graceful handling in callers
        }
        LOG.info("Getting driver: {}", webDriver);
        return webDriver;
    }

    public static void setDriver(WebDriver webDriver) {
        if (webDriver == null) {
            LOG.error("Attempted to set null WebDriver");
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        driver.set(webDriver);
        LOG.info("WebDriver set for the current thread: {}", webDriver);
    }

    public static void removeDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            try {
                webDriver.quit();
                LOG.info("WebDriver quit in removeDriver");
            } catch (Exception e) {
                LOG.error("Error quitting WebDriver in removeDriver: {}", e.getMessage());
            }
        }
        driver.remove();
        LOG.info("WebDriver removed from ThreadLocal");
    }
}