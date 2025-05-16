package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MpsHome;
import utility.DriverManager;
import utility.operationalUtils;

import static pages.MpsHome.PRODUCT_ITEM_RESULTS;
import static utility.operationalUtils.splitProduct;

public class MpsHomeStepDefs {
    WebDriver driver;
    public operationalUtils operationalUtil;
    public MpsHome mpsHome;

    public MpsHomeStepDefs() {
        this.driver= DriverManager.getDriver();
        this.operationalUtil = new operationalUtils(driver);
        this.mpsHome = new MpsHome(driver);
    }

    @Then("^I should see the product results$")
    public void i_should_see_the_product_results() {
        Assert.assertTrue( operationalUtil.elementsPresent(PRODUCT_ITEM_RESULTS, 3));
        for (WebElement product: PRODUCT_ITEM_RESULTS) {
            Assert.assertTrue((product.getText()).contains(splitProduct(CommonElementsStepDefs.PRODUCT)));
        }
    }
}
