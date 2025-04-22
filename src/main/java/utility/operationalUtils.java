package utility;

import okhttp3.*;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static Resources.ProjectConstants.*;
import static Resources.ProjectConstants.URL_PROD_ENV;
import static junit.framework.TestCase.failNotEquals;
import static org.testng.Assert.assertTrue;

public class operationalUtils {
    private static String token;
    private final WebDriverWait wait;
    public WebDriver driver;

    public operationalUtils(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null in operationalUtils");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void moveToElementAction(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public boolean elementsPresent(List<WebElement> elements, int duration) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(duration)).until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) elements));
            return true;
        } catch (NoSuchElementException | TimeoutException | IndexOutOfBoundsException ignore) {
            return false;
        }
    }

    public void waitElementVisibility(int duration, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAttributeToBe(WebElement element, String attribute, String attributeValue) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.attributeToBe(element, attribute, attributeValue));
        } catch (NoSuchElementException | TimeoutException e) {
            org.testng.Assert.fail(e.getMessage());
        }
    }

    public void waitUntilElementDissapears(int timeout, int polling, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(polling));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitJqueryFullyloaded(int timeout) {
        System.out.println("### Method require.s.context start");
        Wait<WebDriver> jsWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        try {
            ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) this.driver)
                    .executeScript("return jQuery.isEmptyObject(require.s.contexts._.registry)").toString().equals("true");

            boolean jsReady = jsExec.executeScript("return jQuery.isEmptyObject(require.s.contexts._.registry)").toString().equals("true");
            if (!jsReady) {
                jsWait.until(jsLoad);
            }
        } catch (TimeoutException | JavascriptException ex) {
            ex.printStackTrace();
            System.out.println("### Method require.s.context different to true Timeout ERROR");
        }
    }

    public void clickDirectlyByDOM(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static String getTokenAPI() {
        if (token == null) {
            token = getMagento_token();
        }
        return token;
    }
        public static String getMagento_token() {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            okhttp3.MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"username\": \"" + USERNAME_MAGENTO_MPS_ADMIN_QA01 + "\",\n    \"password\": \"" + PASSWORD_MAGENTO_MPS_ADMIN_QA01 + "\"\n}");
            Request request = new Request.Builder()
                    .url("https://qa01.mps-aws.com/rest/V1/integration/admin/token")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            try{
                Response response = client.newCall(request).execute();
                return response.body().string().replaceAll("^\"|\"$", "");
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

    public void waitForAllJS(int time) {
        waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        waitForJQueryToDone(time);
    }

    public void waitForJQueryToDone(int timeout) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeout)).until((ExpectedCondition<Boolean>) d -> {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
            });
        } catch (TimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void waitURLtoBeContains(int timeOut, String url) {
        new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(ExpectedConditions.urlContains(url));
    }

    public static void assertEquals(Object expected, Object actual) {
        assertEquals((String) null, (Object) expected, (Object) actual);
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        if (!equalsRegardingNull(expected, actual)) {
            if (expected instanceof String && actual instanceof String) {
                String cleanMessage = message == null ? "" : message;
                throw new ComparisonFailure(cleanMessage, (String) expected, (String) actual);
            } else failNotEquals(message, expected, String.valueOf(actual));
        }
    }

    private static boolean equalsRegardingNull(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        } else {
            return isEquals(expected, actual);
        }
    }

    private static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }

    public void waitElementClickability(int duration, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(duration)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void getURLwithDomain(String subURL, String domain) {
        switch (domain.toLowerCase()) {
            case "en":
                driver.get(System.getProperty("base.url") + subURL);
                waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
                break;
            case "cn":
                if (System.getProperty("base.url").toLowerCase().contains("qa01")) {
                    driver.get("http://qa01.mps-aws.cn" + subURL);
                    waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
                } else {
                    driver.get("https://www.monolithicpower.cn" + subURL);
                    waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
                }
                break;
        }
        waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
    }

    public void getURL(String subURL) {
        driver.get("https://sandbox.monolithicpower.com/" + subURL);
        waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
    }

    public void getURLWithEnv(String env, String subURL) {
        switch (env) {
            case "staging":
                driver.get(URL_STAGING_ENV + subURL);
                break;
            case "sandbox":
                driver.get(URL_SANDBOX_ENV + subURL);
                break;
            case "qa01":
                driver.get(URL_QA01_ENV + subURL);
                break;
            case "prod":
                driver.get(URL_PROD_ENV + subURL);
                break;
        }
        waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
    }

    public void getSpecificURL(String URL) {
        driver.get(URL);
        waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
    }

    public void deleteWebElementAttribute(WebElement element, String attribute) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attribute + "')", element);
        } catch (NoSuchElementException | TimeoutException e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    public static List<String> splitNewLineInString(String line) {
        List<String> items = Arrays.asList(line.split("\\s*\n\\s*"));
        return items;
    }

    public static Date parseDateFromWeb(String stringDate) {
        String month = monthNametoNumber(stringDate.substring(0, 3).trim());
        String day = stringDate.substring(stringDate.indexOf(" ") + 1, stringDate.indexOf(","));
        String year = stringDate.substring(stringDate.indexOf(",") + 2, stringDate.indexOf(",") + 6).trim();
        String dateConcatenate = day + "/" + month + "/" + year;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formatter.parse(dateConcatenate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String monthNametoNumber(String monthName) {
        switch (monthName) {
            case "Jan":
                return "1";
            case "Feb":
                return "2";
            case "Mar":
                return "3";
            case "Apr":
                return "4";
            case "May":
                return "5";
            case "Jun":
                return "6";
            case "Jul":
                return "7";
            case "Aug":
                return "8";
            case "Sep":
                return "9";
            case "Oct":
                return "10";
            case "Nov":
                return "11";
            case "Dec":
                return "12";
            default:
                return "0";
        }
    }

    public static String splitProduct(String product) {
        String numberOnlyProduct = product.replaceAll("[^0-9]", "");
        return numberOnlyProduct;
    }

    public static ArrayList<String> splitInListSeparatedByComma(String input) {
        ArrayList<String> listString = new ArrayList<>(Arrays.asList(input.split("\\s*,\\s*")));
        return listString;
    }

    public boolean executeJavascriptCheckbox_isChecked(String input, String classElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (Boolean) executor.executeScript("return jQuery('input[value=\"" + input + "\"][class=" + classElement + "]').is(':checked')");
    }

    public static Map<String, String> mapMin_Max(String min_max) {
        Map<String, String> map = new HashMap<String, String>();
        min_max = min_max.replace('\n', '*');
        map.put("min", min_max.substring(0, min_max.indexOf("*")));
        map.put("max", min_max.substring(min_max.indexOf("*") + 1, min_max.length()));
        return map;
    }

    public void waitElementInViewPort(int duration, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(duration)).until(isVisibleInViewport(element));
    }

    public static ExpectedCondition<Boolean> isVisibleInViewport(WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                Actions act = new Actions(driver);
                act.moveToElement(element).perform();
                System.out.println("#### Performing Move to Element By Actions");
                return (Boolean) ((JavascriptExecutor) driver)
                        .executeScript(
                                "var elem = arguments[0],                 " +
                                        "  box = elem.getBoundingClientRect(),    " +
                                        "  cx = box.left + box.width / 2,         " +
                                        "  cy = box.top + box.height / 2,         " +
                                        "  e = document.elementFromPoint(cx, cy); " +
                                        "for (; e; e = e.parentElement) {         " +
                                        "  if (e === elem)                        " +
                                        "    return true;                         " +
                                        "}                                        " +
                                        "return false;                            ",
                                element);
            }
        };
    }

    public static boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void interactWebElementDOMJavaScript(WebElement element, String method) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0]." + method + "();", element);
        } catch (NoSuchElementException | TimeoutException e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    public void waitForTextDifferentToZero(WebElement element) {
        try {
            waitForTextDifferentToEmpty(element);
            new WebDriverWait(driver, Duration.ofSeconds(20)).until((ExpectedCondition<Boolean>) d -> {
                return (Boolean) (Integer.valueOf(element.getText()) != 0);
            });
        } catch (NoSuchElementException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void waitForTextDifferentToEmpty(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until((ExpectedCondition<Boolean>) d -> {
                return (Boolean) (element.getText().length() > 0);
            });
        } catch (NoSuchElementException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void waitElementPresence(int duration, String xpathLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(duration)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathLocator)));
    }

    public void waitForAttributeNotPresent(WebElement element, String attribute) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until((ExpectedCondition<Boolean>) d -> {
                String enabled = element.getAttribute(attribute);
                if (enabled != null) return false;
                if (enabled == null) return true;
                else return false;
            });
        } catch (NoSuchElementException | TimeoutException e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    public boolean elementNotPresent(WebElement element, int duration) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(duration)).until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (NoSuchElementException e) {
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void assertElementNotPresent(WebElement element, int duration) {
        org.testng.Assert.assertTrue(elementNotPresent(element, duration));
    }

    public boolean elementNotClickable(WebElement element, int duration) {
        try {
            if (elementPresent(element, duration)) {
                if (element.isEnabled()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } catch (NoSuchElementException | TimeoutException ignore) {
            return false;
        }
    }

    public boolean elementPresent(WebElement element, int duration) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(duration)).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException | TimeoutException ignore) {
            return false;
        }
    }

    public void assertElementClickable(WebElement element) {
        org.testng.Assert.assertTrue(elementNotPresent(element, 10));
    }

    public boolean elementClickable(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (NoSuchElementException | TimeoutException ignore) {
            return false;
        }
    }

    public void assertElementNotClickable(WebElement element, int duration) {
        org.testng.Assert.assertTrue(elementNotPresent(element, duration));
    }

    public void assertElementPresent(WebElement element, int duration) {
        assertTrue(elementPresent(element), String.valueOf(10));
    }

    public void assertElementPresent(WebElement element) {
        org.testng.Assert.assertTrue(elementNotPresent(element, 10));
    }

    public boolean elementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException | TimeoutException ignore) {
            return false;
        }
    }

    public void clickOptionWithValue(WebElement optionElement, String value) {
        optionElement.findElement(By.xpath("option[@value='" + value + "']")).click();
    }

    public void waitElementInvisibility(int duration, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(duration)).until(ExpectedConditions.invisibilityOf(element));
    }

    public void assertSelectedValue(WebElement selectElement, String expectedValue) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String optionValue = (String) executor.executeScript("return arguments[0].value", selectElement);
        assertEquals(optionValue, expectedValue);
    }

    public void assertOptionSelected(WebElement optionElement, String value) {
        org.junit.Assert.assertEquals(
                optionElement.getAttribute("value"),
                value);
    }

    public Boolean elementIsVisibleInViewport(WebElement element) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            ",
                element);
    }

    public static String genUniqueStrFromCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date()).toString();
    }

    public void moveToElementByActions(WebElement element) {
        try {
            Actions act = new Actions(driver);
            act.moveToElement(element).perform();
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(isVisibleInViewport(element));
        } catch (NoSuchElementException | TimeoutException e) {
            Assert.fail(e.getMessage());
        }
    }

    public static void replaceTextByKeyboardKeys(WebElement element, String value) {
        CharSequence[] values = new CharSequence[20];
        for (int i = 0; i < 20; i += 2) {
            values[i] = Keys.BACK_SPACE;
            values[i + 1] = Keys.DELETE;
        }
        element.sendKeys(values);
        element.sendKeys(value);
    }

    public void inputInvalidHighValueFromWebEleTitle(WebElement element) {
        moveToElementByActions(element);
        this.clickAndStayAround(element);
        String TonDelay = convertStrToHigherInvalidInput(element.getAttribute("title"));
        element.clear();
        element.sendKeys(TonDelay);
    }

    public void inputInvalidLowValueFromWebEleTitle(WebElement element) {
        moveToElementByActions(element);
        this.clickAndStayAround(element);
        String TonDelay = convertStrToLowInvalidInput(element.getAttribute("title"));
        element.clear();
        element.sendKeys(TonDelay);
    }

    public void inputValueFromWebEleTitle(WebElement element) {
        moveToElementByActions(element);
        this.clickAndStayAround(element);
        String TonDelay = convertStrToValidInput(element.getAttribute("title"));
        element.clear();
        element.sendKeys(TonDelay);
    }

    public static String convertStrToValidInput(String stringValue) {
        String[] splitedNumber = stringValue.split("[^\\d|\\.]+");
        return String.valueOf(Double.valueOf(splitedNumber[0]) + 0.5 * (Double.valueOf(splitedNumber[1]) - Double.valueOf(splitedNumber[0])));
    }

    public static String convertStrToLowInvalidInput(String stringValue) {
        String[] splitedNumber = stringValue.split("[^\\d|\\.]+");
        return String.valueOf(Double.valueOf(splitedNumber[0]) - 2 * Double.valueOf(splitedNumber[2]));
    }

    public void clickAndStayAround(WebElement element) {
        try {
            Actions act = new Actions(driver);
            act.click();
            act.pause(200);
            act.moveByOffset(0, 3);
            act.pause(200);
            act.moveByOffset(0, -3);
            act.pause(200);
            act.moveToElement(element).perform();
            act.click();
            act.pause(200);
            act.moveToElement(element).perform();
            new WebDriverWait(driver, Duration.ofSeconds(INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT)).until(isVisibleInViewport(element));
        } catch (NoSuchElementException | TimeoutException e) {
            Assert.fail(e.getMessage());
        }
    }

    public static String convertStrToHigherInvalidInput(String stringValue) {
        String[] splitedNumber = stringValue.split("[^\\d|\\.]+");
        return String.valueOf(Double.valueOf(splitedNumber[1]) + 2 * Double.valueOf(splitedNumber[2]));
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void switchToFrame(String IdOrName) {
        driver.switchTo().frame(IdOrName);
    }

    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    public void inputByAction(WebElement e, String v) {
        Actions act = new Actions(this.driver);
        this.clickAndStayAround(e);
        act.sendKeys(v);
        act.perform();
    }
}