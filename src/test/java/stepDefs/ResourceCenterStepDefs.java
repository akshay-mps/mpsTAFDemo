package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.ResourceCenter;
import utility.operationalUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import static pages.ResourceCenter.*;


public class ResourceCenterStepDefs {

    public ResourceCenter rCenterStep;
    public operationalUtils operationalUtil;

    private WebDriver driver;
    private BaseTest bt = new BaseTest();



    public ResourceCenterStepDefs() {
        driver = bt.getDriver();
        this.rCenterStep = new ResourceCenter(driver);
        this.operationalUtil = new operationalUtils(driver);
    }


    @When("^I am on the page$")
    public void check_page_loaded() {
        operationalUtil.waitForAllJS(30);
        operationalUtil.waitElementVisibility(20, RESOURCE_RESULT_BOX);
        assertTrue(RESOURCE_RESULT_BOX.isDisplayed());
    }

    @And("^I use search bar for result of \"([^\"]*)\"$")
    public void search_bar_result(String item) {
        RESOURCE_SEARCH_BAR.sendKeys(item);
        RESOURCE_SEARCH_BAR.sendKeys(Keys.ENTER);
        operationalUtil.waitElementVisibility(10, RESOURCE_RESULT_BOX);
        assertTrue(RESOURCE_RESULTS.size() > 0);
    }

    @And("^I verify sorting order from URL$")
    public void verify_sorting_order() {
        String url = driver.getCurrentUrl();
        Matcher matcher = Pattern.compile("[?&]sort=([^&]*)").matcher(url);
        String sortValue = matcher.find() ? matcher.group(1) : null;
        assertTrue(RESOURCE_ORDER.getText().toLowerCase().equals(sortValue));
    }

    @And("^I verify content type from URL$")
    public void verify_content_type() {
        String url = driver.getCurrentUrl();
        Matcher matcher = Pattern.compile("content_type_filter=([^&]*)").matcher(url);

        List<String> filters = new ArrayList<>();
        String filtersString = matcher.find() ? matcher.group(1) : "";

        for (String item : filtersString.split("%2C")) {
            filters.add(item.toLowerCase().replace("_", " "));
        }

        for (String item : filters) {
            switch (item) {
                case "video":
                    assertTrue(RESOURCE_CONTENT_VIDEO_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_VIDEO.isSelected());
                case "article":
                    assertTrue(RESOURCE_CONTENT_ARTICLE_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_ARTICLE.isSelected());
                case "use case":
                    assertTrue(RESOURCE_CONTENT_USECASE_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_USECASE.isSelected());
                case "reference design":
                    assertTrue(RESOURCE_CONTENT_REFERENCEDESIGN_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_REFERENCEDESIGN.isSelected());
                case "application note":
                    assertTrue(RESOURCE_CONTENT_APPLICATIONNOTE_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_APPLICATIONNOTE.isSelected());
                case "application":
                    assertTrue(RESOURCE_CONTENT_APPLICATION_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_APPLICATION.isSelected());
                case "product category":
                    assertTrue(RESOURCE_CONTENT_PRODUCTCATEGORY_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_PRODUCTCATEGORY.isSelected());
                case "product":
                    assertTrue(RESOURCE_CONTENT_PRODUCT_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_PRODUCT.isSelected());
                case "mpscholar":
                    assertTrue(RESOURCE_CONTENT_MPSCHOLAR_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_MPSCHOLAR.isSelected());
            }
        }
    }

    @And("^I verify ONLY option on \"([^\"]*)\" content type$")
    public void verify_only_option(String category) {
        Actions action = new Actions(rCenterStep.getDriver());
        switch (category) {
            case "video":
                action.moveToElement(RESOURCE_CONTENT_VIDEO_CHECK).perform();
                action.moveToElement(RESOURCE_CONTENT_VIDEO_ONLY).click().perform();

            case "article":
                action.moveToElement(RESOURCE_CONTENT_ARTICLE_CHECK).perform();
                action.moveToElement(RESOURCE_CONTENT_ARTICLE_ONLY).click().perform();

            case "use case":
                action.moveToElement(RESOURCE_CONTENT_USECASE_CHECK).perform();
                action.moveToElement(RESOURCE_CONTENT_USECASE_ONLY).click().perform();

            case "reference design":
                action.moveToElement(RESOURCE_CONTENT_REFERENCEDESIGN_CHECK).perform();
                action.moveToElement(RESOURCE_CONTENT_REFERENCEDESIGN_ONLY).click().perform();

            case "application note":
                action.moveToElement(RESOURCE_CONTENT_APPLICATIONNOTE_CHECK).perform();
                action.moveToElement(RESOURCE_CONTENT_APPLICATIONNOTE_ONLY).click().perform();

            case "application":
                action.moveToElement(RESOURCE_CONTENT_APPLICATION_CHECK).perform();
                action.moveToElement(RESOURCE_CONTENT_APPLICATION_ONLY).click().perform();

            case "product category":
                action.moveToElement(RESOURCE_CONTENT_PRODUCTCATEGORY_CHECK).perform();
                action.moveToElement(RESOURCE_CONTENT_PRODUCTCATEGORY_ONLY).click().perform();

            case "product":
                action.moveToElement(RESOURCE_CONTENT_PRODUCT_CHECK).perform();
                action.moveToElement(RESOURCE_CONTENT_PRODUCT_ONLY).click().perform();

            case "mpscholar":
                action.moveToElement(RESOURCE_CONTENT_MPSCHOLAR_CHECK).perform();
                action.moveToElement(RESOURCE_CONTENT_MPSCHOLAR_ONLY).click().perform();

        }
    }

