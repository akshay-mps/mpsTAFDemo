package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.CheckoutProcess;
import utility.operationalUtils;

import static Resources.ProjectConstants.*;
import static org.junit.Assert.assertTrue;
import static pages.CheckoutProcess.*;
import static pages.CommonElements.*;
import static utility.operationalUtils.assertEquals;

public class CheckoutProcessStepDefs {

    private operationalUtils operationalUtil;
    private CheckoutProcess checkoutProcess;

    private BaseTest bt = new BaseTest();

    // Constructor for manual dependency injection
    public CheckoutProcessStepDefs() {
        WebDriver driver = bt.getDriver();
        this.operationalUtil = new operationalUtils(driver);
        this.checkoutProcess = new CheckoutProcess(driver);
    }

    @And("^I complete the form with email \"([^\"]*)\", Country \"([^\"]*)\", first name \"([^\"]*)\", last name \"([^\"]*)\", address \"([^\"]*)\", city \"([^\"]*)\", province \"([^\"]*)\", postal code \"([^\"]*)\" and phone \"([^\"]*)\" for store \"([^\"]*)\"$")
    public void iCompleteTheFormWithEmailCountryFirstNameLastNameAddressCityProvincePostalCodeAndPhone(String email, String country, String firstName, String lastName, String address, String city, String province, String zipCode, String phone, String language) {
        if(language.equals("cn")){
            operationalUtil.waitElementVisibility(5, UPGRADE_MPS_PLUS_POP_UP_WINDOW_AFTER_REQUEST);
            CN_CREATE_ACCOUNT_POP_UP_GUEST_CHECKOUT.click();
        }
        operationalUtil.waitUntilElementDissapears(100,2,SPINNING_LOADER);
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);

        EMAIL_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(email);
        operationalUtil.waitUntilElementDissapears(150,10,SPINNING_LOADER);

        Select countrySelect = new Select(COUNTRY_FIELD_REQUIRED_INFORMATION_CHECKOUT);
        countrySelect.selectByVisibleText(country);

