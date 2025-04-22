package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
//import org.springframework.beans.factory.annotation.Autowired;
import pages.Account;
import utility.operationalUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pages.Account.*;
import static pages.CommonElements.BODY_CONTAINER;

public class AccountStepDefs {
    private WebDriver driver;
//    @Autowired
    private Account accountStep;
//    @Autowired
    private operationalUtils operationalUtil;

    BaseTest bt = new BaseTest();

    public AccountStepDefs() {
        this.driver = bt.getDriver();
//        operationalUtil = new operationalUtils(driver);
    }

    @Then("^I should see the title with text \"([^\"]*)\" and information in the right section$")
    public void i_should_see_the_title_with_text_and_information_in_the_right_section(String title) {
        operationalUtil.waitElementVisibility(3, TITLE_ACCOUNT_SECTION);
        assertEquals(title, TITLE_ACCOUNT_SECTION.getText());
        assertTrue(DIV_MAIN_ACCOUNT_SECTION.isDisplayed());
        assertTrue(!DIV_MAIN_ACCOUNT_SECTION.getText().equals(""));
    }

    @Then("^I click in option \"([^\"]*)\" from left menu$")
    public void i_click_in_option_from_left_menu(String option) {
        for (WebElement element: LI_ITEMS_LEFT_MENU_ACCOUNT_LIST) {
            if(element.getText().equals(option)){
                element.click();
                break;
            }
        }
        try {
            operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }catch (AssertionError e){
            //accountStep.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }
    }
    @When("^I enter \"([^\"]*)\" in Firstname field$")
    public void i_enter_in_firstname_field(String firstName) {
        STUDENT_FIRSTNAME.sendKeys(firstName);
    }
    @And("^I enter \"([^\"]*)\" in Lastname field$")
    public void i_enter_in_lastname_field(String lastName) {
        STUDENT_LASTNAME.sendKeys(lastName);
    }
    @And("^I enter \"([^\"]*)\" in Email field$")
    public void i_enter_in_email_field(String email) {
        STUDENT_EMAIL.sendKeys(email);
    }
    @And("^I enter \"([^\"]*)\" in Password field$")
    public void i_enter_in_password_field(String password) {
        STUDENT_PASSWORD.sendKeys("oaks1984!");
    }
    @And("^I select \"([^\"]*)\" in major field$")
    public void i_select_in_major_field(String major) {
        Select majorSub = new Select(STUDENT_MAJOR);
        majorSub.selectByVisibleText(major);
    }
    @And("^I enter \"([^\"]*)\" in university field$")
    public void i_enter_in_university_field(String university) {
        STUDENT_UNIVERSITY.sendKeys("Barcelona Diploma Universidad");
    }
    @And("^I select \"([^\"]*)\" in country field$")
    public void i_select_in_country_field(String country) {
        Select studentCountry = new Select(STUDENT_COUNTRY);
        studentCountry.selectByVisibleText(country);
    }
    @And("^I select \"([^\"]*)\" in graduation field$")
    public void i_select_in_graduation_field(String graduationDate) {
        STUDENT_GRADAUTION.click();
        Select year= new Select(STUDENT_GRADUATION_YEAR);
        year.selectByVisibleText(graduationDate.substring(6,10));
        Select month= new Select(STUDENT_GRADUATION_MONTH);
        month.selectByIndex(3);
        STUDENT_GRADUATION_DAY.click();
    }

    @And("^I subscribe to the newsletter$")
    public void i_click_on_subscribe_button() {
        SUBSCRIBE_LETTER.click();
    }

    @And("^I click on Submit button$")
    public void i_click_on_submit_button() {
        STUDENT_FORM_SUBMIT.click();
    }

    @Then("^I should see the successful account creation message$")
    public void i_should_see_the_successful_account_creation_message() {
        operationalUtil.waitElementVisibility(5, STUDENT_ACCOUNT_MESSAGE);
        assertTrue(STUDENT_ACCOUNT_MESSAGE.isDisplayed());
       }
}