    @And("^I verify the link is copied successfully$")
    public void verify_share_link_option() {
        RESOURCE_PAGE_SHARE.click();
        assertTrue(RESOURCE_PAGE_SHARE.getAttribute("aria-describedby").length() > 0);
    }

    @And("^I select product category and verify in URL$")
    public void verify_product_cateogry_in_url() {
        RESOURCE_PRODUCT_CATEGORY.click();
        assertTrue(driver.getCurrentUrl().contains(RESOURCE_PRODUCT_CATEGORY.getText()));
    }

    @And("^I select up arrow button$")
    public void verify_up_arrow_button() {

    }

    @And("^I select load more button$")
    public void verify_load_more_button() {
        int initial_length = RESOURCE_RESULTS.size();
        RESOURCE_LOAD_MORE.click();
        int final_length = RESOURCE_RESULTS.size();
        assertTrue(final_length > initial_length);
    }

    @And("^I select reset filter option \"([^\"]*)\"$")
    public void verify_reset_filter(String url) {
        RESOURCE_RESET_FILTER.click();
        assertTrue(url.equals(driver.getCurrentUrl()));
    }

    @And("^I add content type \"([^\"]*)\" and sorting order \"([^\"]*)\" to URL$")
    public void verify_content_type_from_url(String content_type, String order) {
        String[] itemsList = content_type.replaceAll(" ", "_").split(",");
        String url = driver.getCurrentUrl();

        String contentTypeFilterKeyword = "content_type_filter=";
        String sortFilterKeyword = "sort=";
        boolean containsContentTypeKey = url.contains(contentTypeFilterKeyword);
        boolean containsSortFilterKey = url.contains(sortFilterKeyword);

        if (containsContentTypeKey) {
            String existingFilter = url.substring(url.indexOf(contentTypeFilterKeyword) + contentTypeFilterKeyword.length());
            existingFilter = existingFilter.contains("&") ? existingFilter.substring(0, existingFilter.indexOf("&")) : existingFilter;
            String newFilter = existingFilter + "%2C" + String.join("%2C", itemsList);
            url = url.replaceFirst(contentTypeFilterKeyword + existingFilter, contentTypeFilterKeyword + newFilter);
        } else {
            url += (url.contains("?") ? "&" : "?") + contentTypeFilterKeyword + itemsList[0];
            for (int i = 1; i < itemsList.length; i++) {
                url += "," + itemsList[i];
            }
        }

        if (containsSortFilterKey) {
            url = url.replaceAll(sortFilterKeyword + "[^&]*", sortFilterKeyword + order);
        } else {
            url += "&" + sortFilterKeyword + order;
        }

        driver.get(url);

        for (String item : itemsList) {
            switch (item) {
                case "video":
                    assertTrue(RESOURCE_CONTENT_VIDEO_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_VIDEO.isSelected());
                case "article":
                    assertTrue(RESOURCE_CONTENT_ARTICLE_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_ARTICLE.isSelected());
                case "use case":
                    assertTrue(RESOURCE_CONTENT_USECASE_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_USECASE.isSelected());
                case "reference design":
                    assertTrue(RESOURCE_CONTENT_REFERENCEDESIGN_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_REFERENCEDESIGN.isSelected());
                case "application note":
                    assertTrue(RESOURCE_CONTENT_APPLICATIONNOTE_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_APPLICATIONNOTE.isSelected());
                case "application":
                    assertTrue(RESOURCE_CONTENT_APPLICATION_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_APPLICATION.isSelected());
                case "product category":
                    assertTrue(RESOURCE_CONTENT_PRODUCTCATEGORY_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_PRODUCTCATEGORY.isSelected());
                case "product":
                    assertTrue(RESOURCE_CONTENT_PRODUCT_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_PRODUCT.isSelected());
                case "mpscholar":
                    assertTrue(RESOURCE_CONTENT_MPSCHOLAR_CHECK.isSelected());
                    assertTrue(RESOURCE_CONTENT_TABLE_MPSCHOLAR.isSelected());
            }
        }
        assertTrue(driver.getCurrentUrl().contains(RESOURCE_ORDER.getText().toLowerCase()));
    }

