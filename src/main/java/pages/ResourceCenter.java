package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ResourceCenter extends BaseTest {

    @FindBy(id = "free-search")
    public static WebElement RESOURCE_SEARCH_BAR;

    @FindBy(id = "results-container")
    public static WebElement RESOURCE_RESULT_BOX;

    @FindBy (className = "resource-result")
    public static List <WebElement> RESOURCE_RESULTS;

    @FindBy(id = "drop-down")
    public static WebElement RESOURCE_ORDER;

    @FindBy (xpath = "//*[@id='category-checkbox-buttons']/li[1]/div/label/input")
    public static WebElement RESOURCE_CONTENT_VIDEO_CHECK;

    @FindBy (xpath = "//*[@id='category-checkbox-buttons']/li[2]/div/label/input")
    public static WebElement RESOURCE_CONTENT_ARTICLE_CHECK;

    @FindBy (xpath = "//*[@id='category-checkbox-buttons']/li[3]/div/label/input")
    public static WebElement RESOURCE_CONTENT_USECASE_CHECK;

    @FindBy (xpath = "//*[@id='category-checkbox-buttons']/li[4]/div/label/input")
    public static WebElement RESOURCE_CONTENT_REFERENCEDESIGN_CHECK;

    @FindBy (xpath = "//*[@id='category-checkbox-buttons']/li[5]/div/label/input")
    public static WebElement RESOURCE_CONTENT_APPLICATIONNOTE_CHECK;

    @FindBy (xpath = "//*[@id='category-checkbox-buttons']/li[6]/div/label/input")
    public static WebElement RESOURCE_CONTENT_APPLICATION_CHECK;

    @FindBy (xpath = "//*[@id='category-checkbox-buttons']/li[7]/div/label/input")
    public static WebElement RESOURCE_CONTENT_PRODUCTCATEGORY_CHECK;

    @FindBy (xpath = "//*[@id='category-checkbox-buttons']/li[8]/div/label/input")
    public static WebElement RESOURCE_CONTENT_PRODUCT_CHECK;

    @FindBy (xpath = "//*[@id='category-checkbox-buttons']/li[9]/div/label/input")
    public static WebElement RESOURCE_CONTENT_MPSCHOLAR_CHECK;

    @FindBy (xpath = "//*[@id='category-checkbox-filters']/li[1]/input")
    public static WebElement RESOURCE_CONTENT_TABLE_VIDEO;

    @FindBy (xpath = "//*[@id='category-checkbox-filters']/li[2]/input")
    public static WebElement RESOURCE_CONTENT_TABLE_ARTICLE;

    @FindBy (xpath = "//*[@id='category-checkbox-filters']/li[3]/input")
    public static WebElement RESOURCE_CONTENT_TABLE_USECASE;

    @FindBy (xpath = "//*[@id='category-checkbox-filters']/li[4]/input")
    public static WebElement RESOURCE_CONTENT_TABLE_REFERENCEDESIGN;

    @FindBy (xpath = "//*[@id='category-checkbox-filters']/li[5]/input")
    public static WebElement RESOURCE_CONTENT_TABLE_APPLICATIONNOTE;

    @FindBy (xpath = "//*[@id='category-checkbox-filters']/li[6]/input")
    public static WebElement RESOURCE_CONTENT_TABLE_APPLICATION;

    @FindBy (xpath = "//*[@id='category-checkbox-filters']/li[7]/input")
    public static WebElement RESOURCE_CONTENT_TABLE_MPSCHOLAR;

    @FindBy (xpath = "//*[@id='category-checkbox-filters']/li[8]/input")
    public static WebElement RESOURCE_CONTENT_TABLE_PRODUCTCATEGORY;

    @FindBy (xpath = "//*[@id='category-checkbox-filters']/li[9]/input")
    public static WebElement RESOURCE_CONTENT_TABLE_PRODUCT;

    @FindBy (xpath = "//*[@id='only_type_10']/span")
    public static WebElement RESOURCE_CONTENT_VIDEO_ONLY;

    @FindBy (xpath = "//*[@id='only_type_13']/span")
    public static WebElement RESOURCE_CONTENT_ARTICLE_ONLY;

    @FindBy (xpath = "//*[@id='only_type_16']/span")
    public static WebElement RESOURCE_CONTENT_USECASE_ONLY;

    @FindBy (xpath = "//*[@id='only_type_22']/span")
    public static WebElement RESOURCE_CONTENT_REFERENCEDESIGN_ONLY;

    @FindBy (xpath = "//*[@id='only_type_19']/span")
    public static WebElement RESOURCE_CONTENT_APPLICATIONNOTE_ONLY;

    @FindBy (xpath = "//*[@id='only_type_7']/span")
    public static WebElement RESOURCE_CONTENT_APPLICATION_ONLY;

    @FindBy (xpath = "//*[@id='only_type_4']/span")
    public static WebElement RESOURCE_CONTENT_PRODUCTCATEGORY_ONLY;

    @FindBy (xpath = "//*[@id='only_type_14902']/span")
    public static WebElement RESOURCE_CONTENT_MPSCHOLAR_ONLY;

    @FindBy (xpath = "//*[@id='only_type_1']/span")
    public static WebElement RESOURCE_CONTENT_PRODUCT_ONLY;

    @FindBy (className = "resource-share")
    public static WebElement RESOURCE_PAGE_SHARE;

    @FindBy (xpath = "//*[@class='items']/li")
    public static List <WebElement> RESOURCE_BREADCRUMBS;

    @FindBy (className = "resource-banner-desktop")
    public static WebElement RESOURCE_MPS_BANNER;

    @FindBy (className = "resource-banner-left")
    public static WebElement RESOURCE_CENTER_TEXT;

    @FindBy (xpath = "//input[@class='category-tag-check parent id_43' and @rel='43']")
    public static WebElement RESOURCE_PRODUCT_CATEGORY;

    @FindBy (xpath = "//input[@class='category-tag-check parent id_172' and @rel='172']")
    public static  WebElement RESOURCE_APPLICATION;

    @FindBy (id = "back-top")
    public static WebElement RESOURCE_BACK_TO_TOP;

    @FindBy (id = "load")
    public static WebElement RESOURCE_LOAD_MORE;

    @FindBy (id= "reset-filters-button")
    public static  WebElement RESOURCE_RESET_FILTER;
    private final WebDriver driver;

    public ResourceCenter(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
