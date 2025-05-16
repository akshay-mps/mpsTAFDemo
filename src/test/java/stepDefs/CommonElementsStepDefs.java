package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.CommonElements;
import utility.DriverManager;
import utility.operationalUtils;

import java.io.IOException;

import static Resources.ProjectConstants.*;
import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.*;
import static pages.CommonElements.*;

public class CommonElementsStepDefs{

    WebDriver driver;
    operationalUtils operationalUtil;
    CommonElements commonElements;

    public CommonElementsStepDefs() {
        this.driver = DriverManager.getDriver();
        this.operationalUtil = new operationalUtils(driver);
        this.commonElements = new CommonElements(driver);
    }

    public static String PRODUCT;
    public String emailForgotPassword;
    public String token;
    @Given("^I am on MPS website$")
    public void i_am_on_MPS_website() { driver.get("https://sandbox.monolithicpower.com/");
    }

    @When("^I click on the cart button$")
    public void i_click_on_the_cart_button() {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        try {
            operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }catch (AssertionError e){

        }
        operationalUtil.interactWebElementDOMJavaScript(CART_BUTTON, "click");
    }


    @Given("^I visit MPS redirect page \"([^\"]*)\"$")
    public void i_am_on_MPS_page(String OriginalURL) throws IOException {
        OkHttpClient client= new OkHttpClient.Builder().followRedirects(false).build();
        Request request = new Request.Builder().url(OriginalURL).head().build();
        Response response= client.newCall(request).execute();
        response.close();
    }

    @Then("^I verify the HTTP status code is \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_verify_http_status_code(int expectedStatusCode, String OriginalURL) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().followRedirects(false).build();

        Request request= new Request.Builder().url(OriginalURL).head().build();

        Response response= client.newCall(request).execute();
        Assert.assertEquals(expectedStatusCode, response.code());
        response.close();
    }

    @Then("^I verify the page redirects to \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_verify_the_page_redirection(String expectedRedirectURL, String OriginalURL) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().followRedirects(true).build();

        Request request= new Request.Builder().url(OriginalURL).head().build();

        Response response= client.newCall(request).execute();

        if (response.code()==301) {
            String actualRedirectUrl= response.header("Location");

        Assert.assertEquals(expectedRedirectURL, actualRedirectUrl);
        }
        response.close();
    }


    @Given("^I am on MPS website with domain \"([^\"]*)\"$")
    public void i_am_on_MPS_website_with_domain(String domain) {
        operationalUtil.getURLwithDomain("", domain);
        try {
            operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }catch (AssertionError e){
        }
    }

    @Given("^I am on MPS website \"([^\"]*)\" for the product \"([^\"]*)\"$")
    public void i_am_on_MPS_website_for_the_product(String locale, String product) {
        if (product.equals("")) {
            if(locale.equals("cn")){
                i_am_on_MPS_website_with_domain(locale);
            }else {
                operationalUtil.getURL("/" + locale + "/");
            }
        }else{
            operationalUtil.getURL("/" + locale + "/" + product + ".html");
        }
        try {
            operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }catch (AssertionError e){
        }
    }

    @Given("^I am on MPS website \"([^\"]*)\" environment \"([^\"]*)\" for the product \"([^\"]*)\"$")
    public void i_am_on_MPS_website_environment_for_the_product(String locale, String env, String product) {

        if (product.equals("")) {
            operationalUtil.getURLWithEnv(env, "/" + locale + "/");
        }else{
            if(product.startsWith("PARAMTEST_")) {
                operationalUtil.getURLWithEnv(env, "/" + locale + "/" + product.substring(10, product.length()));
            } else {
                operationalUtil.getURLWithEnv(env, "/" + locale + "/" + product + ".html");
            }
        }
        try {
            operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }catch (AssertionError e){

        }

    }

    @Given("I am on MPS specific URL {string}")
    public void i_am_on_MPS_specific_URL(String url) {
        if(url.contains("investor-relations.html")){
            operationalUtil.getSpecificURL(url+"?time="+currentTimeMillis());
        }else{
            operationalUtil.getSpecificURL(url);
        }
    }

