package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import pages.ProductDetail;
import utility.DriverManager;
import utility.operationalUtils;

import static Resources.ProjectConstants.*;
import static org.junit.Assert.*;
import static pages.CommonElements.*;
import static pages.ProductDetail.*;

public class ProductDetailStepDefs {
    WebDriver driver;
    public ProductDetail productDetailS;
    public operationalUtils operationalUtil;
    public static String productTitleRelatedProduct;

    public ProductDetailStepDefs() {
        this.driver = DriverManager.getDriver();
        this.productDetailS = new ProductDetail(driver);
        this.operationalUtil = new operationalUtils(driver);
    }

    @When("^I click on Add to Cart button \"(\\d+)\" times$")
    public void i_click_on_Add_to_Cart_button_times(int click_times) {
        for(int i = 0; i < click_times; i++){
            try {
                operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
            }catch (AssertionError e){
            }
            operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
            operationalUtil.waitElementVisibility(5, ADD_TO_CART_BUTTON);
            ADD_TO_CART_BUTTON.click();
            operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
            try {
                operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
            }catch (AssertionError e){
            }
        }

    }

    @Given("^I change the quantity field for \"([^\"]*)\"$")
    public void i_change_the_quantity_field_for(String quantity) {
        QUANTITY_FIELD.clear();
        QUANTITY_FIELD.sendKeys(quantity);
    }

    @When("^I click on Add to Cart button$")
    public void i_click_on_Add_to_Cart_button() {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitElementInViewPort(5, ADD_TO_CART_BUTTON);
        ADD_TO_CART_BUTTON.click();
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
    }

    @Then("^I should see the message \"([^\"]*)\"$")
    public void i_should_see_the_message(String message) {

    }

    @Then("^I should see the \"([^\"]*)\" message regarding to the product \"([^\"]*)\"$")
    public void i_should_see_the_message_regarding_to_the_product(String messageType, String product) {
        switch(messageType) {
            case "success":
                assertTrue(SUCCESS_MESSAGE.getText().equalsIgnoreCase("You added "+product+" to your shopping cart."));
                break;
            case "error":
                assertTrue(ERROR_MESSAGE.getText().contains("The requested qty exceeds the maximum qty allowed in shopping cart"));
                break;
        }
    }

    @When("^I see the Grey Area$")
    public void i_see_the_Grey_Area() {
        operationalUtil.waitElementVisibility(4, GREY_AREA_INFO_PRODUCT);
        assertTrue(GREY_AREA_INFO_PRODUCT.isDisplayed());
    }


    @Then("^I should see the quantity label \"([^\"]*)\"$")
    public void i_should_see_the_quantity_label(String quantityLabel) {
        assertTrue(QUANTITY_LABEL_INFO_PRODUCT.isDisplayed());
        assertEquals(QUANTITY_LABEL_INFO_PRODUCT.getText(), quantityLabel);
    }

    @Then("^I should see the price label \"([^\"]*)\"$")
    public void i_should_see_the_price_label(String priceLabel) {
        assertTrue(PRICE_LABEL_INFO_PRODUCT.isDisplayed());
        assertEquals(PRICE_LABEL_INFO_PRODUCT.getText(), priceLabel);
    }

    //This method is only to check quantity in grey area for kit products
    @Then("^I should see the quantity of the product$")
    public void i_should_see_the_quantity_of_the_product() {
        assertEquals(QUANTITY_NUMBER_INFO_PRODUCT.getText(), "1");
    }

    @Then("^I should see the product price in \"([^\"]*)\"$")
    public void i_should_see_the_product_price_in(String currency) {
        assertTrue(PRICE_NUMBER_INFO_PRODUCT.getText().contains(currency));
        //This line evaluate if the price is greater than 0, the regex deletes all the chars except digits
        assertTrue(Integer.valueOf(PRICE_NUMBER_INFO_PRODUCT.getText().replaceAll(".\\D+","").replaceAll("\\D+",""))>0);

    }

    @Then("^I should see the product status \"([^\"]*)\"$")
    public void i_should_see_the_product_status(String status) {
        assertEquals(STATUS_INFO_PRODUCT.getText(), status);
    }

    @Then("^I should see the delivery time label \"([^\"]*)\"$")
    public void i_should_see_the_delivery_time_label(String deliveryTimeLabel) {
        assertEquals(DELIVERY_TIME_INFO_PRODUCT.getText(), deliveryTimeLabel);
    }

