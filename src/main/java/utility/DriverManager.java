package utility;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);

    public static WebDriver getDriver() {
        WebDriver webDriver = driver.get();
//        if (webDriver == null) {
//            LOG.error("WebDriver is null for the current thread");
//            throw new IllegalStateException("WebDriver is null for the current thread");
//        }
        LOG.info("Getting driver: {}", webDriver);
        return webDriver;
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
        LOG.info("WebDriver set for the current thread: {}", webDriver);
    }

    public static void removeDriver() {
        driver.remove();
        LOG.info("WebDriver removed and session closed for the current thread.");
    }
}