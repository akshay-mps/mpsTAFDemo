package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CheckoutProcess extends BaseTest {

    @FindBy(id = "top-cart-btn-checkout")
    public static WebElement CHECKOUT_BUTTON_CART;
    @FindBy(id = "stripe_payments")
    public static WebElement CARD_PAYMENT_METHOD;
    @FindBy(id = "purchaseorder")
    public static WebElement ORDER_PAYMENT_METHOD;
    @FindBy(id = "po_number")
    public static WebElement PURCHASE_ORDER_NUMBER_FIELD;
    @FindBy(xpath = "//div[@class='primary']//button[@class='button action continue primary']")
    public static WebElement NEXT_BUTTON_CHECKOUTPROCESS;
    @FindBy(xpath = "//div[@id='actions-toolbar']//*[@class='action primary checkout']")
    public static WebElement PLACE_ORDER_BUTTON;
    @FindBy(id = "popup-cn")
    public static WebElement POPUP_DIV_CN_STORE;
    @FindBy(xpath = "//aside[@class='modal-popup             modal-slide            _inner-scroll _show']//div[@class='modal-inner-wrap']//header[@class='modal-header']//button[@class='action-close']")
    public static WebElement POPUP_CLOSE_BUTTON_CN_STORE;
    @FindBy(xpath = "//div[@id='stripe-payments-card-cvc']//div//iframe")
    public static WebElement IFRAME_CVC;
    @FindBy(xpath = "//div[@id='stripe-payments-card-number']//div//iframe")
    public static WebElement IFRAME_CARD_NUMBER;
    @FindBy(xpath = "//div[@id='stripe-payments-card-expiry']//div//iframe")
    public static WebElement IFRAME_CARD_EXPIRATION_DATE;
    @FindBy(name = "payment[cc_saved]")
    public static List<WebElement> CARD_DETAILS_SAVED;
    @FindBy(xpath = "//*[@class='base']")
    public static WebElement TITLE_ACCOUNT_SECTION_CHECKOUT;
    @FindBy(xpath = "//fieldset[@id='customer-email-fieldset']//div//div//input[@id='customer-email']")
    public static WebElement EMAIL_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[1]//div[@class='control']//select[@name='country_id']")
    public static WebElement COUNTRY_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[2]//div[@class='control']//input[@name='firstname']")
    public static WebElement FIRST_NAME_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[3]//div[@class='control']//input[@name='lastname']")
    public static WebElement LAST_NAME_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//fieldset[@class='field street admin__control-fields required']//div[@class='control']//div[@class='field _required']//div[@class='control']//input[@name='street[0]']")
    public static WebElement ADDRESS_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//fieldset[@class='field street admin__control-fields required']//div[@class='control']//div[@class='field _required']//div[@class='control']//textarea[@name='street[0]']")
    public static WebElement CN_ADDRESS_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//input[@name='city']")
    public static WebElement CITY_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[6]//div[@class='control']//input[@name='city']")
    public static WebElement CN_CITY_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//select[@name='region_id']")
    public static WebElement PROVINCE_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[4]//div[@class='control']//select[@name='region_id']")
    public static WebElement CN_PROVINCE_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//input[@name='postcode']")
    public static WebElement ZIPCODE_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[7]//div[@class='control']//input[@name='postcode']")
    public static WebElement CN_ZIPCODE_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//input[@name='telephone']")
    public static WebElement TELEPHONE_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[8]//div[@class='control _with-tooltip']//input[@name='telephone']")
    public static WebElement CN_TELEPHONE_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[9]//div[@class='control']//input[@name='company']")
    public static WebElement CN_COMPANY_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[10]//div[@class='control']//select[@name='segment']")
    public static WebElement CN_SEGMENT_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//div[@id='shipping-new-address-form']//div[19]//div[@class='control']//select[@name='application_industry']")
    public static WebElement CN_INDUSTRY_FIELD_REQUIRED_INFORMATION_CHECKOUT;
    @FindBy(xpath = "//li[@id='shipping']//div[@id='checkout-step-shipping']//div[@id='shipping-address-login-fieldset']//div[1]//div[1]//div[1]//select[@name='segment']")
    public static WebElement CN_SEGMENT_FIELD_REQUIRED_INFORMATION_CHECKOUT_PROCESS;
    @FindBy(xpath = "//li[@id='shipping']//div[@id='checkout-step-shipping']//div[@id='shipping-address-login-fieldset']//div[1]//div[10]//div[1]//select[@name='application_industry']")
    public static WebElement CN_APPLICATION_FIELD_REQUIRED_INFORMATION_CHECKOUT_PROCESS;
    @FindBy(xpath = "//div[@class='modals-wrapper']//aside[@class='modal-popup             modal-slide            _inner-scroll _show']//div[@class='modal-inner-wrap']")
    public static WebElement CN_CHECKOUT_USE_CN_WEBSITE_SUGGESTION_POPUP;
    @FindBy(xpath = "//li[@id='shipping']//div[@id='checkout-step-shipping']//div[@class='field addresses']//div[1]//div[@class='shipping-address-items']//div[2]//button[2]")
    public static WebElement SHIP_HERE_US_BUTTON_CHECKOUT;
    @FindBy(xpath = "//li[@id='shipping']//div[@id='checkout-step-shipping']//div[@class='field addresses']//div[1]//div[@class='shipping-address-items']//div[1]//button[2]")
    public static WebElement SHIP_HERE_CN_BUTTON_CHECKOUT;
    @FindBy(xpath = "//li[@id='shipping']//div[@id='checkout-step-shipping']//div[@class='field addresses']//div[@class='control']//div[@class='shipping-address-items']//div[2]")
    public static WebElement US_ADDRESS_DIV_CHECKOUT;
    @FindBy(xpath = "//div[@class='actions-toolbar']//div[@class='primary']//a")
    public static WebElement CN_CREATE_ACCOUNT_POP_UP_GUEST_CHECKOUT;
    private final WebDriver driver;

    public CheckoutProcess(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);    }
}
