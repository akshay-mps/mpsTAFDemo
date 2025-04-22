package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MpsHome extends BaseTest {
    @FindBy(className = "product-item-name")
    public static List<WebElement> PRODUCT_ITEM_RESULTS;
    private final WebDriver driver;

    public MpsHome(WebDriver driver) {if (driver == null) {
        throw new IllegalArgumentException("WebDriver cannot be null");
    }
        this.driver = driver;
        PageFactory.initElements(driver, this);}
}