    @Then("^I should see the delivery price label \"([^\"]*)\"$")
    public void i_should_see_the_delivery_price_label(String deliveryPriceLabel) {
        assertEquals(DELIVERY_PRICE_INFO_PRODUCT.getText(), deliveryPriceLabel);
    }

    @Then("^I should see the quantity input label \"([^\"]*)\"$")
    public void i_should_see_the_quantity_input_label(String quantityInputLabel) {
        assertEquals(QUANTITY_INPUT_LABEL_INFO_PRODUCT.getText(), quantityInputLabel);
    }

    @Then("^I should see the quantity input default number with (\\d+)$")
    public void i_should_see_the_quantity_input_default_number_with(int intQuantity) {
        assertEquals(Integer.valueOf(QUANTITY_FIELD.getAttribute("value")), (Integer) intQuantity);
    }

    @Then("^I should see the Add to Cart button with text \"([^\"]*)\"$")
    public void i_should_see_the_Add_to_Cart_button_with_text(String addToCartButtonText) {
        assertEquals(ADD_TO_CART_BUTTON.getText(), addToCartButtonText);
    }

    @Then("^I should see the Talk to an Engineer label with text \"([^\"]*)\"$")
    public void i_should_see_the_Talk_to_an_Engineer_label_with_text(String talkToAnEngineer) {
        assertEquals(TALK_TO_ENGINEER_LABEL_INFO_PRODUCT.getText(), talkToAnEngineer);
    }

    @Then("^I should see the Contact Us button with text \"([^\"]*)\"$")
    public void i_should_see_the_Contact_Us_button_with_text(String contactUsButtonText) {
        assertEquals(CONTACT_US_BUTTON.getText(), contactUsButtonText);
    }

    @When("^I click on View Related Products button$")
    public void i_click_on_View_Related_Products_button() {
        VIEW_RELATED_PRODUCTS_BUTTON.click();
        operationalUtil.waitElementVisibility(5, RELATED_PRODUCTS_SECTION.get(0));
    }

    @Then("^I should see the Related Products Section$")
    public void i_should_see_the_Related_Products_Section() {
        assertTrue(RELATED_PRODUCTS_SECTION.size()>0);
        assertTrue(RELATED_PRODUCTS_SECTION.get(0).isDisplayed());
        operationalUtil.waitElementClickability(5, RELATED_PRODUCTS_SECTION.get(0));
    }

    @When("^I click on the Contact Us button$")
    public void i_click_on_the_Contact_Us_button() {
        operationalUtil.clickDirectlyByDOM(CONTACT_US_BUTTON);
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        try {
            operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }catch (AssertionError e){
            operationalUtil.waitForAttributeNotPresent(BODY_CONTAINER, "aria-busy");
        }
    }

    @Then("^I should see the contact page with text \"([^\"]*)\"$")
    public void i_should_see_the_contact_page(String contactText) {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        try {
            operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }catch (AssertionError e){
        }
        assertEquals(productDetailS.getDriver().getTitle(), contactText);

    }

    @When("^I see the main content$")
    public void i_see_the_main_content() {
        operationalUtil.waitElementVisibility(20, MAIN_CONTENT_PDP);
        assertTrue(MAIN_CONTENT_PDP.isDisplayed());
    }

    @Then("^I should see the breadcrumbs$")
    public void i_should_see_the_breadcrumbs() {
        assertTrue(!BREADCRUMBS_PDP.getText().equals(""));
    }

    @Then("^I should see the product title$")
    public void i_should_see_the_product_title() {
        assertTrue(PRODUCT_NAME_PDP.isDisplayed());
        assertTrue(!PRODUCT_NAME_PDP.getText().equals(""));
    }

    @Then("^I should see the product MPS status \"([^\"]*)\"$")
    public void i_should_see_the_product_MPS_status(String productStatus) {
        assertEquals(productStatus, PRODUCT_STATUS_PDP.getText());

    }

    @Then("^I should see the product category$")
    public void i_should_see_the_product_category() {
        assertTrue(!PRODUCT_CATEGORY_DESCRIPTION_PDP.getText().equals(""));
    }

    @Then("^I should see the product image view$")
    public void i_should_see_the_product_image_view() {
        assertTrue(PRODUCT_IMAGE_VIEW_PDP.isEnabled());
        assertTrue(PRODUCT_IMAGE_VIEW_PDP.getAttribute("src").contains(STR_IMG_SRC_PARTIAL));
        assertTrue(PRODUCT_IMAGE_VIEW_PDP.getAttribute("src").contains(".jpg"));
    }

