package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class Articles extends BaseTest {

    private final WebDriver driver;
    @FindBy(id = "application-notes-detail")
    public WebElement MAIN_DIV_TABLE_ARTICLES;  // Changed to instance variable

    @FindBy(xpath = "//tbody[@id='application-notes-detail']//td//a")
    public List<WebElement> A_TD_TABLE_LEFT_COLUMN_ARTICLES;  // Changed to instance variable

    public Articles(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
