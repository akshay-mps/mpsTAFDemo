package pages;

import base.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage extends BaseTest{

    @FindBy(className = "category-tiles")
    public static WebElement MAIN_DIV_CATEGORY_TILES;
    @FindBy(id = "page-title-heading")
    public static WebElement H1_TITLE_CATEGORY;
    private final WebDriver driver;


    public CategoryPage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);    }
}
