package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ParametricTable extends BaseTest {
    @FindBy(id = "table-header")
    public static WebElement ALL_PARAMETRIC_TABLE;
    @FindBy(id = "mps_status_multi_container")
    public static WebElement STATUS_FIELD_PARAMETRIC_TABLE;
    @FindBy(xpath = "//div[@id='slider-range-vinminv']")
    public static WebElement VINMINV_FIELD_PARAMETRIC_TABLE;
    @FindBy(xpath = "//div[@id='pop-spin']")
    public static WebElement SPINNER;
    @FindBy(xpath = "//div[@id='slider-range-vinmaxv']")
    public static WebElement VINMAXV_FIELD_PARAMETRIC_TABLE;
    @FindBy(xpath = "//div[@id='slider-range-voutminv']")
    public static WebElement VOUTMINV_FIELD_PARAMETRIC_TABLE;
    @FindBy(xpath = "//div[@id='slider-range-voutmaxv']")
    public static WebElement VOUTMAXV_FIELD_PARAMETRIC_TABLE;
    @FindBy(xpath = "//div[@id='slider-range-ioutmaxa']")
    public static WebElement IOUTMAX_FIELD_PARAMETRIC_TABLE;
    @FindBy(xpath = "//div[@id='mpl_filter']//span[1]")
    public static WebElement MPL_INDUCTOR_LABEL_FILTER_PARAMETRIC_TABLE;
    @FindBy(xpath = "//div[@class='mpl-separator']")
    public static WebElement MPL_SEPARATOR_FILTER_PARAMETRIC_TABLE;
    @FindBy(xpath = "//span[@class='mpl-title']")
    public static WebElement MPL_TITLE_FILTER_PARAMETRIC_TABLE;
    @FindBy(xpath = "//span[@class='attr-label-filter-mpl']")
    public static List<WebElement> MPL_INDUCTOR_LABEL_TABLE_PARAMETRIC_TABLE;
    private final WebDriver driver;

    public ParametricTable(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
