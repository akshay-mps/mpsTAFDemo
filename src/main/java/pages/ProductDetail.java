package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductDetail extends BaseTest {
    @FindBy(id = "product-addtocart-button")
    public static WebElement ADD_TO_CART_BUTTON;
    @FindBy(id = "qty")
    public static WebElement QUANTITY_FIELD;
    @FindBy(id = "nav_evaluation_buybox")
    public static WebElement VIEW_RELATED_PRODUCTS_BUTTON;
    @FindBy(id = "nav_design_resources")
    public static WebElement DESIGN_RESOURCES_TAB;
    @FindBy(id = "nav_description")
    public static WebElement DESCRIPTION_TAB;
    @FindBy(id = "nav_intro")
    public static WebElement INTRO_TAB;
    @FindBy(id = "nav_intro")
    public static WebElement OVERVIEW_TAB;
    @FindBy(id = "nav_evaluation")
    public static WebElement RELATED_PRODUCTS_TAB;
    @FindBy(id = "nav_quality")
    public static WebElement QUALITY_TAB;
    @FindBy(id = "nav_order")
    public static WebElement ORDER_BUTTON_TAB;
    @FindBy(xpath = "//div[@class='message-success success message']//div[@data-bind='html: message.text']")
    public static WebElement SUCCESS_MESSAGE;
    @FindBy(xpath = "//div[@class='message-error error message']//div[@data-bind='html: message.text']")
    public static WebElement ERROR_MESSAGE;
    @FindBy(className = "product-info-main")
    public static WebElement GREY_AREA_INFO_PRODUCT;
    @FindBy(xpath = "//span[@class='qty-block']")
    public static WebElement QUANTITY_LABEL_INFO_PRODUCT;
    @FindBy(xpath = "//span[@class='unit-block']")
    public static WebElement PRICE_LABEL_INFO_PRODUCT;
    @FindBy(xpath = "//div[@class='qty-block']")
    public static WebElement QUANTITY_NUMBER_INFO_PRODUCT;
    @FindBy(xpath = "//div[@class='unit-block']")
    public static WebElement PRICE_NUMBER_INFO_PRODUCT;
    @FindBy(xpath = "//div[@class='stock available']//span")
    public static WebElement STATUS_INFO_PRODUCT;
    @FindBy(xpath = "//div[@class='stock available']//p//span[1]")
    public static WebElement DELIVERY_TIME_INFO_PRODUCT;
    @FindBy(xpath = "//div[@class='stock available']//p//span[2]")
    public static WebElement DELIVERY_PRICE_INFO_PRODUCT;
    @FindBy(xpath = "//label[@for='qty']")
    public static WebElement QUANTITY_INPUT_LABEL_INFO_PRODUCT;
    @FindBy(className = "SEE-ALL-BUYING-OPTIO1")
    public static WebElement LEARN_ABOUT_LABEL_INFO_PRODUCT;
    @FindBy(xpath = "//div[@class='View-distributor-pri2']//button//span")
    public static WebElement TALK_TO_ENGINEER_LABEL_INFO_PRODUCT;
    @FindBy(xpath = "//*[@class='footer_block_Contact']")
    public static WebElement CONTACT_US_BUTTON;
    @FindBy(className = "eve")
    public static List<WebElement> RELATED_PRODUCTS_SECTION;
    @FindBy(id = "maincontent")
    public static WebElement MAIN_CONTENT_PDP;
    @FindBy(className = "breadcrumbs")
    public static WebElement BREADCRUMBS_PDP;
    @FindBy(id = "stickey")
    public static WebElement HEADER_PDP;
    @FindBy(xpath = "//div[@id='stickey']//ul//li[1]//span")
    public static WebElement HEADER_MENU1_PDP;
    @FindBy(xpath = "//div[@id='stickey']//ul//li[2]//span")
    public static WebElement HEADER_MENU2_PDP;
    @FindBy(xpath = "//div[@id='stickey']//ul//li[3]//span")
    public static WebElement HEADER_MENU3_PDP;
    @FindBy(xpath = "//div[@id='stickey']//ul//li[4]//span")
    public static WebElement HEADER_MENU4_PDP;
    @FindBy(xpath = "//div[@id='stickey']//ul//li[5]//span")
    public static WebElement HEADER_MENU5_PDP;
    @FindBy(xpath = "//span[@itemprop='name']")
    public static WebElement PRODUCT_NAME_PDP;
    @FindBy(xpath = "//div[@id='product-header']//div[@class='product-header-status  ']//div//span")
    public static WebElement PRODUCT_STATUS_PDP;
    @FindBy(xpath = "//div[@id='product-header']/div/p[1]")
    public static WebElement PRODUCT_CATEGORY_DESCRIPTION_PDP;
    @FindBy(xpath = "//div[@class='owl-stage']//div//div//img")
    public static WebElement PRODUCT_IMAGE_VIEW_PDP;
    @FindBy(className = "owl-dots")
    public static WebElement PRODUCT_THUMBSIMAGE_PDP;
    @FindBy(xpath = "//div[@id='product-header']//div[contains(@class,'download-sheet product-header-datasheet')]//div[1]//div[@id='datasheet1']//a")
    public static WebElement PRODUCT_DATASHEET_PDP;
    @FindBy(xpath = "//div[@class='product-description-wrapper less']//div//p")
    public static WebElement PRODUCT_DESCRIPTION_TEXT_PDP;
    @FindBy(xpath = "//div[@class='product-description less']//p")
    public static WebElement PRODUCT_DESCRIPTION_TEXT_PDP_WITHOUT_MORE;
    @FindBy(xpath = "//div[@id='product_description_block']//div//p")
    public static WebElement PRODUCT_DESCRIPTION_TEXT_PDP_KIT;
    @FindBy(xpath = "//div[@class='product-description-wrapper less']//h2")
    public static WebElement PRODUCT_DESCRIPTION_TITLE_PDP;
    @FindBy(xpath = "//div[@id='product_description_block']//h1")
    public static WebElement PRODUCT_DESCRIPTION_TITLE_PDP_KIT;
    @FindBy(xpath = "//div[@class='features']//h3")
    public static WebElement PRODUCT_FEATURES_BENEFITS_TITLE_PDP;
    @FindBy(xpath = "//div[@class='lf_se']//h3")
    public static WebElement PRODUCT_FEATURES_TITLE_PDP;
    @FindBy(xpath = "//div[@class='rt_se']//h3")
    public static WebElement PRODUCT_PARAMETRICS_TITLE_PDP;
    @FindBy(xpath = "//div[@class='features-wrapper']//div[@class='features']//div//ul")
    public static WebElement PRODUCT_FEATURES_PDP;
    @FindBy(xpath = "//div[@class='columns']//div[@class='column main']//h2[@class='design-resources-title']")
    public static WebElement PRODUCT_DESIGN_RESORUCES_TITLE_PDP;
    @FindBy(xpath = "//div[@class='design-resources-wrapper']")
    public static WebElement PRODUCT_DESIGN_RESOURCES_GRID_PDP;
    @FindBy(xpath = "//div[@id='qualityscrolling']//div[@class='qulaity-docs']")
    public static WebElement PRODUCT_SEE_ALL_DOCUMENTS_QUALITY_PDP;
    @FindBy(id = "evaluationscrolling")
    public static WebElement PRODUCT_RELATED_PRODUCTS_TITLE_PDP;
    @FindBy(xpath = "//div[@class='add-to-cart-wrapper']//h2")
    public static WebElement PRODUCT_BUY_FROM_MPS_TITLE_PDP;
    @FindBy(xpath = "//div[@class='evaluation_bloack1']//span//img")
    public static WebElement PRODUCT_RELATED_PRODUCTS_IMAGE_PDP;
    @FindBy(xpath = "//div[@class='evaluation_bloack2']//h4")
    public static WebElement PRODUCT_RELATED_PRODUCTS_CATEGORY_DESCRIPTION_PDP;
    @FindBy(xpath = "//div[@class='evaluation_bloack2']//p")
    public static WebElement PRODUCT_RELATED_PRODUCTS_TEXT_PDP;
    @FindBy(xpath = "//div[@class='middle-block']//div//a")
    public static WebElement PRODUCT_REFERENCE_MATERIALS_DATA_DOWNLOADABLE_PDP;
    @FindBy(xpath = "//div[@class='qulaity-docs']//a")
    public static WebElement PRODUCT_QUALITY_POLICY_SEE_ALL_LINK_PDP;
    @FindBy(className = "ler")
    public static WebElement PRODUCT_RELATED_PRODUCTS_LEARN_MORE_BUTTON_PDP;
    @FindBy(className = "quality-policy-title")
    public static WebElement PRODUCT_QUALITY_POLICY_TITLE_PDP;
    @FindBy(className = "mps-parts")
    public static WebElement PRODUCT_QUALITY_POLICY_CONTENT_PDP;
    @FindBy(xpath = "//div[@class='reliability-image-1 reliability-image-box']")
    public static WebElement PRODUCT_QUALITY_POLICY_SECTION1_PDP;
    @FindBy(xpath = "//div[@class='reliability-image-2 reliability-image-box']")
    public static WebElement PRODUCT_QUALITY_POLICY_SECTION2_PDP;
    @FindBy(xpath = "//div[@class='reliability-image-3 reliability-image-box']")
    public static WebElement PRODUCT_QUALITY_POLICY_SECTION3_PDP;
    @FindBy(xpath = "//div[@class='reliability-image-4 reliability-image-box']")
    public static WebElement PRODUCT_QUALITY_POLICY_SECTION4_PDP;
    @FindBy(className = "reliabiliy-right-side")
    public static WebElement PRODUCT_QUALITY_POLICY_IMAGE_DIV_CONTAINER_PDP;
    @FindBy(xpath = "//span[@class='counter qty']")
    public static WebElement SPAN_ADD_TO_CART_ATTRIBUTE;
    @FindBy(xpath = "//ul[@class='desc-show-more']//li")
    public static WebElement SHOW_MORE_BUTTON_DESCRIPTION_PDP;
    @FindBy(xpath = "//div[@class='show-more-button more']")
    public static WebElement SHOW_MORE_BUTTON_FEATURES_BENEFITS_PDP;
    @FindBy(xpath = "//div[@class='show-more-button less']")
    public static WebElement SHOW_MORE_BUTTON_FEATURES_BENEFITS_LESS_PDP;
    @FindBy(xpath = "//div[@class='product-info-description']//div//ul")
    public static WebElement SHOW_MORE_BUTTON_IS_PRESENT_PDP;
    @FindBy(xpath = "//div[@id='intro-block']//div[@class='pduct-tittle-Rectangle-8 old-layout-prod-mpl-label']")
    public static WebElement MPL_TITLE_PRODUCT_LABEL_PDP;
    @FindBy(xpath = "//div[@id='product_description_block']//div[@class='pduct-tittle-Rectangle-8 old-layout-prod-mpl-label desc-label']")
    public static WebElement MPL_PRODUCT_DESCRIPTION_LABEL_PDP;
    @FindBy(xpath = "//div[@class='gallery-mpl-text']")
    public static WebElement MPL_GALLERY_TEXT_PDP;
    @FindBy(xpath = "//div[@class='desc-mpl-text']")
    public static WebElement MPL_DESCRIPTION_TEXT_PDP;
    @FindBy(xpath = "//div[@class='title-description']//div[@class='description']//div[@class='desc-mpl-text']")
    public static WebElement MPL_DESCRIPTION_TEXT_KIT_SECTION_PDP;
    @FindBy(xpath = "//div[@id='product-header']//div[@class='product-header-mpl-label']//span")
    public static WebElement MPL_TITLE_PRODUCT_LABEL_PDP_SINGLE_PRODUCT;
    @FindBy(xpath = "//div[@id='product-header']//div[@class='product-header-mpl-label']")
    public static WebElement MPL_TITLE_ATTRIBUTE_PRODUCT_LABEL_PDP_SINGLE_PRODUCT;
    @FindBy(xpath = "//div[@class='evk-mpl-text']")
    public static WebElement MPL_LABEL_IMAGE_KIT_PDP_SINGLE_PRODUCT;
    @FindBy(xpath = "//div[@class='recommended-inductors-wrapper']")
    public static WebElement MPL_RECOMMENDED_INDUCTORS_SECTION_PDP_SINGLE_PRODUCT;
    @FindBy(xpath = "//div[@class='recommended-inductor-label']")
    public static WebElement MPL_RECOMMENDED_INDUCTORS_SECTION_LABEL_MPL_PDP_SINGLE_PRODUCT;
    @FindBy(xpath = "//div[@class='recommended-inductor-label']")
    public static List <WebElement> MPL_RECOMMENDED_INDUCTORS_SECTION_LABEL_MPL_PDP_SINGLE_PRODUCT_LIST;
    @FindBy(xpath = "//div[@class='recommended-inductors-table-container']//a//div[3]")
    public static WebElement MPL_RECOMMENDED_INDUCTORS_SECTION_TEXT_MPL_PDP_SINGLE_PRODUCT;
    @FindBy(className = "technical-forum-title")
    public static WebElement FORUM_TITLE_POST_SECTION_PDP;
    @FindBy(className = "technical-forum-table")
    public static WebElement FORUM_TABLE_POST_SECTION_PDP;
    @FindBy(className = "technical-forum-topic-title")
    public static WebElement FORUM_POST_ELEMENT_TITLE_PDP;
    @FindBy(className = "technical-forum-topic-text")
    public static WebElement FORUM_POST_ELEMENT_COMMENT_TOPIC_PDP;
    @FindBy(className = "technical-forum-latest-activity")
    public static WebElement FORUM_POST_ELEMENT_LATEST_ACTIVITY_PDP;
    @FindBy(className = "technical-forum-comments")
    public static WebElement FORUM_POST_ELEMENT_COMMENTS_PDP;
    @FindBy(className = "technical-forum-see-all-posts")
    public static WebElement FORUM_BUTTON_SEE_ALL_POSTS_PDP;
    @FindBy(xpath = "//div[@class='mpsmart-document-widget']//table")
    public static WebElement MPSMART_TABLE_ELEMENT;
    @FindBy(tagName = "th")
    public static List<WebElement> MPSMART_LIST_HEADER_ELEMENTS;
    @FindBy(xpath = "//div[@class='mpsmart-document-widget']//table//tbody//a")
    public static List<WebElement> MPSMART_LIST_A_ELEMENTS_ENTIRE_TABLE;
    private final WebDriver driver;

    public ProductDetail(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