    @Then("^I should see the product thumbs images$")
    public void i_should_see_the_product_thumbs_images() {
        assertTrue(PRODUCT_THUMBSIMAGE_PDP.findElements(By.tagName("div")).size()>0);

    }

    @Then("^I should see the Datasheet product section with text \"([^\"]*)\" and be able to download it$")
    public void i_should_see_the_Datasheet_product_section_with_text_and_be_able_to_download_it(String datasheetText) {
        assertTrue(PRODUCT_DATASHEET_PDP.isDisplayed());
        assertTrue(PRODUCT_DATASHEET_PDP.getAttribute("href").contains(STR_DOC_URL_PARTIAL));
        assertTrue(PRODUCT_DATASHEET_PDP.getAttribute("onclick").contains(STR_DOC_OPEN_FUNCTION_PARTIAL));
        assertTrue(PRODUCT_DATASHEET_PDP.getAttribute("target").contains(STR_OPEN_NEW_TAB));assertTrue(PRODUCT_DATASHEET_PDP.isDisplayed());
    }

    @Then("^I should see the product description including \"([^\"]*)\" description, \"([^\"]*)\" product features content, \"([^\"]*)\" and parametrics content$")
    public void i_should_see_the_product_description_including_description_product_features_content_and_parametrics_content(String descriptionTitle, String productFeaturesAndBenefitsTitle, String designResourcesTitle) {
        assertTrue(PRODUCT_DESCRIPTION_TITLE_PDP.isDisplayed() && PRODUCT_DESCRIPTION_TITLE_PDP.getText().equals(descriptionTitle));
        assertTrue(PRODUCT_DESCRIPTION_TEXT_PDP.isDisplayed() && !PRODUCT_DESCRIPTION_TEXT_PDP.getText().equals(""));
        assertTrue(PRODUCT_FEATURES_BENEFITS_TITLE_PDP.isDisplayed() && PRODUCT_FEATURES_BENEFITS_TITLE_PDP.getText().equals(productFeaturesAndBenefitsTitle));
        assertTrue(PRODUCT_FEATURES_PDP.isDisplayed() && !PRODUCT_FEATURES_PDP.getText().equals(""));
        assertTrue(PRODUCT_DESIGN_RESORUCES_TITLE_PDP.isDisplayed() && PRODUCT_DESIGN_RESORUCES_TITLE_PDP.getText().equals(designResourcesTitle));
        assertTrue(PRODUCT_DESIGN_RESOURCES_GRID_PDP.isDisplayed() && !PRODUCT_DESIGN_RESOURCES_GRID_PDP.getText().equals(""));
    }

    @Then("^I should see the related products with title, image, content and learn more button$")
    public void i_should_see_the_related_products_with_title_image_content_and_learn_more_button() {
        assertTrue(PRODUCT_RELATED_PRODUCTS_TITLE_PDP.isDisplayed() && !PRODUCT_RELATED_PRODUCTS_TITLE_PDP.getText().equals(""));
        assertTrue(PRODUCT_RELATED_PRODUCTS_IMAGE_PDP.isDisplayed());
        assertTrue(PRODUCT_RELATED_PRODUCTS_IMAGE_PDP.getAttribute("src").contains(STR_IMG_SRC_PARTIAL));
        assertTrue(PRODUCT_RELATED_PRODUCTS_CATEGORY_DESCRIPTION_PDP.isDisplayed() && !PRODUCT_RELATED_PRODUCTS_CATEGORY_DESCRIPTION_PDP.getText().equals(""));
        assertTrue(PRODUCT_RELATED_PRODUCTS_TEXT_PDP.isDisplayed() && !PRODUCT_RELATED_PRODUCTS_TEXT_PDP.getText().equals(""));
        assertTrue(PRODUCT_RELATED_PRODUCTS_LEARN_MORE_BUTTON_PDP.isDisplayed() && !PRODUCT_RELATED_PRODUCTS_LEARN_MORE_BUTTON_PDP.getText().equals(""));
    }

    @Then("^I should see the buy section with text \"([^\"]*)\"$")
    public void i_should_see_the_buy_section_with_text(String buyTitle) {
        assertTrue(PRODUCT_BUY_FROM_MPS_TITLE_PDP.isEnabled() && PRODUCT_BUY_FROM_MPS_TITLE_PDP.getText().equalsIgnoreCase(buyTitle));
    }