    @When("^I enter \"([^\"]*)\" in the search box$")
    public void i_enter_in_the_search_box(String product) throws  InterruptedException{
        Thread.sleep(5000);
        SEARCH_BAR.sendKeys(product);
        this.PRODUCT = product;

    }

    @When("^I press search button$")
    public void i_press_search_button() {
        operationalUtil.deleteWebElementAttribute(SEARCH_BUTTON_BAR, "disabled");
        SEARCH_BUTTON_BAR.click();
    }

    @When("^I mouseover in \"([^\"]*)\"$")
    public void i_mouseover_in(String menu) {
        Actions action = new Actions(DriverManager.getDriver());
        switch(menu) {
            case "Products":
                action.moveToElement(HEADER_CONTAINER_MENU_OPTIONS.get(0)).build().perform();
                break;
            case "Applications":
                action.moveToElement(HEADER_CONTAINER_MENU_OPTIONS.get(1)).build().perform();
                break;
            case "Design":
                action.moveToElement(HEADER_CONTAINER_MENU_OPTIONS.get(2)).build().perform();
                break;
            case "Support":
                action.moveToElement(HEADER_CONTAINER_MENU_OPTIONS.get(3)).build().perform();
                break;
            case "Learning Center":
                action.moveToElement(HEADER_CONTAINER_MENU_OPTIONS.get(5)).build().perform();
                break;
            case "About MPS":
                action.moveToElement(HEADER_CONTAINER_MENU_OPTIONS.get(6)).build().perform();
                break;
            case "Contact":
                action.moveToElement(HEADER_CONTAINER_MENU_OPTIONS.get(7)).build().perform();
                break;
        }

    }

    @Then("^I should see the submenu for \"([^\"]*)\"$")
    public void i_should_see_the_submenu_for(String menu) {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        switch(menu) {
            case "Products":
                assertTrue(HEADER_CONTAINER_MENU_OPTIONS.get(0).findElements(By.className("mmm-column")).size() == 5);
                break;
            case "Applications":
                assertTrue(HEADER_CONTAINER_MENU_OPTIONS.get(1).findElements(By.className("mmm-column")).size() == 4);
                break;
            case "Design":
                assertTrue(HEADER_CONTAINER_MENU_OPTIONS.get(2).findElements(By.className("mmm-column")).size() == 5);
                break;
            case "Support":
                assertTrue(HEADER_CONTAINER_MENU_OPTIONS.get(3).findElements(By.className("mmm-column")).size() == 3);
                break;
            case "Learning Center":
                assertTrue(HEADER_CONTAINER_MENU_OPTIONS.get(5).findElements(By.className("mmm-column")).size() == 0);
                break;
            case "About MPS":
                assertTrue(HEADER_CONTAINER_MENU_OPTIONS.get(6).findElements(By.className("mmm-column")).size() == 3);
                break;
            case "Contact":
                assertTrue(HEADER_CONTAINER_MENU_OPTIONS.get(7).isDisplayed() && HEADER_CONTAINER_MENU_OPTIONS.get(7).getText().equals("Contact"));
                break;
        }

    }



    @Then("^I should see the cart summary window$")
    public void i_should_see_the_cart_summary_window() {
        operationalUtil.waitElementVisibility(5, CART_SUMMARY_WINDOWN);
        assertTrue(CART_SUMMARY_WINDOWN.isDisplayed());

    }

    @When("^I click on the x for closing the cart summary window$")
    public void i_click_on_the_x_for_closing_the_cart_summary_window() {
        CART_SUMMARY_CLOSE_BUTTON.click();
    }

    @Then("^I should not see the cart summary window$")
    public void i_should_not_see_the_cart_summary_window() {
        assertFalse(CART_SUMMARY_CLOSE_BUTTON.isDisplayed());
    }

    @When("^I click on the language button$")
    public void i_click_on_the_language_button() {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        LANGUAGE_BUTTON.click();
    }

    @Then("^I should see the languages submenu$")
    public void i_should_see_the_languages_submenu() {
        assertTrue(LANGUAGE_DROPDOWN.get(1).isDisplayed());
    }

    @Then("^I should not see the languages submenu$")
    public void i_should_not_see_the_languages_submenu() {
        assertFalse(LANGUAGE_DROPDOWN.get(1).isDisplayed());
    }

