package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Articles;
import utility.DriverManager;

import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class ArticlesStepDefs{

    private WebDriver driver ;
    private Articles articles;

    public ArticlesStepDefs() {
        this.driver = DriverManager.getDriver();
        this.articles = new Articles(driver);
    }

    @Then("^I should see the articles table section$")
    public void iShouldSeeTheArticlesTableSection() {
        assertTrue(articles.MAIN_DIV_TABLE_ARTICLES.isDisplayed());  // Access via articles instance
    }

    @And("^I should see all the links with its a href tag pointing to the current environment$")
    public void iShouldSeeAllTheLinksWithItsAHrefTagPointingToTheCurrentEnvironment() {
        for (WebElement w : articles.A_TD_TABLE_LEFT_COLUMN_ARTICLES) {  // Access via articles instance
            assertFalse(w.getAttribute("href").equals(""));
        }
    }
}