    @And("^I add product categories \"([^\"]*)\" to the URL$")
    public void verify_product_category_added_in_url(String productCategory) {

        RESOURCE_RESET_FILTER.click();
        String url = driver.getCurrentUrl();
        String categoryFilterKeyword = "category_filter=";
        boolean containsCategoryFilterKey = url.contains(categoryFilterKeyword);
        String paramCategory = productCategory.replaceAll(",", "%2C");

        if (containsCategoryFilterKey) {
            String existingFilter = url.substring(url.indexOf(categoryFilterKeyword) + categoryFilterKeyword.length());
            existingFilter = existingFilter.contains("&") ? existingFilter.substring(0, existingFilter.indexOf("&")) : existingFilter;
            url = url.replaceFirst(categoryFilterKeyword + existingFilter, categoryFilterKeyword + existingFilter + "%2C" + paramCategory);
        } else {
            url += (url.contains("?") ? "&" : "?") + categoryFilterKeyword + productCategory.split(",")[0] + "%2C" + paramCategory;
        }

        driver.get(url);

        operationalUtil.waitElementVisibility(10, RESOURCE_PRODUCT_CATEGORY);
        assertTrue(RESOURCE_PRODUCT_CATEGORY.isSelected());
    }

    @And("^I add application \"([^\"]*)\" to the URL$")
    public void verify_application_added_in_url(String applicationCategory) {

        String url = driver.getCurrentUrl();
        String applicationFilterKeyword = "application_filter=";
        boolean containsApplicationFilterKey = url.contains(applicationFilterKeyword);
        String paramCategory = applicationCategory.replaceAll(",", "%2C");

        if (containsApplicationFilterKey) {
            String existingFilter = url.substring(url.indexOf(applicationFilterKeyword) + applicationFilterKeyword.length());
            existingFilter = existingFilter.contains("&") ? existingFilter.substring(0, existingFilter.indexOf("&")) : existingFilter;
            url = url.replaceFirst(applicationFilterKeyword + existingFilter, applicationFilterKeyword + existingFilter + "%2C" + paramCategory);
        } else {
            url += (url.contains("?") ? "&" : "?") + applicationFilterKeyword + applicationCategory.split(",")[0] + "%2C" + paramCategory;
        }

        driver.get(url);

        operationalUtil.waitElementVisibility(10, RESOURCE_APPLICATION);
        assertTrue(RESOURCE_APPLICATION.isSelected());
    }

    @And("^I add filter \"([^\"]*)\" for search bar in URL$")
    public void verify_filter_added_in_url(String searchBar) {

        String url = driver.getCurrentUrl();
        String filterKeyword = "filter=";
        boolean containsFilterKey = url.contains(filterKeyword);
        String paramCategory = searchBar.replaceAll(",", "%2C");

        if (containsFilterKey) {
            String existingFilter = url.substring(url.indexOf(filterKeyword) + filterKeyword.length());
            existingFilter = existingFilter.contains("&") ? existingFilter.substring(0, existingFilter.indexOf("&")) : existingFilter;
            url = url.replaceFirst(filterKeyword + existingFilter, filterKeyword + existingFilter + "%2C" + paramCategory);
        } else {
            url += (url.contains("?") ? "&" : "?") + filterKeyword + searchBar.split(",")[0] + "%2C" + paramCategory;
        }

        driver.get(url);
        assertTrue(RESOURCE_SEARCH_BAR.getText().contains(searchBar) && RESOURCE_RESULTS.size()>0);
    }

    @Then("^I check small placeholder item in the page$")
    public void verify_small_placeholder_items() {
        assertTrue(RESOURCE_BREADCRUMBS.size()>0);
        assertTrue(RESOURCE_MPS_BANNER.isDisplayed());
        assertTrue(RESOURCE_CENTER_TEXT.getText().contains("Resource Center"));

    }
}

