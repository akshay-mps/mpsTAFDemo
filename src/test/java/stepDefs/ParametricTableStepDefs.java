package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ParametricTable;
import utility.operationalUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Resources.ProjectConstants.STR_MPL_LABEL_TEXT_FILTER_SECTION_PARAMETRIC_TABLE;
import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static pages.ParametricTable.*;
import static utility.operationalUtils.mapMin_Max;
import static utility.operationalUtils.splitInListSeparatedByComma;


public class ParametricTableStepDefs {

    public ParametricTable parametricTable;
    public operationalUtils operationalUtil;

    public Map<String, String> mapMinMax_Slider = new HashMap<>();
    public List<WebElement> listTempWebElement = new ArrayList<>();
    public ArrayList<String> listTemp = new ArrayList<>();
    private BaseTest bt = new BaseTest();


    public ParametricTableStepDefs() {
        WebDriver driver = bt.getDriver();
        this.parametricTable = new ParametricTable(driver);
        this.operationalUtil = new operationalUtils(driver);
    }

    @When("^I wait the complete page load$")
    public void i_wait_the_complete_page_load() {
        operationalUtil.waitElementVisibility(3, ALL_PARAMETRIC_TABLE);
    }

    @Then("^I should see the status \"([^\"]*)\"$")
    public void i_should_see_the_status(String status) {
        listTempWebElement = STATUS_FIELD_PARAMETRIC_TABLE.findElements(By.tagName("label"));
        if(!status.equals("skip")) {
            listTemp = splitInListSeparatedByComma(status);
            for (String item : listTemp) {
                assertTrue(operationalUtil.executeJavascriptCheckbox_isChecked(item, "mps_status_multi"));
            }
            listTemp.clear();
            mapMinMax_Slider.clear();
        }
    }

    @Then("^I should see the module assmebly \"([^\"]*)\"$")
    public void i_should_see_the_module_assmebly(String moduleAssembly) {
        if(!moduleAssembly.equals("skip")) {
            listTemp = splitInListSeparatedByComma(moduleAssembly.replace("%20", " "));
            for (String item : listTemp) {
                assertTrue(operationalUtil.executeJavascriptCheckbox_isChecked(item, "moduleassembly_multi"));
            }
            listTemp.clear();
            mapMinMax_Slider.clear();
        }
    }

    @Then("^I should see vin \\(min\\) \\(v\\) \"([^\"]*)\"$")
    public void i_should_see_vin_min_v(String vinmin) {
        if(!vinmin.equals("skip")) {
            listTemp = splitInListSeparatedByComma(vinmin);
            for (int i = 0; i < listTemp.size(); i++) {
                if (listTemp.get(i).length() == 3 && listTemp.get(i).substring(listTemp.get(i).length() - 1).equals("0")) {
                    listTemp.set(i, listTemp.get(i).substring(0, listTemp.get(i).indexOf(".")));
                }
            }
            mapMinMax_Slider = mapMin_Max(VINMINV_FIELD_PARAMETRIC_TABLE.getText());
            assertEquals(listTemp.get(0), mapMinMax_Slider.get("min"));
            assertEquals(listTemp.get(1), mapMinMax_Slider.get("max"));
            listTemp.clear();
            mapMinMax_Slider.clear();
        }
    }

    @Then("^I should see vin \\(max\\) \\(v\\) \"([^\"]*)\"$")
    public void i_should_see_vin_max_v(String vinmax) {
        if(!vinmax.equals("skip")) {
            listTemp = splitInListSeparatedByComma(vinmax);
            for (int i = 0; i < listTemp.size(); i++) {
                if (listTemp.get(i).length() == 3 && listTemp.get(i).substring(listTemp.get(i).length() - 1).equals("0")) {
                    listTemp.set(i, listTemp.get(i).substring(0, listTemp.get(i).indexOf(".")));
                }
            }
            mapMinMax_Slider = mapMin_Max(VINMAXV_FIELD_PARAMETRIC_TABLE.getText());
            assertEquals(listTemp.get(0), mapMinMax_Slider.get("min"));
            assertEquals(listTemp.get(1), mapMinMax_Slider.get("max"));
            listTemp.clear();
            mapMinMax_Slider.clear();
        }
    }