    @When("^I click on the login button$")
    public void i_click_on_the_login_button() {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        LOGIN_BUTTON_HOME.click();
    }

    @Then("^I should see the login window$")
    public void i_should_see_the_login_window() {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitElementVisibility(5,LOGIN_WINDOW);
        assertTrue(LOGIN_WINDOW.isDisplayed());
    }

    @When("^I click on the x for closing the login window$")
    public void i_click_on_the_x_for_closing_the_login_window() {
        LOGIN_WINDOW_CLOSE_BUTTON.click();
    }

    @Then("^I should not see the login window$")
    public void i_should_not_see_the_login_window() {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitElementInvisibility(4,LOGIN_WINDOW);
        assertFalse(LOGIN_WINDOW.isDisplayed());
    }

    @When("^I click on the sign up button$")
    public void i_click_on_the_sign_up_button() {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.clickDirectlyByDOM(SIGN_UP_BUTTON);
    }

    @Then("^I should see the sign up window$")
    public void i_should_see_the_sign_up_window() {
        operationalUtil.waitElementVisibility(5,SIGN_UP_WINDOW);
        assertTrue(SIGN_UP_WINDOW.isDisplayed());
    }

    @When("^I click on the x for closing the sign up window$")
    public void i_click_on_the_x_for_closing_the_sign_up_window() {
        SIGN_UP_CLOSE_BUTTON.click();
    }

    @Then("^I should not see the sign up window$")
    public void i_should_not_see_the_sign_up_window() {
        operationalUtil.waitElementInvisibility(4,SIGN_UP_WINDOW);
        assertFalse(SIGN_UP_WINDOW.isDisplayed());

    }

    @Then("^I should see the quantity of \"(\\d+)\" in the Cart summary$")
    public void i_should_see_the_quantity_of_in_the_Cart_summary(int quantity) throws InterruptedException{
        operationalUtil.waitForTextDifferentToZero(CART_QUANTITY_HEADER.get(1));
        assertEquals(Integer.valueOf(CART_QUANTITY_HEADER.get(1).getText()), (Integer) quantity);
        i_click_on_the_cart_button();

        if(quantity == 0){
            assertEquals(STR_EMPTY_CART_MESSAGE_WINDOWS_HEADER, CART_TEXT_EMPTY_CART_SUMMARY_WINDOW.getText());
        }else {
            operationalUtil.waitElementVisibility(5, CART_SUMMARY_WINDOWN);
            assertEquals(Integer.valueOf(CART_QUANTITY_WINDOW.getAttribute("value")), (Integer) quantity);
        }
    }

    @When("^I click the language \"([^\"]*)\"$")
    public void i_click_the_language(String languageLocale) {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        switch(languageLocale) {
            case "CN":
                operationalUtil.waitElementVisibility(2, LANGUAGE_DROPDOWN.get(1));
                LANGUAGE_CN.click();
                break;
            case "JP":
                operationalUtil.waitElementVisibility(3, LANGUAGE_DROPDOWN.get(1));
                LANGUAGE_JP.click();
                break;
            case "EN":
                operationalUtil.waitElementVisibility(3, LANGUAGE_DROPDOWN.get(1));
                LANGUAGE_EN.click();
                break;
        }

    }

    @Then("^I should see the header \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" in the \"([^\"]*)\" language$")
    public void i_should_see_the_header_and_in_the_language(String product, String applications, String design, String support, String LearningCenter, String AboutMPS, String contact, String cart, String languageLocale, String logIn, String signUp, String languageSelected) {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);

