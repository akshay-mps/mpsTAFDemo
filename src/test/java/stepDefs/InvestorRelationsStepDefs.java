package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.InvestorRelations;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pages.CommonElements.SEARCH_BAR;
import static pages.InvestorRelations.*;
import static utility.operationalUtils.parseDateFromWeb;
import static utility.operationalUtils.splitNewLineInString;

public class InvestorRelationsStepDefs {
    private InvestorRelations investorRelations;
    private Date date;
    private BaseTest bt = new BaseTest();


    public InvestorRelationsStepDefs() {
        WebDriver driver = bt.getDriver();
        this.investorRelations = new InvestorRelations(driver);
    }

    @Then("^I should see the search box for investors relations$")
    public void i_should_see_the_search_box_for_investors_relations() {
        assertTrue(SEARCH_BAR.isDisplayed());
    }

    @Then("^I should see the title with text \"([^\"]*)\"$")
    public void i_should_see_the_title_with_text(String title) {
        List<String> list = splitNewLineInString(DIV_IR_TITLE_TEXT.getText());
        assertEquals(list.get(0), title);
    }

    @Then("^I should see the description has some text$")
    public void i_should_see_the_description_has_some_text() {
        assertTrue(!DIV_IR_DESCRIPTION_TEXT.getText().equals(""));
    }

    @Then("^I should see the stock investor square with information$")
    public void i_should_see_the_stock_investor_square_with_information_and_date_no_older_than_days_before() {
        assertTrue(MPWR_STOCK_TILE.isDisplayed());
    }

    @Then("^I should see the tabs with titles \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_should_see_the_tabs_with_titles_and(String pressReleases,String eventsAndPresentations, String corporateGovernance, String financialAndFilings, String proxyInformation, String stockInformation, String contactUs) {
        assertEquals(pressReleases, A_IR_TAB_MENU_LIST.get(0).getText());
        assertEquals(eventsAndPresentations, A_IR_TAB_MENU_LIST.get(1).getText());
        assertEquals(corporateGovernance, A_IR_TAB_MENU_LIST.get(2).getText());
        assertEquals(financialAndFilings, A_IR_TAB_MENU_LIST.get(3).getText());
        assertEquals(proxyInformation, A_IR_TAB_MENU_LIST.get(4).getText());
        assertEquals(stockInformation, A_IR_TAB_MENU_LIST.get(5).getText());
        assertEquals(contactUs, A_IR_TAB_MENU_LIST.get(6).getText());

    }

    @Then("^I should see the column with title text \"([^\"]*)\" and View All button with text \"([^\"]*)\"$")
    public void i_should_see_the_column_with_title_text_and_View_All_button_with_text(String titleText, String buttonText) {
        if(titleText.equals("Press Releases")){
            assertEquals(titleText, DIV_IR_PRESS_RELEASES_TITLE_TEXT.getText());
            assertEquals(buttonText, DIV_IR_PRESS_RELEASES_VIEW_ALL_BUTTON_TEXT.getText());
        }else{
            assertEquals(titleText, DIV_IR_EVENTS_PRESENTATIONS_TITLE_TEXT.getText());
            assertEquals(buttonText, DIV_IR_EVENTS_PRESENTATIONS_VIEW_ALL_BUTTON_TEXT.getText());
        }

    }

    @Then("^I should see the title in Events & presentations page with text \"([^\"]*)\"$")
    public void i_should_see_the_title_in_Events_presentations_page_with_text(String title) {
        assertTrue(H1_TITLE_IR_EP.isDisplayed());
        assertTrue(H1_TITLE_IR_EP.getText().equals(title));
    }

    @Then("^I should see the breadcrumbs in Investor Relations page$")
    public void i_should_see_the_breadcrumbs_in_Investor_Relations_page() {
        assertTrue(!BREADCRUMBS_IR.getText().equals(""));
    }

    @Then("^I should see the region for events and presentations information including sections with text \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_should_see_the_region_for_events_and_presentations_information_including_sections_with_text_and(String presentationsTitle, String upcomingEventsTitle, String archivedEventsTitle) {
        assertTrue(DIV_MAIN_REGION_IR_EP.isDisplayed());
        List <WebElement> h2_list = DIV_MAIN_REGION_IR_EP.findElements(By.tagName("h2"));
        assertEquals(presentationsTitle, h2_list.get(0).getText());
        assertEquals(upcomingEventsTitle, h2_list.get(1).getText());
        assertEquals(archivedEventsTitle, h2_list.get(2).getText());
    }

    @Then("^I should see only older dates than the actual date in the Archived Events section$")
    public void i_should_see_only_older_dates_than_the_actual_date_in_the_Archived_Events_section() {
        for (WebElement element: DIV_ARCHIVED_EVENTS_DATES_IR_EP_LIST) {
            assertTrue(date.after(parseDateFromWeb(element.getText())));
        }
    }

    @Then("^I should see newer dates than the actual date or a message for upcoming events with text \"([^\"]*)\" in the Upcoming Events section$")
    public void i_should_see_newer_dates_than_the_actual_date_or_a_message_for_upcoming_events_with_text_in_the_Upcoming_Events_section(String messageText) {
        if(DIV_UPCOMING_EVENTS_DATES_IR_EP_LIST.size()>0) {
            for (WebElement element : DIV_UPCOMING_EVENTS_DATES_IR_EP_LIST) {
                assertTrue(date.before(parseDateFromWeb(element.getText())));
            }
        }else{
            assertTrue(DIV_UPCOMING_EVENTS_MORE_EVENTS_COMING_SOON_TEXT_IR_EP.isDisplayed());
            assertTrue(DIV_UPCOMING_EVENTS_MORE_EVENTS_COMING_SOON_TEXT_IR_EP.getText().equals(messageText));
        }
    }
}