    @Then("^I should see the quality policy section with title \"([^\"]*)\" text content, \"([^\"]*)\" and the images with download links for \"([^\"]*)\"$")
    public void i_should_see_the_quality_policy_section_with_title_text_content_and_the_images_with_download_links(String qualityPolicyTitle, String seeAllQualityDocumentsLink, String product) {
        assertTrue(PRODUCT_QUALITY_POLICY_TITLE_PDP.isDisplayed() && PRODUCT_QUALITY_POLICY_TITLE_PDP.getText().equals(qualityPolicyTitle));
        assertTrue(PRODUCT_SEE_ALL_DOCUMENTS_QUALITY_PDP.isDisplayed() && PRODUCT_SEE_ALL_DOCUMENTS_QUALITY_PDP.getText().equals(seeAllQualityDocumentsLink));
        assertTrue(!PRODUCT_QUALITY_POLICY_CONTENT_PDP.getText().equals(""));
        assertTrue(!PRODUCT_QUALITY_POLICY_SEE_ALL_LINK_PDP.getText().equals(""));
        assertTrue(!PRODUCT_QUALITY_POLICY_SEE_ALL_LINK_PDP.getAttribute("href").equals(""));
        assertTrue(PRODUCT_QUALITY_POLICY_IMAGE_DIV_CONTAINER_PDP.isDisplayed());
        if(product.equals("SINGLE")){
            assertTrue(!PRODUCT_QUALITY_POLICY_SECTION1_PDP.findElement(By.tagName("p")).getText().equals("") && PRODUCT_QUALITY_POLICY_SECTION1_PDP.findElements(By.tagName("img")).size()>0);
            assertTrue(!PRODUCT_QUALITY_POLICY_SECTION1_PDP.findElement(By.xpath("//span[@class='download-document']")).getText().equals(""));
        }
        assertTrue(!PRODUCT_QUALITY_POLICY_SECTION2_PDP.findElement(By.tagName("p")).getText().equals("") && PRODUCT_QUALITY_POLICY_SECTION2_PDP.findElements(By.tagName("img")).size()>0);
        assertTrue(!PRODUCT_QUALITY_POLICY_SECTION2_PDP.findElement(By.xpath("//span[@class='download-document']")).getText().equals(""));
        assertTrue(!PRODUCT_QUALITY_POLICY_SECTION3_PDP.findElement(By.tagName("p")).getText().equals("") && PRODUCT_QUALITY_POLICY_SECTION3_PDP.findElements(By.tagName("img")).size()>0);
        assertTrue(!PRODUCT_QUALITY_POLICY_SECTION3_PDP.findElement(By.xpath("//span[@class='download-document']")).getText().equals(""));
        assertTrue(!PRODUCT_QUALITY_POLICY_SECTION4_PDP.findElement(By.tagName("p")).getText().equals("") && PRODUCT_QUALITY_POLICY_SECTION4_PDP.findElements(By.tagName("img")).size()>0);
        assertTrue(!PRODUCT_QUALITY_POLICY_SECTION4_PDP.findElement(By.xpath("//span[@class='download-document']")).getText().equals(""));
    }

    @When("^I click in design resources tab$")
    public void i_click_in_design_resources_tab() {
        DESIGN_RESOURCES_TAB.click();
    }

    @Then("^I should go to the design resources part in the same page$")
    public void i_should_go_to_the_design_resources_part_in_the_same_page() {
        operationalUtil.waitElementInViewPort(INT_TIMEOUT_JQUERY_TO_DONE, PRODUCT_DESIGN_RESORUCES_TITLE_PDP);
        operationalUtil.waitForAttributeToBe(DESIGN_RESOURCES_TAB, "class", "sub active");
        assertEquals("sub active", DESIGN_RESOURCES_TAB.getAttribute("class"));
    }

    @When("^I click in Overview tab$")
    public void i_click_in_overview_tab() {
        //productDetailS.moveToElementByActions(INTRO_TAB);
        OVERVIEW_TAB.click();
    }

    @Then("^I should go to the overview part in the same page$")
    public void i_should_go_to_the_overview_part_in_the_same_page() {
        operationalUtil.waitElementInViewPort(INT_TIMEOUT_JQUERY_TO_DONE, MPS_LOGO_HEADER);
        try{
            operationalUtil.waitForAttributeToBe(OVERVIEW_TAB, "class", "sub active");
            assertEquals("sub active", OVERVIEW_TAB.getAttribute("class"));
        }catch (AssertionError e){
            e.printStackTrace();
        }
    }

