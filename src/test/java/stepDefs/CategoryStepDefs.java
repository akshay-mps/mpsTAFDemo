package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import utility.DriverManager;
import utility.operationalUtils;

import java.util.List;

import static Resources.ProjectConstants.INT_TIMEOUT_JQUERY_TO_DONE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pages.CategoryPage.H1_TITLE_CATEGORY;
import static pages.CategoryPage.MAIN_DIV_CATEGORY_TILES;

public class CategoryStepDefs {

    WebDriver driver;
    public List<WebElement> listCategoryTiles;
    public CategoryPage category;
    public operationalUtils operationalUtil;

    public CategoryStepDefs() {
        this.driver = DriverManager.getDriver();
        this.category = new CategoryPage(driver);
        this.operationalUtil = new operationalUtils(driver);
    }
    @When("^I go to category tile section$")
    public void iGoToCategoryTileSection() {
        operationalUtil.moveToElementAction(MAIN_DIV_CATEGORY_TILES);
    }

    @Then("^I should see category tiles$")
    public void iShouldSeeCategoryTiles() {
        listCategoryTiles = category.getDriver().findElements(By.className("category-tile"));
        assertTrue(listCategoryTiles.size()>0);
    }

    @When("^I click in show more button in category tile for the category \"([^\"]*)\"$")
    public void iClickInShowMoreButtonInCategoryTileForTheCategory(String categoryTitle) {
        category.getDriver().findElement(By.xpath("//div[@class='category-tile-header']//a//span[contains(text(),'"+ categoryTitle +"')]//parent::a//parent::div//span[@class='category-tile-expand-button category-tile-show-more']")).click();

    }

    @Then("^It should expand and show the list of items in category \"([^\"]*)\"$")
    public void itShouldExpandAndShowTheListOfItemsInCategory(String categoryTitle) {
        List<WebElement> itemsShowMoreCategory = category.getDriver().findElements(By.xpath("//div[@class='category-tile-header']//a//span[contains(text(),'"+ categoryTitle +"')]//parent::a//parent::div//parent::div[contains(@class,'category-tile-open')]//div[@class='category-tile-body']"));
        assertTrue(itemsShowMoreCategory.size()>0);
    }

    @When("^I click in show less button in category tile for the category \"([^\"]*)\"$")
    public void iClickInShowLessButtonInCategoryTileForTheCategory(String categoryTitle) {
        category.getDriver().findElement(By.xpath("//div[@class='category-tile-header']//a//span[contains(text(),'"+ categoryTitle +"')]//parent::a//parent::div//span[@class='category-tile-expand-button category-tile-show-less']")).click();
    }

    @Then("^It should collapse and hide the category tile body for the category \"([^\"]*)\"$")
    public void itShouldCollapseAndHideTheCategoryTileBodyForTheCategory(String categoryTitle) {
        assertFalse(category.getDriver().findElement(By.xpath("//div[@class='category-tile-header']//a//span[contains(text(),'"+ categoryTitle +"')]//parent::a//parent::div//parent::div[@class='category-tile']//div[@class='category-tile-body']")).isDisplayed());
    }

    @And("^I click in View All button for the category \"([^\"]*)\"$")
    public void iClickInViewAllButtonForTheCategory(String categoryTitle) throws InterruptedException {
        category.getDriver().findElement(By.xpath("//div[@class='category-tile-header']//a//span[contains(text(),'"+ categoryTitle +"')]//parent::a//parent::div//parent::div[contains(@class,'category-tile-open')]//div[@class='category-tile-view-all']")).click();
    }

    @Then("^I should go to the category \"([^\"]*)\" page$")
    public void iShouldGoToTheCategoryPage(String categoryTitle) {
        operationalUtil.waitElementVisibility(INT_TIMEOUT_JQUERY_TO_DONE, H1_TITLE_CATEGORY);
        assertTrue(H1_TITLE_CATEGORY.getText().equals(categoryTitle));
    }
}
