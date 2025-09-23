package Resources;

import org.junit.Assert;

import java.util.Optional;

import static Resources.ProjectConstants.*;

public enum Property {

        PLATFORM_DEVICE(System.getProperty("platform.os")),
        PLATFORM_NAME(System.getProperty("platform.name")),
        PLATFORM_VERSION(System.getProperty("platform.version")),
        IMPLICIT_WAIT(Optional.ofNullable(System.getProperty("implicit.wait")).orElse("5")),
        COMPARE_IMAGE(Optional.ofNullable(System.getProperty("compare.image")).orElse("false")),

        //Appium Specific
        APP_FILE(System.getProperty("app.file")),
        DEVICE_NAME(System.getProperty("device.name")),
        DEVICE_ORIENTATION(System.getProperty("device.orientation")),
        APPIUM_HOST(Optional.ofNullable(System.getProperty("appium.host")).orElse("127.0.0.1")),
        APPIUM_PORT(Optional.ofNullable(System.getProperty("appium.port")).orElse("0")),
        NO_RESET(Optional.ofNullable(System.getProperty("no.reset")).orElse("true")),
        IGNORE_UNIMPORTANT_VIEWS(Optional.ofNullable(System.getProperty("ignore.unimportant.views")).orElse("false")),
        NATIVE_WEB_SCREENSHOT(Optional.ofNullable(System.getProperty("native.web.screenshot")).orElse("false")),
        APPIUM_LOG(Optional.ofNullable(System.getProperty("appium.log")).orElse("warn")),
        XCODE_ORG_ID(System.getProperty("xcode.org.id")),
        XCODE_SIGNING_ID(System.getProperty("xcode.signing.id")),

        //Selenium Specific
        BROWSER_NAME(Optional.ofNullable(System.getProperty("browser.name")).orElse("Chrome")),
        BASE_URL(getBaseURL(System.getProperty("base.url"))),
        GRID_URL(System.getProperty("grid.url")),
        GRID_USE(System.getProperty("grid.use")),
        CBT_URL(System.getProperty("cbt.url")),
        CBT_USE(System.getProperty("cbt.use")),
        CBT_USERNAME(Optional.ofNullable(System.getProperty("cbt.username")).orElse("anthony.brittis")),
        CBT_AUTH_KEY(Optional.ofNullable(System.getProperty("cbt.authkey")).orElse("f8e2e50a-0831-4e22-ab05-e667e6a3e9ac")),
        //FEATURES_PATH(System.getProperty("features.path")+ File.separator +"src"+ File.separator +"test"+ File.separator +"resources"+ File.separator +"features"+ File.separator),
        FEATURES_PATH(System.getProperty("features.path")),
        SELENIUM_LOG(Optional.ofNullable(System.getProperty("selenium.log")).orElse("WARNING")),
        BROWSER_HEIGHT(Optional.ofNullable(System.getProperty("browser.height")).orElse("1000")),
        BROWSER_WIDTH(Optional.ofNullable(System.getProperty("browser.width")).orElse("1400")),

        //Test Management
        TEST_RAIL_FLAG(Optional.ofNullable(System.getProperty("testrail.flag")).orElse("true")),
        TESTRAIL_URL(Optional.ofNullable(System.getProperty("testrail.url")).orElse("https://mpsmps.testrail.io/")),
        TESTRAIL_USERNAME(Optional.ofNullable(System.getProperty("testrail.username")).orElse("akshay.chauhan@monolithicpower.com")),
        TESTRAIL_PASSWORD(Optional.ofNullable(System.getProperty("testrail.password")).orElse("Iforgot@123")),
        TESTRAIL_PROJECT_NAME(Optional.ofNullable(System.getProperty("testrail.projectname")).orElse("singleTestCase")),
        TESTRAIL_SECTION_NAME(Optional.ofNullable(System.getProperty("testrail.sectionname")).orElse("Search Box")),
        TESTRAIL_PROJECTID(Optional.ofNullable(System.getProperty("testrail.projectid")).orElse("3")),
        TESTRAIL_SUITEID(Optional.ofNullable(System.getProperty("testrail.suiteid")).orElse("7")),
        TESTRAIL_USERID(Optional.ofNullable(System.getProperty("testrail.userid")).orElse("4")),

        // SauceLabs
        SAUCELABS_ENABLED(Optional.ofNullable(System.getProperty("saucelabs.enabled")).orElse("false")),
        SAUCELABS_TUNNEL(Optional.ofNullable(System.getProperty("saucelabs.tunnel")).orElse("")),
        SAUCELABS_USERNAME(Optional.ofNullable(System.getProperty("saucelabs.username")).orElse("anthony.brittis")),
        SAUCELABS_ACCESS_KEY(Optional.ofNullable(System.getProperty("saucelabs.accessKey")).orElse("f8e2e50a-0831-4e22-ab05-e667e6a3e9ac")),
        SAUCELABS_REMOTE_URL(Optional.ofNullable(System.getProperty("saucelabs.remoteUrl")).orElse("https://ondemand.saucelabs.com/wd/hub"));

        private String value;

        Property(String value) {
            this.value = value;
        }

        public boolean toBoolean() {
            if (stringIsEmpty(value)) {
                Assert.fail("Property " + this.name() + " is missing. Check your your pom.xml");
            }
            return Boolean.parseBoolean(value);
        }

        public int toInt() {
            if (stringIsEmpty(value)) {
                Assert.fail("Property " + this.name() + " is missing. Check your your pom.xml");
            }
            return Integer.parseInt(value);
        }

        public String toString() {
            if (stringIsEmpty(value)) {
                Assert.fail("Property " + this.name() + " is missing. Check your your pom.xml");
            }
            return value;
        }
        public static boolean stringIsEmpty(CharSequence cs) {
            return cs == null || cs.length() == 0;
        }

    public static String getBaseURL(String env) {
        if (env == null) {
            throw new IllegalArgumentException("Environment cannot be null");
        }
        switch (env.toLowerCase()) {
            case "staging":
                return URL_STAGING_ENV;
            case "sandbox":
                return URL_SANDBOX_ENV;
            case "qa01":
                return URL_QA01_ENV;
            case "prod":
                return URL_PROD_ENV;
            default:
                throw new IllegalArgumentException("Unknown environment: " + env);
        }
    }

    }