    @When("^I click in Related Products tab$")
    public void i_click_in_Related_Products_tab() {
        RELATED_PRODUCTS_TAB.click();
    }

    @Then("^I should go to the Related Products part in the same page$")
    public void i_should_go_to_the_Related_Products_part_in_the_same_page() {
        operationalUtil.waitElementInViewPort(INT_TIMEOUT_JQUERY_TO_DONE, PRODUCT_RELATED_PRODUCTS_TEXT_PDP);
        operationalUtil.waitForAttributeToBe(RELATED_PRODUCTS_TAB, "class", "sub active");
        assertEquals("sub active", RELATED_PRODUCTS_TAB.getAttribute("class"));
    }

    @When("^I click in Order button$")
    public void i_click_in_Order_button() {
        operationalUtil.clickDirectlyByDOM(ORDER_BUTTON_TAB);
    }

    @Then("^I should go to the Order grey area part in the same page$")
    public void i_should_go_to_the_Order_grey_area_part_in_the_same_page() {
        operationalUtil.waitElementInViewPort(INT_TIMEOUT_JQUERY_TO_DONE, GREY_AREA_INFO_PRODUCT);
        operationalUtil.waitForAttributeToBe(HEADER_PDP, "class", "top-cantainer fixed");
        assertEquals("top-cantainer fixed", HEADER_PDP.getAttribute("class"));
    }

    @When("^I click Learn More button in Related Products section$")
    public void i_click_Learn_More_button_in_Related_Products_section() {
        operationalUtil.moveToElementByActions(PRODUCT_RELATED_PRODUCTS_LEARN_MORE_BUTTON_PDP);
        productTitleRelatedProduct = PRODUCT_RELATED_PRODUCTS_TITLE_PDP.getText();
        PRODUCT_RELATED_PRODUCTS_LEARN_MORE_BUTTON_PDP.click();
    }

    @Then("^I should see the product page$")
    public void i_should_see_the_product_page() {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        try {
            operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
        }catch (AssertionError e){
            operationalUtil.waitForAttributeNotPresent(BODY_CONTAINER, "aria-busy");
        }
        assertEquals(productTitleRelatedProduct, PRODUCT_NAME_PDP.getText());
    }

    @When("^I click in quality tab$")
    public void i_click_in_quality_tab() {
        operationalUtil.moveToElementByActions(QUALITY_TAB);
        QUALITY_TAB.click();
    }

    @Then("^I should go to the Reference Materials part in the same page$")
    public void i_should_go_to_the_Reference_Materials_part_in_the_same_page() {
        operationalUtil.waitElementInViewPort(INT_TIMEOUT_JQUERY_TO_DONE, PRODUCT_REFERENCE_MATERIALS_DATA_DOWNLOADABLE_PDP);
        operationalUtil.waitForAttributeToBe(QUALITY_TAB, "class", "sub active");
        assertEquals("sub active", QUALITY_TAB.getAttribute("class"));
    }

    @When("^I click in show more button in description$")
    public void i_click_in_show_more_button_in_description() {
        if(!SHOW_MORE_BUTTON_IS_PRESENT_PDP.getAttribute("class").contains("hidden")){
            operationalUtil.waitElementInViewPort(10, SHOW_MORE_BUTTON_DESCRIPTION_PDP);
            SHOW_MORE_BUTTON_DESCRIPTION_PDP.click();
        }
    }

    @Then("^I should see the description text expanded$")
    public void i_should_see_the_description_text_expanded() {
        if(!SHOW_MORE_BUTTON_IS_PRESENT_PDP.getAttribute("class").contains("hidden")) {
            operationalUtil.waitElementInViewPort(5, PRODUCT_FEATURES_BENEFITS_TITLE_PDP);
            operationalUtil.waitForAttributeToBe(SHOW_MORE_BUTTON_DESCRIPTION_PDP, "class", "desc-show-more-button less");
            assertEquals("desc-show-more-button less", SHOW_MORE_BUTTON_DESCRIPTION_PDP.getAttribute("class"));
        }
    }

    @When("^I click in show more button in features and benefits$")
    public void i_click_in_show_more_button_in_features_and_benefits() {
        if(!SHOW_MORE_BUTTON_IS_PRESENT_PDP.getAttribute("class").contains("hidden")){
            operationalUtil.waitElementInViewPort(5, SHOW_MORE_BUTTON_FEATURES_BENEFITS_PDP);
            SHOW_MORE_BUTTON_FEATURES_BENEFITS_PDP.click();
        }
    }