                    assertEquals(product, HEADER_CONTAINER_MENU_OPTIONS.get(0).getText());
                    assertEquals(applications, HEADER_CONTAINER_MENU_OPTIONS.get(1).getText());
                    assertEquals(design, HEADER_CONTAINER_MENU_OPTIONS.get(2).getText());
                    assertEquals(support, HEADER_CONTAINER_MENU_OPTIONS.get(3).getText());
               if(languageSelected.equals("CN")){
                   assertEquals(AboutMPS, HEADER_CONTAINER_MENU_OPTIONS.get(7).getText());
                   assertEquals(contact, HEADER_CONTAINER_MENU_OPTIONS.get(8).getText());
               }else {
                   assertEquals(AboutMPS, HEADER_CONTAINER_MENU_OPTIONS.get(6).getText());
                   assertEquals(contact, HEADER_CONTAINER_MENU_OPTIONS.get(7).getText());
               }
                    assertEquals(LearningCenter, HEADER_CONTAINER_MENU_OPTIONS.get(5).getText());
                    assertEquals(cart, CART_TEXT_HEADER.get(1).getText());
                    assertEquals(languageLocale, LANGUAGE_TEXT_HEADER.getText());
                    assertEquals(logIn, LOGIN_BUTTON_HOME.getText());
                    assertEquals(signUp, SIGN_UP_BUTTON.getText());
    }

    @When("^I click on MPS logo in the header$")
    public void i_click_on_MPS_logo_in_the_header() {
        MPS_LOGO_HEADER.click();
    }

    @Then("^I should go to MPS homepage \"([^\"]*)\"$")
    public void i_should_go_to_MPS_homepage(String language) {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        if(language.equals("cn")){
            assertEquals(STR_TITLE_MPS_HOMEPAGE_CN, DriverManager.getDriver().getTitle());
        }else{
            assertEquals(STR_TITLE_MPS_HOMEPAGE, DriverManager.getDriver().getTitle());
        }
    }

    @When("^I click on Forgot password link$")
    public void i_click_on_Forgot_password_link() {
        operationalUtil.waitElementVisibility(3, FORGOT_PASSWORD_DIV_ENTIRE_PAGE_FOR_INTERACTION);
        operationalUtil.waitElementClickability(3, FORGOT_PASSWORD_LINK);
        operationalUtil.waitElementInViewPort(3, FORGOT_PASSWORD_LINK);
        FORGOT_PASSWORD_LINK.click();
    }

    @Then("^I should see the Forgot Password window$")
    public void i_should_see_the_Forgot_Password_window() {
        assertTrue(FORGOT_PASSWORD_DIV.isDisplayed());
    }

    @When("^I type \"([^\"]*)\" in the email field$")
    public void i_type_in_the_email_field(String email) {
        emailForgotPassword = email;
        operationalUtil.waitElementClickability(3, FORGOT_PASSWORD_EMAIL_FIELD);
        FORGOT_PASSWORD_EMAIL_FIELD.sendKeys(email);
    }

    @When("^I click in Submit button$")
    public void i_click_in_Submit_button() {
        FORGOT_PASSWORD_SUBMIT_BUTTON_WINDOW.click();
    }

    @Then("^I should see the message$")
    public void i_should_see_the_message() {
        operationalUtil.waitElementPresence(40, "//div[@class='message-success success message']");
        assertTrue(FORGOT_PASSWORD_SUCCESS_MESSAGE.getText().contains(emailForgotPassword));
    }

    @And("^I click in Reset My Password button$")
    public void iClickInResetMyPasswordButton() {
        FORGOT_PASSWORD_RESET_MY_PASSWORD_BUTTON_FULL_PAGE.click();
    }

    @And("^I should see the Forgot Password form$")
    public void iShouldSeeTheForgotPasswordForm() {
        assertTrue(FORGOT_PASSWORD_DIV_FULL_PAGE.isDisplayed());
    }

    @When("^I type \"([^\"]*)\" in the email field for full page$")
    public void iTypeInTheEmailFieldForFullPage(String email) {
        emailForgotPassword = email;
        FORGOT_PASSWORD_EMAIL_FIELD_FULL_PAGE.sendKeys(email);
    }

    @Then("^I should see the message for full page$")
    public void iShouldSeeTheMessageForFullPage() {
        operationalUtil.waitElementVisibility(10, FORGOT_PASSWORD_SUCCESS_MESSAGE_FULL_PAGE);
        assertTrue(FORGOT_PASSWORD_SUCCESS_MESSAGE_FULL_PAGE.getText().contains(emailForgotPassword));
    }

    @Then("^I should see the footer$")
    public void i_should_see_the_footer() {
        assertTrue(FOOTER_PAGE.isDisplayed());
        assertTrue(FOOTER_CONTAINER.isDisplayed());
    }

    @Given("^I type \"([^\"]*)\" in the email field for login$")
    public void i_type_in_the_email_field_for_login(String email) {
        LOG_IN_WINDOW_POP_UP_EMAIL_FIELD.sendKeys(email);
    }


    @Given("^I type \"([^\"]*)\" in the password field for login$")
    public void i_type_in_the_password_field_for_login(String password) {
        LOG_IN_WINDOW_POP_UP_PASSWORD_FIELD.sendKeys(password);
    }

    @Given("^I click in Login button in the window pop up$")
    public void i_click_in_Login_button_in_the_window_pop_up() {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        LOGIN_BUTTON_POPUP.click();
    }
    @Then("^Verify popup is shown \"([^\"]*)\" on page$")
    public void i_verify_the_verification_popup_is_shown(String popup){
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        if (popup.toLowerCase().equals("true")) {
            operationalUtil.waitElementVisibility(10, REVERIFICATION_POP_UP_WINDOW);
            REVERIFICATION_POP_UP_WINDOW.getText().contains("verification process has been sent to your email");
        }
        else {
            operationalUtil.waitElementVisibility(10, USER_INFO);
            assertTrue(USER_INFO.isDisplayed());
        }

    }

    @Given("^I click in name account button$")
    public void i_click_in_name_account_button() {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitElementClickability(5, USERNAME_HEADER_CIRCLE_BUTTON);
        USERNAME_HEADER_CIRCLE_BUTTON.click();
    }

    @Given("^I click in option \"([^\"]*)\" from name account menu$")
    public void i_click_in_option_from_name_account_menu(String option) {
        operationalUtil.waitElementClickability(3, MENU_ACCOUNT_USERNAME_LIST.get(0));
        for (WebElement element: MENU_ACCOUNT_USERNAME_LIST) {
            if(element.getText().equals(option)){
                element.click();
                break;
            }
        }
        try {
            operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }catch (AssertionError e){

        }
    }

    @Then("^I should see the required fields highlighted for email with text \"([^\"]*)\"$")
    public void iShouldSeeTheRequiredFieldsHighlightedForEmailWithText(String requiredFieldText) throws Throwable {
        assertEquals("true", LOG_IN_WINDOW_POP_UP_EMAIL_FIELD.getAttribute("aria-required"));
        assertEquals(requiredFieldText, REQUIRED_MESSAGE_LOGIN_FIELD_EMAIL.getText());
    }

    @Then("^I should see the required fields highlighted for password with text \"([^\"]*)\"$")
    public void iShouldSeeTheRequiredFieldsHighlightedForPasswordWithText(String requiredFieldText) throws Throwable {
        assertEquals("true", LOG_IN_WINDOW_POP_UP_PASSWORD_FIELD.getAttribute("aria-required"));
        assertEquals(requiredFieldText, REQUIRED_MESSAGE_LOGIN_FIELD_PASSWORD.getText());
    }

    @And("^I click in Go to checkout button$")
    public void iClickInGoToCheckoutButton() {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitElementVisibility(4, IMAGE_THUMB_CART);
        operationalUtil.waitElementClickability(3, CHECKOUT_BUTTON_CART);
        operationalUtil.waitElementInViewPort(3, CHECKOUT_BUTTON_CART);
        CHECKOUT_BUTTON_CART.click();
    }

    @And("^I click on the sign up link at the bottom$")
    public void iClickOnTheSignUpLinkAtTheBottom() {
        operationalUtil.waitElementClickability(3, SIGN_UP_LINK);
        SIGN_UP_LINK.click();
    }

    @And("^I type \"([^\"]*)\" in the email field for sign up$")
    public void iTypeInTheEmailFieldForSignUp(String email) throws Throwable{
        if(email.equals("test@example.com")){
            iDeleteTheUser(email);
        }
        operationalUtil.waitElementVisibility(3, SIGN_UP_WINDOW_POP_UP_EMAIL_FIELD);
        SIGN_UP_WINDOW_POP_UP_EMAIL_FIELD.sendKeys(email);
    }

    @And("^I type \"([^\"]*)\" in the password field for sign up$")
    public void iTypeInThePasswordFieldForSignUp(String pwd) {
        SIGN_UP_WINDOW_POP_UP_PASSWORD_FIELD.sendKeys(pwd);
    }

    @And("^I type \"([^\"]*)\" in the confirm password field for sign up$")
    public void iTypeInTheConfirmPasswordFieldForSignUp(String pwd) {
        SIGN_UP_WINDOW_POP_UP_PASSWORD_CONFIRMATION__FIELD.sendKeys(pwd);

    }

    @And("^I click in Create basic account button$")
    public void iClickInCreateBasicAccountButton() {
        SIGN_UP_BUTTON_POPUP_CREATE_BASIC_ACCOUNT.click();
    }
    @And("^I verify the existence \"([^\"]*)\" of the user \"([^\"]*)\"$")
    public void iVerifyTheExistenceOfTheUser(String exist, String user)  {

        this.token = operationalUtil.getTokenAPI();
        String user_at_Replaced = user.replace("@", "%40");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://qa01.mps-aws.com/rest/V1/MpsQA/customer/count/"+ user_at_Replaced +"")
                .method("GET", null)
                .addHeader("Authorization", "Bearer "+ token +"")
                .build();

        try{
            Response response = client.newCall(request).execute();

            switch (exist){
                case "yes":
                    assertTrue(response.peekBody(1).string().equals("1"));
                    break;

                case "no":
                    assertTrue(response.peekBody(1).string().equals("0"));
                    break;
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @And("^I delete the user \"([^\"]*)\"$")
    public void iDeleteTheUser(String user) throws Throwable {

        this.token = operationalUtil.getTokenAPI();
        String user_at_Replaced = user.replace("@", "%40");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.Companion.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://qa01.mps-aws.com/rest/V1/MpsQA/customer/emailStatus/"+ user_at_Replaced +"")
                .method("DELETE", body)
                .addHeader("Authorization", "Bearer "+ token +"")
                .build();


            Response response = client.newCall(request).execute();


    }

    @And("^I verify the presence of the modal basic account created message$")
    public void iVerifyThePresenceOfTheModalBasicAccountCreatedMessage() {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitElementVisibility(24, MODAL_POPUP_WINDOW_BASIC_ACCOUNT_CREATED);
        assertTrue(MODAL_POPUP_WINDOW_BASIC_ACCOUNT_CREATED.isDisplayed());
        assertTrue(!MODAL_POPUP_WINDOW_BASIC_ACCOUNT_CREATED.getText().equals(""));
    }

    @And("^I verify the account status \"([^\"]*)\" of the user \"([^\"]*)\"$")
    public void iVerifyTheAccountStatusOfTheUser(String status, String user) throws Throwable {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);

        this.token = operationalUtil.getTokenAPI();
        String user_at_Replaced = user.replace("@", "%40");

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://qa01.mps-aws.com/rest/V1/MpsQA/customer/type/"+ user_at_Replaced +"")
                .method("GET", null)
                .addHeader("Authorization", "Bearer "+ token +"")
                .build();

        try{
            Response response = client.newCall(request).execute();

            assertTrue((response.body().string().replace("\"", "")).toLowerCase().equals(status));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Then("^I should see the upgrade pop up and click Upgrade to MyMPS\\+ button$")
    public void iShouldSeeTheUpgradePopUpAndClickUpgradeToMyMPSButton() {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        try {
            operationalUtil.waitElementVisibility(24, UPGRADE_MPS_PLUS_POP_UP_WINDOW);
        }
        catch(org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.TimeoutException newException)
        {
            newException.printStackTrace();
        }

        assertTrue(UPGRADE_MPS_PLUS_POP_UP_WINDOW.isDisplayed());
        UPGRADE_MPS_PLUS_POP_UP_BUTTON.click();
    }

    @And("^I fill the upgrade form with ProjectInfo \"([^\"]*)\", FirstName \"([^\"]*)\", LastName \"([^\"]*)\", EMail \"([^\"]*)\", Company \"([^\"]*)\", and Country \"([^\"]*)\"$")
    public void iFillTheUpgradeFormWithJobTitleCompanyProvinceCityAndZip(String ProjectInfo, String FirstName, String LastName, String EMail, String Company, String Country) throws Throwable {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitElementVisibility(24, UPGRADE_MPS_PLUS_FORM);

        /* Project_Info */
        UPGRADE_MPS_PLUS_FORM_FIELDS_INFO.sendKeys(ProjectInfo);
        /* FirstName */
        UPGRADE_MPS_PLUS_FORM_FIELDS_FNAME.sendKeys(FirstName);
        /* LastName */
        UPGRADE_MPS_PLUS_FORM_FIELDS_LNAME.sendKeys(LastName);
        /* EMail */
        UPGRADE_MPS_PLUS_FORM_FIELDS_EMAIL.sendKeys(EMail);
        /* CompanyName */
        UPGRADE_MPS_PLUS_FORM_FIELDS_COMPANY.sendKeys(Company);
        /* Country */
        Select countryName = new Select(UPGRADE_MPS_PLUS_FORM_FIELDS_COUNTRY);
        countryName.selectByVisibleText(Country);
    }

    @And("^I click in Create your myMPS\\+ account button$")
    public void iClickInCreateYourMyMPSAccountButton() {
        //Submit form button
        UPGRADE_MPS_PLUS_FORM_SUBMIT_BUTTON.click();
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
    }

    @Then("^I should see the window pop up information after the request$")
    public void iShouldSeeTheWindowPopUpInformationAfterTheRequest() throws InterruptedException {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        try{
            assertTrue(UPGRADE_MPS_PLUS_POP_UP_WINDOW_AFTER_REQUEST.isDisplayed());
        }catch (AssertionError ex){
            assertTrue(DriverManager.getDriver().findElement(By.xpath("//div[@class='modals-wrapper']//aside[3]//div[@class='modal-inner-wrap']")).isDisplayed());
        }

    }

    @And("^I approve the upgrade request for the user \"([^\"]*)\"$")
    public void iApproveTheUpgradeRequestForTheUser(String user) throws Throwable {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);

        this.token = operationalUtil.getTokenAPI();;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.Companion.create(mediaType, "{\n    \"emailAddress\": \""+user+"\"\n}");
        Request request = new Request.Builder()
                .url("https://qa01.mps-aws.com/rest/V1/MpsQA/customer/approveMpsPlus")
                .method("PUT", body)
                .addHeader("Authorization", "Bearer "+ token +"")
                .addHeader("Content-Type", "application/json")
                .build();
        try{
            Response response = client.newCall(request).execute();

            assertTrue((response.body().string().replace("\"", "")).toLowerCase().equals("mymps+"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @And("^I downgrade the user \"([^\"]*)\" to a basic account and delete de email record$")
    public void iDowngradeTheUserToABasicAccountAndDeleteDeEmailRecord(String user) throws Throwable {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);

        this.token = operationalUtil.getTokenAPI();;

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = "{\n    \"emailAddress\": \"" + user + "\",\n    \"userType\": \"MyMPS\"\n}";
        RequestBody body = RequestBody.create(jsonBody, mediaType);
        Request request = new Request.Builder()
                .url("https://qa01.mps-aws.com/rest/V1/MpsQA/customer/changeUserType")
                .method("PUT", body)
                .addHeader("Authorization", "Bearer "+ token +"")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        deleteEmailApproval(user);

    }

    public void deleteEmailApproval(String user) throws Throwable {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);

        this.token = operationalUtil.getTokenAPI();

        String user_at_Replaced = user.replace("@", "%40");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.Companion.create("", mediaType);
        Request request = new Request.Builder()
                .url("https://qa01.mps-aws.com/rest/V1/MpsQA/customer/emailStatus/"+user_at_Replaced+"")
                .method("DELETE", body)
                .addHeader("Authorization", "Bearer "+ token +"")
                .build();
        Response response = client.newCall(request).execute();
    }

    @Then("^I should see the cookie banner$")
    public void iShouldSeeTheCookieBanner() {
        try{
            if(!DriverManager.getDriver().manage().getCookieNamed("lalala").getValue().equals("")){
                operationalUtil.waitElementVisibility(INT_TIMEOUT_JQUERY_TO_DONE, DIV_COOKIE_CONSENT_BANNER);
                assertTrue(DIV_COOKIE_CONSENT_BANNER.isDisplayed());
                assertFalse(DIV_COOKIE_CONSENT_BANNER.getText().equals(""));
            }
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}