        FIRST_NAME_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(firstName);
        LAST_NAME_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(lastName);
        if(language.equals("cn")){
            CN_ADDRESS_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(address);
            CN_CITY_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(city);

            Select regionSelect = new Select(CN_PROVINCE_FIELD_REQUIRED_INFORMATION_CHECKOUT);
            regionSelect.selectByVisibleText(province);

            CN_ZIPCODE_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(zipCode);
            CN_TELEPHONE_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(phone);
            CN_COMPANY_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys("TestCompany SLU");
            Select segmentSelect = new Select(CN_SEGMENT_FIELD_REQUIRED_INFORMATION_CHECKOUT);
            segmentSelect.selectByVisibleText(CN_CHECKOUT_FORM_SEGMENT_REQUIRED_FIELD);

            Select industrySelect = new Select(CN_INDUSTRY_FIELD_REQUIRED_INFORMATION_CHECKOUT);
            industrySelect.selectByVisibleText(CN_CHECKOUT_FORM_INDUSTRY_REQUIRED_FIELD);


        }else{
            ADDRESS_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(address);
            CITY_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(city);

            Select regionSelect = new Select(PROVINCE_FIELD_REQUIRED_INFORMATION_CHECKOUT);
            regionSelect.selectByVisibleText(province);

            ZIPCODE_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(zipCode);
            TELEPHONE_FIELD_REQUIRED_INFORMATION_CHECKOUT.sendKeys(phone);

        }


    }

    @And("^I verify CN popup and close it$")
    public void iVerifyCNPopupAndCloseIt() {
        operationalUtil.waitElementVisibility(3, POPUP_DIV_CN_STORE);
        assertTrue(!POPUP_DIV_CN_STORE.getText().equals(""));
        POPUP_CLOSE_BUTTON_CN_STORE.click();

    }

    @And("^I click in Place Order button$")
    public void iClickInPlaceOrderButton() {
            try{
                operationalUtil.clickDirectlyByDOM(PLACE_ORDER_BUTTON);
                operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
                operationalUtil.waitUntilElementDissapears(80,10,SPINNING_LOADER);
                operationalUtil.waitURLtoBeContains(INT_TIMEOUT_JQUERY_TO_DONE, "checkout/onepage/success");
            }catch (TimeoutException ex){
                ex.printStackTrace();
            }

    }

    @Then("^I verify the checkout process was completed successfully in \"([^\"]*)\"$")
    public void iVerifyTheCheckoutProcessWasCompletedSuccessfullyIn(String language) {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitElementVisibility(INT_TIMEOUT_JQUERY_TO_DONE,TITLE_ACCOUNT_SECTION_CHECKOUT);

        switch(language) {
            case "en":
                assertEquals(SUCCESS_MESSAGE_CHECKOUT_EN, TITLE_ACCOUNT_SECTION_CHECKOUT.getText().trim());
                break;
            case "cn":
                assertEquals(SUCCESS_MESSAGE_CHECKOUT_CN, TITLE_ACCOUNT_SECTION_CHECKOUT.getText().trim());
                break;
        }



    }

    @And("^I select options depending on the store \"([^\"]*)\"$")
    public void iSelectOptionsDependingOnTheStore(String language) {
        operationalUtil.waitUntilElementDissapears(INT_TIMEOUT_JQUERY_TO_DONE, 2, LOADER_ELEMENT_AVOIDING_STRATEGY);

        if(language.equals("cn")){
            operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
            Select segmentSelectCheckout = new Select(CN_SEGMENT_FIELD_REQUIRED_INFORMATION_CHECKOUT_PROCESS);
            segmentSelectCheckout.selectByVisibleText(CN_CHECKOUT_FORM_SEGMENT_REQUIRED_FIELD);

            operationalUtil.waitElementVisibility(5, CN_APPLICATION_FIELD_REQUIRED_INFORMATION_CHECKOUT_PROCESS);


            Select applicationSelectCheckout = new Select(CN_APPLICATION_FIELD_REQUIRED_INFORMATION_CHECKOUT_PROCESS);
            applicationSelectCheckout.selectByVisibleText(CN_CHECKOUT_FORM_INDUSTRY_REQUIRED_FIELD_CHECKOUT);


        } else if (language.equals("en")){
            operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
            if(US_ADDRESS_DIV_CHECKOUT.getAttribute("class").contains("not-selected-item")) {
                operationalUtil.waitElementClickability(10, SHIP_HERE_US_BUTTON_CHECKOUT);
                operationalUtil.clickDirectlyByDOM(SHIP_HERE_US_BUTTON_CHECKOUT);
                //SHIP_HERE_US_BUTTON_CHECKOUT.click();
                operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
            }

        }
    }

    @And("^I select the payment method \"([^\"]*)\" with \"([^\"]*)\" card details$")
    public void iSelectThePaymentMethodWithCardDetails(String paymentMethod, String cardDetails)  {
        operationalUtil.waitUntilElementDissapears(80,10,SPINNING_LOADER);
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.clickDirectlyByDOM(NEXT_BUTTON_CHECKOUTPROCESS);
        operationalUtil.waitUntilElementDissapears(60,10,SPINNING_LOADER);
        try {
            operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        }catch (TimeoutException ex){
            ex.printStackTrace();
        }

        switch(paymentMethod) {
            case "Card":
                try{
                    CARD_PAYMENT_METHOD.click();
                    operationalUtil.waitForAttributeToBe(DIV_LOADING_MASK, "style", "display: none;");
                }catch (ElementClickInterceptedException e){
                    operationalUtil.clickDirectlyByDOM(CARD_PAYMENT_METHOD);
                }
                if(cardDetails.equals("new")){
                    operationalUtil.waitElementVisibility(INT_TIMEOUT_JQUERY_TO_DONE, IFRAME_CARD_NUMBER);
                    checkoutProcess.getDriver().switchTo().frame(IFRAME_CARD_NUMBER).findElement(By.xpath("//span[@class='InputContainer']//input")).sendKeys(CARD_NUMBER);
                    checkoutProcess.getDriver().switchTo().defaultContent();
                    checkoutProcess.getDriver().switchTo().frame(IFRAME_CARD_EXPIRATION_DATE).findElement(By.xpath("//span[@class='InputContainer']//input")).sendKeys(CARD_EXPIRATION_DATE);
                    checkoutProcess.getDriver().switchTo().defaultContent();
                    checkoutProcess.getDriver().switchTo().frame(IFRAME_CVC).findElement(By.xpath("//span[@class='InputContainer']//input")).sendKeys(CARD_CVC);
                    checkoutProcess.getDriver().switchTo().defaultContent();
                    break;

                } else {
                    operationalUtil.clickDirectlyByDOM(CARD_DETAILS_SAVED.get(0));
                    //CARD_DETAILS_SAVED.get(0).click();
                    operationalUtil.waitForAttributeToBe(DIV_LOADING_MASK, "style", "display: none;");
                    break;
                }

            case "Order":
                try{
                    ORDER_PAYMENT_METHOD.click();
                }catch (ElementClickInterceptedException e){
                    operationalUtil.clickDirectlyByDOM(CARD_PAYMENT_METHOD);
                }
                operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
                operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
                PURCHASE_ORDER_NUMBER_FIELD.sendKeys("123456789");
                break;
        }


    }
}