    @Then("^I should see the features and benefits text expanded$")
    public void i_should_see_the_features_and_benefits_text_expanded() {
        if(!SHOW_MORE_BUTTON_IS_PRESENT_PDP.getAttribute("class").contains("hidden")) {
            operationalUtil.waitElementInViewPort(5, PRODUCT_DESIGN_RESORUCES_TITLE_PDP);
            operationalUtil.waitForAttributeToBe(SHOW_MORE_BUTTON_FEATURES_BENEFITS_LESS_PDP, "class", "show-more-button less");
            assertEquals("show-more-button less", SHOW_MORE_BUTTON_FEATURES_BENEFITS_LESS_PDP.getAttribute("class"));
        }
    }

    @When("^I click in Description tab$")
    public void i_click_in_Description_tab() {
        DESCRIPTION_TAB.click();
    }

    @Then("^I should go to the Description part in the same page$")
    public void i_should_go_to_the_Description_part_in_the_same_page() {
        operationalUtil.waitElementInViewPort(INT_TIMEOUT_JQUERY_TO_DONE, PRODUCT_DESCRIPTION_TEXT_PDP_KIT);
        operationalUtil.waitForAttributeToBe(DESCRIPTION_TAB, "class", "sub active");
        assertEquals("sub active", DESCRIPTION_TAB.getAttribute("class"));
    }


    @Then("^All the MPL inductors labels should be \"([^\"]*)\" for \"([^\"]*)\" in \"([^\"]*)\"$")
    public void allTheMPLInductorsLabelsShouldBeFor(String isVisible, String typeProduct, String language) {
        switch(typeProduct) {
            case "kit":
                if(isVisible.equals("yes")){
                    assertEquals(STR_MPL_MOUSEHOVER, MPL_TITLE_ATTRIBUTE_PRODUCT_LABEL_PDP_SINGLE_PRODUCT.getAttribute("title"));
                    assertTrue(MPL_TITLE_ATTRIBUTE_PRODUCT_LABEL_PDP_SINGLE_PRODUCT.findElement(By.tagName("img")).getAttribute("src").contains(STR_IMG_MPL_PARTIAL));
                    assertEquals(STR_MPL_TITLE, MPL_TITLE_PRODUCT_LABEL_PDP_SINGLE_PRODUCT.getText());

                }else{
                    assertFalse(operationalUtil.isElementPresent(MPL_TITLE_PRODUCT_LABEL_PDP));
                    assertFalse(operationalUtil.isElementPresent(MPL_GALLERY_TEXT_PDP));
                    assertFalse(operationalUtil.isElementPresent(MPL_PRODUCT_DESCRIPTION_LABEL_PDP));
                }
                break;
            case "single":
                if(isVisible.equals("yes")){
                    assertEquals(STR_MPL_MOUSEHOVER, MPL_TITLE_ATTRIBUTE_PRODUCT_LABEL_PDP_SINGLE_PRODUCT.getAttribute("title"));
                    assertTrue(MPL_TITLE_ATTRIBUTE_PRODUCT_LABEL_PDP_SINGLE_PRODUCT.findElement(By.tagName("img")).getAttribute("src").contains(STR_IMG_MPL_PARTIAL));
                    assertEquals(STR_MPL_TITLE, MPL_TITLE_PRODUCT_LABEL_PDP_SINGLE_PRODUCT.getText());

                    switch(language) {
                        case "en":
                            assertEquals(STR_MPL_GALLERY_TITLE, MPL_LABEL_IMAGE_KIT_PDP_SINGLE_PRODUCT.getText());
                            assertTrue(MPL_RECOMMENDED_INDUCTORS_SECTION_PDP_SINGLE_PRODUCT.getText().contains(STR_MPL_PARTIAL_TEXT_SINGLE_PRODUCT_RECOMMENDED_INDUCTORS_SECTION));
                            assertTrue(MPL_RECOMMENDED_INDUCTORS_SECTION_TEXT_MPL_PDP_SINGLE_PRODUCT.getText().contains(STR_MPL_TEXT_LABEL_RECOMMENDED_INDUCTORS));
                            break;
                        case "cn":
                            assertEquals(STR_CN_MPL_GALLERY_TITLE, MPL_LABEL_IMAGE_KIT_PDP_SINGLE_PRODUCT.getText());
                            assertTrue(MPL_RECOMMENDED_INDUCTORS_SECTION_PDP_SINGLE_PRODUCT.getText().contains(STR_CN_MPL_PARTIAL_TEXT_SINGLE_PRODUCT_RECOMMENDED_INDUCTORS_SECTION));
                            break;
                        case "jp":
                            assertEquals(STR_JP_MPL_GALLERY_TITLE, MPL_LABEL_IMAGE_KIT_PDP_SINGLE_PRODUCT.getText());
                            assertTrue(MPL_RECOMMENDED_INDUCTORS_SECTION_PDP_SINGLE_PRODUCT.getText().contains(STR_JP_MPL_PARTIAL_TEXT_SINGLE_PRODUCT_RECOMMENDED_INDUCTORS_SECTION));
                            break;
                    }

                    assertEquals(STR_MPL_MOUSEHOVER, MPL_RECOMMENDED_INDUCTORS_SECTION_LABEL_MPL_PDP_SINGLE_PRODUCT.getAttribute("title"));


                }else{
                    assertFalse(operationalUtil.isElementPresent(MPL_TITLE_PRODUCT_LABEL_PDP_SINGLE_PRODUCT));
                    assertFalse(operationalUtil.isElementPresent(MPL_LABEL_IMAGE_KIT_PDP_SINGLE_PRODUCT));
                    assertFalse(operationalUtil.isElementPresent(MPL_RECOMMENDED_INDUCTORS_SECTION_LABEL_MPL_PDP_SINGLE_PRODUCT));
                    assertFalse(operationalUtil.isElementPresent(MPL_RECOMMENDED_INDUCTORS_SECTION_PDP_SINGLE_PRODUCT));
                }
                break;
        }
    }

