package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class InvestorRelations extends BaseTest {
    @FindBy(xpath = "//div[@class='header-left']//h1")
    public static WebElement DIV_IR_TITLE_TEXT;
    @FindBy(xpath = "//div[@class='header-left']//p")
    public static WebElement DIV_IR_DESCRIPTION_TEXT;
    @FindBy(xpath = "//div[@class='stock-landing-widget']//div[@class='symbol']")
    public static WebElement DIV_IR_NASHDAQ_STOCK_TEXT;
    @FindBy(xpath = "//div[@class='stock-landing-widget']//div[@class='last-trade-time']")
    public static WebElement DIV_IR_NASHDAQ_STOCK_TIME;
    @FindBy(className = "tradingview-widget-container")
    public static WebElement MPWR_STOCK_TILE;
    @FindBy(xpath = "//li[@class='subcategory-tab']//a")
    public static List<WebElement> A_IR_TAB_MENU_LIST;
    @FindBy(xpath = "//div[@class='category-cms']//div[@class='column-1']//h2")
    public static WebElement DIV_IR_PRESS_RELEASES_TITLE_TEXT;
    @FindBy(xpath = "//div[@class='category-cms']//div[@class='column-1']//a[@class='allPressReleasesButton']")
    public static WebElement DIV_IR_PRESS_RELEASES_VIEW_ALL_BUTTON_TEXT;
    @FindBy(xpath = "//div[@class='category-cms']//div[@class='column-2']//h2")
    public static WebElement DIV_IR_EVENTS_PRESENTATIONS_TITLE_TEXT;
    @FindBy(xpath = "//div[@class='category-cms']//div[@class='column-2']//a[@class='allEventsButton']")
    public static WebElement DIV_IR_EVENTS_PRESENTATIONS_VIEW_ALL_BUTTON_TEXT;
    @FindBy(className = "page-title")
    public static WebElement H1_TITLE_IR_EP;
    @FindBy(className = "breadcrumbs")
    public static WebElement BREADCRUMBS_IR;
    @FindBy(className = "category-view")
    public static WebElement DIV_MAIN_REGION_IR_EP;
    @FindBy(xpath = "//div[@class='investor_relations_archived_events']//div[@class='presentationDate']")
    public static List<WebElement> DIV_ARCHIVED_EVENTS_DATES_IR_EP_LIST;
    @FindBy(xpath = "//div[@class='investor_relations_upcoming_events']//div[@class='presentationDate']")
    public static List<WebElement> DIV_UPCOMING_EVENTS_DATES_IR_EP_LIST;
    @FindBy(xpath = "//p[@class='moreEvents']")
    public static WebElement DIV_UPCOMING_EVENTS_MORE_EVENTS_COMING_SOON_TEXT_IR_EP;
    private final WebDriver driver;

    public InvestorRelations(WebDriver driver) {if (driver == null) {
        throw new IllegalArgumentException("WebDriver cannot be null");
    }
        this.driver = driver;
        PageFactory.initElements(driver, this);}
}