    @Then("^I should see vout \\(min\\) \\(v\\) \"([^\"]*)\"$")
    public void i_should_see_vout_min_v(String voutminv) {
        if(!voutminv.equals("skip")) {
            listTemp = splitInListSeparatedByComma(voutminv);
            for (int i = 0; i < listTemp.size(); i++) {
                if (listTemp.get(i).length() == 3 && listTemp.get(i).substring(listTemp.get(i).length() - 1).equals("0")) {
                    listTemp.set(i, listTemp.get(i).substring(0, listTemp.get(i).indexOf(".")));
                }
            }
            mapMinMax_Slider = mapMin_Max(VOUTMINV_FIELD_PARAMETRIC_TABLE.getText());
            assertEquals(listTemp.get(0), mapMinMax_Slider.get("min"));
            assertEquals(listTemp.get(1), mapMinMax_Slider.get("max"));
            listTemp.clear();
            mapMinMax_Slider.clear();
        }
    }

    @Then("^I should see vout \\(max\\) \\(v\\) \"([^\"]*)\"$")
    public void i_should_see_vout_max_v(String voutvinmaxv) {
        if(!voutvinmaxv.equals("skip")) {
            listTemp = splitInListSeparatedByComma(voutvinmaxv);
            for (int i = 0; i < listTemp.size(); i++) {
                if (listTemp.get(i).length() == 3 && listTemp.get(i).substring(listTemp.get(i).length() - 1).equals("0")) {
                    listTemp.set(i, listTemp.get(i).substring(0, listTemp.get(i).indexOf(".")));
                }
            }
            mapMinMax_Slider = mapMin_Max(VOUTMAXV_FIELD_PARAMETRIC_TABLE.getText());
            assertEquals(listTemp.get(0), mapMinMax_Slider.get("min"));
            assertEquals(listTemp.get(1), mapMinMax_Slider.get("max"));
            listTemp.clear();
            mapMinMax_Slider.clear();
        }
    }

    @Then("^I should see regulated outputs \"([^\"]*)\"$")
    public void i_should_see_regulated_outputs(String regulatedOutputs) {
        if(!regulatedOutputs.equals("skip")) {
            listTemp = splitInListSeparatedByComma(regulatedOutputs);
            for (String item : listTemp) {
                assertTrue(operationalUtil.executeJavascriptCheckbox_isChecked(item, "regulatedoutputs"));
            }
            listTemp.clear();
            mapMinMax_Slider.clear();
        }
    }

    @Then("^I should see iout \\(max\\) \\(A\\) \"([^\"]*)\"$")
    public void i_should_see_iout_max_A(String ioutmax) {
        if(!ioutmax.equals("skip")) {
            listTemp = splitInListSeparatedByComma(ioutmax);
            for (int i = 0; i < listTemp.size(); i++) {
                if (listTemp.get(i).length() == 3 && listTemp.get(i).substring(listTemp.get(i).length() - 1).equals("0")) {
                    listTemp.set(i, listTemp.get(i).substring(0, listTemp.get(i).indexOf(".")));
                }
            }
            mapMinMax_Slider = mapMin_Max(IOUTMAX_FIELD_PARAMETRIC_TABLE.getText());
            assertEquals(listTemp.get(0), mapMinMax_Slider.get("min"));
            assertEquals(listTemp.get(1), mapMinMax_Slider.get("max"));
            listTemp.clear();
            mapMinMax_Slider.clear();
        }
    }

    @Then("^I should see IC interface \"([^\"]*)\"$")
    public void i_should_see_IC_interface(String checkbox) {
        if(!checkbox.equals("skip")) {
            listTemp = splitInListSeparatedByComma(checkbox);
            for (String item : listTemp) {
                assertTrue(operationalUtil.executeJavascriptCheckbox_isChecked(item, "i2cinterface"));
            }
            listTemp.clear();
            mapMinMax_Slider.clear();
        }
    }

    @Then("^I should see MPL inductors labels in the parametric table$")
    public void iShouldSeeMPLInductorsLabelsInTheParametricTable() {
        assertTrue(MPL_SEPARATOR_FILTER_PARAMETRIC_TABLE.isDisplayed());
        assertTrue(MPL_TITLE_FILTER_PARAMETRIC_TABLE.isDisplayed());
        assertTrue(MPL_INDUCTOR_LABEL_FILTER_PARAMETRIC_TABLE.isDisplayed());
        assertEquals(STR_MPL_LABEL_TEXT_FILTER_SECTION_PARAMETRIC_TABLE, MPL_TITLE_FILTER_PARAMETRIC_TABLE.getText());
        assertTrue(MPL_INDUCTOR_LABEL_TABLE_PARAMETRIC_TABLE.size()==1);
    }
}