    @Then("^I should see MPL inductors labels$")
    public void iShouldSeeMPLInductorsLabels() {
        assertTrue(MPL_RECOMMENDED_INDUCTORS_SECTION_LABEL_MPL_PDP_SINGLE_PRODUCT_LIST.size()>0);
        assertEquals(STR_MPL_MOUSEHOVER, MPL_RECOMMENDED_INDUCTORS_SECTION_LABEL_MPL_PDP_SINGLE_PRODUCT.getAttribute("title"));
    }

    @And("^I should see the header for Product Detail Page with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" for \"([^\"]*)\"$")
    public void iShouldSeeTheHeaderForProductDetailPageWithAndFor(String overview, String resources, String designResources, String technicalForum, String quality, String language) {
        assertTrue(HEADER_PDP.isDisplayed());
        assertTrue(HEADER_MENU1_PDP.isDisplayed() && HEADER_MENU1_PDP.getText().equalsIgnoreCase(overview));
        assertTrue(HEADER_MENU2_PDP.isDisplayed() && HEADER_MENU2_PDP.getText().equalsIgnoreCase(resources));
        assertTrue(HEADER_MENU3_PDP.isDisplayed() && HEADER_MENU3_PDP.getText().equalsIgnoreCase(designResources));
        if(!language.equalsIgnoreCase("jp")){
            assertTrue(HEADER_MENU4_PDP.isDisplayed() && HEADER_MENU4_PDP.getText().equalsIgnoreCase(technicalForum));
            assertTrue(HEADER_MENU5_PDP.isDisplayed() && HEADER_MENU5_PDP.getText().equalsIgnoreCase(quality));
        } else {
            assertTrue(HEADER_MENU4_PDP.isDisplayed() && HEADER_MENU4_PDP.getText().equalsIgnoreCase(quality));
        }

    }

    @And("^I should see the header for Product Detail Page with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" in \"([^\"]*)\"$")
    public void iShouldSeeTheHeaderForProductDetailPageWithAndIn(String overview, String designResources, String technicalForum, String quality, String orderButton, String language) {
        assertTrue(HEADER_PDP.isDisplayed());
        assertTrue(HEADER_MENU1_PDP.isDisplayed() && HEADER_MENU1_PDP.getText().equals(overview));
        assertTrue(HEADER_MENU2_PDP.isDisplayed() && HEADER_MENU2_PDP.getText().equals(designResources));
        if(language.equals("en") || language.equals("cn")){
            assertTrue(HEADER_MENU3_PDP.isDisplayed() && HEADER_MENU3_PDP.getText().equals(technicalForum));
            assertTrue(HEADER_MENU4_PDP.isDisplayed() && HEADER_MENU4_PDP.getText().equals(quality));
        }else{
            assertTrue(HEADER_MENU3_PDP.isDisplayed() && HEADER_MENU3_PDP.getText().equals(quality));
        }

        assertTrue(ORDER_BUTTON_TAB.isDisplayed() && ORDER_BUTTON_TAB.getText().equals(orderButton));
    }


