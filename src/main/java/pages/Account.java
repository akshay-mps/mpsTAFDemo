package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class Account extends  BaseTest{
    @FindBy(xpath = "//main[@id='maincontent']//div[@class='page-title-wrapper']//h1//span")
    public static WebElement TITLE_ACCOUNT_SECTION;
    @FindBy(xpath = "//div[@class='column main']")
    public static WebElement DIV_MAIN_ACCOUNT_SECTION;
    @FindBy(xpath = "//ul[@class='nav items']//li[@class='nav item']")
    public static List<WebElement> LI_ITEMS_LEFT_MENU_ACCOUNT_LIST;
    @FindBy(id = "firstname")
    public static WebElement STUDENT_FIRSTNAME;
    @FindBy(id = "lastname")
    public static WebElement STUDENT_LASTNAME;
    @FindBy(id = "student_email")
    public static WebElement STUDENT_EMAIL;
    @FindBy(id = "password")
    public static WebElement STUDENT_PASSWORD;
    @FindBy(id = "select_major")
    public static WebElement STUDENT_MAJOR;
    @FindBy(id = "university_name")
    public static WebElement STUDENT_UNIVERSITY;
    @FindBy(id = "student_country")
    public static WebElement STUDENT_COUNTRY;
    @FindBy(id = "expected_graduation_date")
    public static WebElement STUDENT_GRADAUTION;
    @FindBy(className = "submit-button")
    public static WebElement STUDENT_FORM_SUBMIT;
    @FindBy(className = "ui-datepicker-year")
    public static WebElement STUDENT_GRADUATION_YEAR;
    @FindBy(className = "ui-datepicker-month")
    public static WebElement STUDENT_GRADUATION_MONTH;
    @FindBy(xpath= "//td[@data-handler='selectDay']/a[text()='20']")
    public static WebElement STUDENT_GRADUATION_DAY;
    @FindBy(id= "modal-content-0")
    public static WebElement STUDENT_ACCOUNT_MESSAGE;
    @FindBy(id="subscribed")
    public static WebElement SUBSCRIBE_LETTER;
    private WebDriver driver;

    public Account(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);    }
}
