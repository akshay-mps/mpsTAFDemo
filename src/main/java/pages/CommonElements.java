package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CommonElements {

        @FindBy(id = "search")
        public static WebElement SEARCH_BAR;
        @FindBy(xpath = "//button[@name='submitButton']")
        public static WebElement SEARCH_BUTTON_BAR;
        @FindBy(xpath = "//div[@class='header content']//div[@class='panel header']//ul[@class='header links']//li[@class='toplinks login-link']//a")
        public static WebElement LOGIN_BUTTON_HOME;
        @FindBy(id = "mps_register")
        public static WebElement SIGN_UP_BUTTON;
        @FindBy(id = "social-login-authentication")
        public static WebElement LOGIN_WINDOW;
        @FindBy(xpath = "//div[@class='social-login block-container create']//div[@class='block col-mp mp-12']")
        public static WebElement SIGN_UP_WINDOW;
        @FindBy(xpath = "//div[@class='actions-toolbar createaccount']//div[@class='popup_bottom']//a")
        public static WebElement SIGN_UP_LINK;
        @FindBy(xpath = "//body[@data-container='body']")
        public static WebElement BODY_CONTAINER;
        @FindBy(id = "closelog")
        public static WebElement LOGIN_WINDOW_CLOSE_BUTTON;
        @FindBy(xpath = "//div[@class='social-login block-container create']//button[@class='mfp-close'][contains(text(),'×')]")
        public static WebElement SIGN_UP_CLOSE_BUTTON;
        @FindBy(xpath = "//div[@class='panel header']//div[@id='switcher-store']//div[starts-with(@class,'actions dropdown options switcher-options')]")
        public static WebElement LANGUAGE_BUTTON;
        @FindBy(xpath = "//div[@id='switcher-store']//div//ul//li")
        public static List<WebElement> LANGUAGE_DROPDOWN;
        @FindBy(xpath = "//div[@class='action toggle switcher-trigger']")
        public static WebElement LANGUAGE_TEXT_HEADER;
        @FindBy(xpath = "//div[@id='switcher-store']//div//ul//a[contains(text(),'中文')]")
        public static WebElement LANGUAGE_CN;
        @FindBy(xpath = "//div[@id='switcher-store']//div//ul//a[contains(text(),'日本語')]")
        public static WebElement LANGUAGE_JP;
        @FindBy(xpath = "//div[@id='switcher-store']//div//ul//a[contains(text(),'EN')]")
        public static WebElement LANGUAGE_EN;
        @FindBy(className = "showcart")
        public static WebElement CART_BUTTON;
        @FindBy(className = "logo")
        public static WebElement MPS_LOGO_HEADER;
        @FindBy(xpath = "//strong[@class='subtitle empty']")
        public static WebElement CART_TEXT_EMPTY_CART_SUMMARY_WINDOW;
        @FindBy(xpath = "//input[@class='item-qty cart-item-qty']")
        public static WebElement CART_QUANTITY_WINDOW;
        @FindBy(xpath = "//span[@class='counter-number']")
        public static List<WebElement> CART_QUANTITY_HEADER;
        @FindBy(className = "cart")
        public static WebElement CART_SUMMARY_WINDOWN;
        @FindBy(id = "btn-minicart-close")
        public static WebElement CART_SUMMARY_CLOSE_BUTTON;
        @FindBy(className = "text-cart")
        public static List<WebElement> CART_TEXT_HEADER;
        @FindBy(id = "login_pass")
        public static WebElement PASSWORD;
        @FindBy(id = "login_email")
        public static WebElement EMAIL;
        @FindBy(id = "bnt-social-login-authentication")
        public static WebElement LOGIN_BUTTON_POPUP;
        @FindBy(xpath = "//div[@class='actions-toolbar']//div[@class='primary']//button[@class='basic_account_signup action submit primary']")
        public static WebElement SIGN_UP_BUTTON_POPUP_CREATE_BASIC_ACCOUNT;
        @FindBy(id = "password1")
        public static WebElement PASSWORD_SIGN_UP;
        @FindBy(id = "email_address1")
        public static WebElement EMAIL_SIGN_UP;
        @FindBy(className = "basic_account_signup")
        public static WebElement CREATE_BASIC_ACCOUNT_BUTTON;
        @FindBy(id = "mage_msg_popup")
        public static WebElement MODAL_POPUP_WINDOW_BASIC_ACCOUNT_CREATED;
        @FindBy(xpath = "//nav[@class='mps-mega-menu']//ul//li[6]")
        public static WebElement MENU_CONTACT;
        @FindBy(xpath = "//nav[@class='mps-mega-menu']//ul//li[@class='mmm-level mmm-level-2']")
        public static List<WebElement> LIST_MENU_ITEMS_HEADER;
        @FindBy(xpath = "//div[@class='sections nav-sections']//div//div[@id='store.menu']//nav[@class='mps-mega-menu']//ul//li[contains(@class,'mmm-level mmm-level-2')]")
        public static List<WebElement> HEADER_CONTAINER_MENU_OPTIONS;
        @FindBy(xpath = "//a[@class='action remind']")
        public static WebElement FORGOT_PASSWORD_LINK;
        @FindBy(xpath = "//div[@class='social-login block-container forgot']")
        public static WebElement FORGOT_PASSWORD_DIV;
        @FindBy(xpath = "//button[@class='action send primary popup-login-button']")
        public static WebElement FORGOT_PASSWORD_SUBMIT_BUTTON_WINDOW;
        @FindBy(xpath = "//div[@class='primary']//button[@class='action submit primary']")
        public static WebElement FORGOT_PASSWORD_RESET_MY_PASSWORD_BUTTON_FULL_PAGE;
        @FindBy(xpath = "//div[@class='message-success success message']")
        public static WebElement FORGOT_PASSWORD_SUCCESS_MESSAGE;
        @FindBy(xpath = "//div[@class='message-success success message']//div[@data-bind='html: message.text']")
        public static WebElement FORGOT_PASSWORD_SUCCESS_MESSAGE_FULL_PAGE;
        @FindBy(xpath = "//div[@class='mfp-wrap mfp-close-btn-in mfp-auto-cursor mfp-move-from-top mfp-ready']")
        public static WebElement FORGOT_PASSWORD_DIV_ENTIRE_PAGE_FOR_INTERACTION;
        @FindBy(xpath = "//div[@class='control tooltip-validate']//input[@id='email_address']")
        public static WebElement FORGOT_PASSWORD_EMAIL_FIELD;
        @FindBy(xpath = "//div[@class='control tooltip-validate']//input[@id='login_email']")
        public static WebElement LOG_IN_WINDOW_POP_UP_EMAIL_FIELD;
        @FindBy(className = "popup-title")
        public static WebElement REVERIFICATION_POP_UP_WINDOW;
        @FindBy(xpath = "//div[@class='control tooltip-validate-create']//input[@id='email_address1']")
        public static WebElement SIGN_UP_WINDOW_POP_UP_EMAIL_FIELD;
        @FindBy(xpath = "//div[@class='control tooltip-validate-create']//input[@id='password1']")
        public static WebElement SIGN_UP_WINDOW_POP_UP_PASSWORD_FIELD;
        @FindBy(xpath = "//div[@class='control tooltip-validate-create']//input[@id='password-confirmation1']")
        public static WebElement SIGN_UP_WINDOW_POP_UP_PASSWORD_CONFIRMATION__FIELD;
        @FindBy(xpath = "//div[@class='control tooltip-validate']//input[@id='login_pass']")
        public static WebElement LOG_IN_WINDOW_POP_UP_PASSWORD_FIELD;
        @FindBy(xpath = "//fieldset[@class='fieldset']//input[@id='email_address']")
        public static WebElement FORGOT_PASSWORD_EMAIL_FIELD_FULL_PAGE;
        @FindBy(xpath = "//div[@class='control tooltip-validate']//div[@id='login_email-error']")
        public static WebElement REQUIRED_MESSAGE_LOGIN_FIELD_EMAIL;
        @FindBy(xpath = "//div[@class='control tooltip-validate']//div[@id='login_pass-error']")
        public static WebElement REQUIRED_MESSAGE_LOGIN_FIELD_PASSWORD;
        @FindBy(id = "form-validate")
        public static WebElement FORGOT_PASSWORD_DIV_FULL_PAGE;
        @FindBy(xpath = "//footer[@class='page-footer']")
        public static WebElement FOOTER_PAGE;
        @FindBy(xpath = "//div[@class='footer_Container']")
        public static WebElement FOOTER_CONTAINER;
        @FindBy(xpath = "//div[@class='panel header']//ul//li[@class='after_logged_in profile']//div[@id='prw-magt-trigger3']//div[@class='view-user-info']//span[@class='view_text']")
        public static WebElement USERNAME_HEADER_CIRCLE_BUTTON;
        @FindBy(xpath = "//ul[@id='jf_dropdown']//li[@class='view-dr prw-option']")
        public static List<WebElement> MENU_ACCOUNT_USERNAME_LIST;
        @FindBy(xpath = "//div[@class='panel header']//ul//li[@class='after_logged_in profile']//div[@id='prw-magt3']//div[@class='actions dropdown options prw-options']//ul[@id='jf_dropdown']")
        public static WebElement MENU_ACCOUNT_DROPDOWN;
        @FindBy(id = "top-cart-btn-checkout")
        public static WebElement CHECKOUT_BUTTON_CART;
        @FindBy(xpath = "//div[@id='checkout']//div[@id='checkout-loader']//div[@class='loader']")
        public static WebElement SPINNING_LOADER;
        @FindBy(className = "loading-mask")
        public static WebElement DIV_LOADING_MASK;
        @FindBy(id = "checkout-loader")
        public static WebElement LOADER_ELEMENT_AVOIDING_STRATEGY;
        @FindBy(xpath = "//div[@id='upgrade-request-popup']//a")
        public static WebElement UPGRADE_MPS_PLUS_POP_UP_BUTTON;
        @FindBy(id = "upgrade-request-popup")
        public static WebElement UPGRADE_MPS_PLUS_POP_UP_WINDOW;
        @FindBy(id = "myMPS Reconsideration native form")
        public static WebElement UPGRADE_MPS_PLUS_FORM;
        @FindBy(id = "mage_msg_popup_wrap")
        public static WebElement UPGRADE_MPS_PLUS_POP_UP_WINDOW_AFTER_REQUEST;
        @FindBy(id = "contactmps")
        public static WebElement UPGRADE_MPS_PLUS_FORM_SUBMIT_BUTTON;
        @FindBy(className = "product-image-photo")
        public static WebElement IMAGE_THUMB_CART;
        @FindBy(id = "CybotCookiebotDialog")
        public static WebElement DIV_COOKIE_CONSENT_BANNER;
        @FindBy(id = "projdesc")
        public static WebElement UPGRADE_MPS_PLUS_FORM_FIELDS_INFO;

        @FindBy(id = "name")
        public static WebElement UPGRADE_MPS_PLUS_FORM_FIELDS_FNAME;

        @FindBy(id = "lname")
        public static WebElement UPGRADE_MPS_PLUS_FORM_FIELDS_LNAME;

        @FindBy(id = "mail")
        public static WebElement UPGRADE_MPS_PLUS_FORM_FIELDS_EMAIL;

        @FindBy(id = "company")
        public static WebElement UPGRADE_MPS_PLUS_FORM_FIELDS_COMPANY;

        @FindBy(id = "selectedcountry")
        public static WebElement UPGRADE_MPS_PLUS_FORM_FIELDS_COUNTRY;

        @FindBy(className = "view-user-info")
        public static WebElement USER_INFO;
        private WebDriver driver;

        public CommonElements(WebDriver driver) {
                if (driver == null) {
                        throw new IllegalArgumentException("WebDriver cannot be null");
                }
                this.driver = driver;
                PageFactory.initElements(driver, this);
        }
}