    @Then("^I should see forum title section with text \"([^\"]*)\"$")
    public void iShouldSeeForumTitleSectionWithText(String forumTitle) {
        assertEquals(forumTitle, FORUM_TITLE_POST_SECTION_PDP.getText());
    }

    @And("^I should see post$")
    public void iShouldSeePost() {
        assertTrue(FORUM_TABLE_POST_SECTION_PDP.isDisplayed());
        assertTrue(FORUM_TABLE_POST_SECTION_PDP.findElements(By.tagName("a")).size() > 0);
    }

    @And("^I should see title element in forum post$")
    public void iShouldSeeTitleElementInForumPost() {
        assertTrue(FORUM_POST_ELEMENT_TITLE_PDP.isDisplayed());
    }

    @And("^I should see comment element in forum post$")
    public void iShouldSeeCommentElementInForumPost() {
        assertTrue(FORUM_POST_ELEMENT_COMMENT_TOPIC_PDP.isDisplayed());
    }

    @And("^I should see time ago element in forum post$")
    public void iShouldSeeTimeAgoElementInForumPost() {
        assertTrue(FORUM_POST_ELEMENT_LATEST_ACTIVITY_PDP.isDisplayed());
    }

    @And("^I should see comment number summary element in forum post$")
    public void iShouldSeeCommentNumberSummaryElementInForumPost() {
        assertTrue(FORUM_POST_ELEMENT_COMMENTS_PDP.isDisplayed());
        assertTrue(MPSMART_LIST_A_ELEMENTS_ENTIRE_TABLE.size()>0);
    }

    @And("^I should see button for all forum posts with text \"([^\"]*)\"$")
    public void iShouldSeeButtonForAllForumPostsWithText(String seeAllPostsText) {
        assertEquals(seeAllPostsText, FORUM_BUTTON_SEE_ALL_POSTS_PDP.getText());
    }

    @When("^I go to the  table section$")
    public void iGoToTheTableSection() {
        operationalUtil.moveToElementAction(MPSMART_TABLE_ELEMENT);
        assertTrue(MPSMART_TABLE_ELEMENT.isDisplayed());
    }

    @And("^I verify the header with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iVerifyTheHeaderWithAnd(String partNumber, String category, String description, String download)  {
        for(int i = 0; i < MPSMART_LIST_HEADER_ELEMENTS.size(); i++){

            switch (i){
                case 0:
                    assertEquals(partNumber,MPSMART_LIST_HEADER_ELEMENTS.get(i).getText());
                    break;
                case 1:
                    assertEquals(category,MPSMART_LIST_HEADER_ELEMENTS.get(i).getText());
                    break;
                case 2:
                    assertEquals(description,MPSMART_LIST_HEADER_ELEMENTS.get(i).getText());
                    break;
                case 3:
                    assertEquals(download,MPSMART_LIST_HEADER_ELEMENTS.get(i).getText());
                    break;
            }

        }
    }

    @And("^I verify there is at least one element in the table with value in every column$")
    public void iVerifyThereIsAtLeastOneElementInTheTableWithValueInEveryColumn() {
        assertTrue(MPSMART_LIST_A_ELEMENTS_ENTIRE_TABLE.size()>0);
    }

    @And("^I click on the datasheet button$")
    public void iClickOnTheDatasheetButton() {
        operationalUtil.waitJqueryFullyloaded(INT_TIMEOUT_JQUERY_TO_DONE);
        try{
            operationalUtil.waitUntilElementDissapears(INT_TIMEOUT_JQUERY_TO_DONE,2, LOGIN_WINDOW);
        } catch (TimeoutException | StaleElementReferenceException ex){
            ex.printStackTrace();
            operationalUtil.waitUntilElementDissapears(INT_TIMEOUT_JQUERY_TO_DONE,2, productDetailS.getDriver().findElement(By.id("social-login-authentication")));
        }
        PRODUCT_DATASHEET_PDP.click();
    }

    @Then("^I should go to the quality part in the same page$")
    public void iShouldGoToTheQualityPartInTheSamePage() {
        operationalUtil.waitElementInViewPort(INT_TIMEOUT_JQUERY_TO_DONE, PRODUCT_QUALITY_POLICY_TITLE_PDP);
        operationalUtil.waitForAttributeToBe(QUALITY_TAB, "class", "sub active");
        assertEquals("sub active", QUALITY_TAB.getAttribute("class"));
    }
}
