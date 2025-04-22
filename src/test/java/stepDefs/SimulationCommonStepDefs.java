package stepDefs;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.SimulationElements;
import utility.operationalUtils;

import java.util.stream.Stream;

import static Resources.ProjectConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pages.CommonElements.BODY_CONTAINER;
import static pages.SimulationElements.*;
import static utility.operationalUtils.genUniqueStrFromCurrentTime;
import static utility.operationalUtils.replaceTextByKeyboardKeys;

public class SimulationCommonStepDefs {

    public SimulationElements simulationElements;
    public operationalUtils operationalUtil;
    public String designName;
    private BaseTest bt = new BaseTest();


    public SimulationCommonStepDefs() {
        WebDriver driver = bt.getDriver();
        this.simulationElements = new SimulationElements(driver);
        this.operationalUtil = new operationalUtils(driver);
    }

    @And("^Wait loading image disappear$")
    public void wait_loading_image_disappear(){ operationalUtil.elementNotPresent(LOADING_SIMULATION_IMAGE, INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @And("^Wait for JS$")
    public void wait_for_js(){ operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE); }
    @When("^I move mouse to \"Login Load your Design\" button$")
    public  void i_move_mouse_to_login_load_your_design_button() { operationalUtil.moveToElementByActions(LOGIN_LOAD_YOUR_DESIGN_BUTTON); }
    @And("^I click on the \"Login Load your Design\" button$")
    public  void i_click_on_the_login_load_your_design_button() {
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        LOGIN_LOAD_YOUR_DESIGN_BUTTON.click();
    }
    @Then("^\"Loading Simulation\" procedure can complete within 30 seconds")
    public void loading_simulation_procedure_can_complete_within_30_seconds() { operationalUtil.assertElementNotPresent(LOADING_SIMULATION_IMAGE, INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @Then("^\"Advanced Specs\" could not be clickable$")
    public void advanced_specs_could_not_be_clickable(){ operationalUtil.elementNotClickable(ADVANCED_SPECS_BUTTON,INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @And("^\"View Waveforms\" could not be clickable$")
    public void view_waveforms_could_not_be_clickable(){ operationalUtil.assertElementNotClickable(VIEW_WAVEFORMS_BUTTON,INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @And("^\"View Custom Datasheet\" could not be clickable$")
    public void view_custome_datasheet_could_not_be_clickable(){ operationalUtil.assertElementNotClickable(VIEW_CUSTOM_DATASHEET_BUTTON,INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @And("^\"Save Specs\" could not be clickable$")
    public  void save_specs_could_not_be_clickable(){ operationalUtil.assertElementNotClickable(SAVE_SPECS_BUTTON,INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @And("^I input value \"([^\"]*)\" in VinTypInput$")
    public  void I_input_value_in_VinTypInput(String InputValue){ replaceTextByKeyboardKeys(VinTyp_Input,InputValue); }
    @And("^I input value \"([^\"]*)\" in VinMinInput$")
    public  void I_input_value_in_VinMinInput(String InputValue){ replaceTextByKeyboardKeys(VinMin_Input,InputValue); }
    @And("^I input value \"([^\"]*)\" in VinMaxInput$")
    public  void I_input_value_in_VinMaxInput(String InputValue){ replaceTextByKeyboardKeys(VinMax_Input,InputValue);}
    @And("^I input value \"([^\"]*)\" in VoutInput$")
    public  void I_input_value_in_VoutInput(String InputValue){ replaceTextByKeyboardKeys(VinVout_Input,InputValue);}
    @And("^I input value \"([^\"]*)\" in IoutInput$")
    public  void I_input_value_in_IoutInput(String InputValue){ replaceTextByKeyboardKeys(Iout_Input,InputValue); }
    @And("^I click VoutInput button$")
    public void i_click_VoutInput_button(){ VinVout_Input.click(); }
    @Then("^\"Advanced Specs\" could be clickable$")
    public void advanced_specs_could_be_clickable(){ operationalUtil.assertElementClickable(ADVANCED_SPECS_BUTTON); }
    @And("^\"View Waveforms\" could be clickable$")
    public void view_waveforms_could_be_clickable(){ operationalUtil.assertElementClickable(VIEW_WAVEFORMS_BUTTON); }
    @And("^\"View Custom Datasheet\" could be clickable$")
    public void view_custom_datasheet_could_be_clickable(){ operationalUtil.assertElementClickable(VIEW_CUSTOM_DATASHEET_BUTTON); }
    @And("^I click Advanced Specs button$")
    public void i_click_advanced_specs_button(){
        operationalUtil.waitForAllJS(INT_TIMEOUT_JQUERY_TO_DONE);
        ADVANCED_SPECS_BUTTON.click();
    }
    @And("^I click View Waveforms button$")
    public void i_click_view_waveforms_button(){ VIEW_WAVEFORMS_BUTTON.click(); }
    @Then("^Waveforms can pop up$")
    public void waveforms_can_pop_up(){ operationalUtil.assertElementPresent(WAVEFORMSPOP); }
    @And("^I click View Design button$")
    public void i_click_view_design_button(){ VIEWDATASHEET.click(); }
    @Then("^\"Generating Custom Datasheet\" pop up$")
    public void generating_custom_datasheet_popo_up(){ operationalUtil.assertElementPresent(GENERATINGCUSTOMDATASHEET); }
    @And("^Wait \"Generating Custom Datasheet\" finish$")
    public void generating_custom_datasheet(){ operationalUtil.waitElementInvisibility(INT_TIMEOUT_GENERAT_DATASHEET_TO_DONE,GENERATINGCUSTOMDATASHEET); }
    @And("^I click Save Specs button$")
    public void i_click_save_specs_button(){ SAVE_SPECS_BUTTON.click(); }
    @Then("^\"Enter New Design name\" pop up$")
    public void enter_new_design_name_pop_up(){ operationalUtil.assertElementPresent(NEWDESIGNNAMEPOPUP); }
    @Given("^Enter new name in \"Enter New Design name\"$")
    public void enter_new_name_in_enter_new_design_name(){
        designName = genUniqueStrFromCurrentTime();
        operationalUtil.waitElementClickability(INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT,CURRENTCONFIGDIALOG);
        CURRENTCONFIGDIALOG.click();
        operationalUtil.inputByAction(CURRENTCONFIGDIALOG,designName);
    }
    @And("^I click OK on \"Enter New Design name\"$")
    public void i_click_ok_on_enter_new_design_name(){ ENTER_NEW_DESIGN_NAME_OK_BUTTON.click(); }
    @Then("^Name should appear in the list$")
    public void name_should_in_the_list(){ assertEquals(FIRSTNAMEINLIST.getText().toString(),"Untitled"+designName); }
    @Then("^Advanced Specification Alert pop up$")
    public void advanced_specification_alert_pop_up(){ operationalUtil.assertElementPresent(AD_SPE_ALERT_WINDOW, INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @And("^Wait \"Committing Final Design\" disappear$")
    public void wait_committing_final_design_disappear (){ operationalUtil.waitElementInvisibility(INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT,LOADINGOVERLAY); }
    @And("^I click Vin Typ. Input button$")
    public void i_click_vin_typ_input_button(){ VinTyp_Input.click(); }
    @Then("^I should see that \"Required Fields\" appear$")
    public void i_should_see_requied_fields_appear(){ operationalUtil.assertElementPresent(PRODUCTOPTIONWRAPPER); }
    @Then("^I should see that \"Required Fields\" don't appear$")
    public void i_should_see_requied_fields_not_appear(){ operationalUtil.assertElementNotPresent(PRODUCTOPTIONWRAPPER, INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @Then("^\"field required\" should not display$")
    public void field_required_should_not_display(){ operationalUtil.assertElementNotPresent(FIELDREQUIRED, INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @And("^I click Light Load Mode tip button$")
    public void i_click_light_load_mode_tip_button(){ LIGHT_LOAD_MODE_TIP_IMAGE.click(); }
    @And("^I click Compensation tip button$")
    public void i_click_compensation_tip(){ COMPENSATION_TIP_IMAGE.click(); }
    @And("^I click Switching tip button$")
    public void i_click_switching_tip(){ SWITCHING_TIP_IMAGE.click(); }
    @And("^I click Input Protection tip button$")
    public void i_click_protection_tip(){ INPUT_PROTECTION_TIP_IMAGE.click(); }
    @And("^I click Input SS Time tip button$")
    public void i_click_ss_time_tip(){ SS_TIME_TIME_IAMGE.click(); }
    @And("^I click Valley Current Limit tip button$")
    public void i_click_valley_current_limit_tip(){ VALLEY_CURRENT_LIMIT_TIP_IMAGE.click();}
    @And("^I click Peak Current Limit tip button$")
    public void i_click_peak_current_limit_tip(){ PEAK_CURRENT_LIMIT_TIP_IMAGE.click();}
    @And("^I click OC Response Mode tip button$")
    public void i_click_oc_response_tip(){ OC_RESPONSE_MODE_TIP_IMAGE.click();}
    @And("^I click OVP tip button$")
    public void i_click_ovp_tip(){ OVP_TIP_IMAGE.click();}
    @And("^I click UVP tip button$")
    public void i_click_uvp_tip(){ UVP_TIP_IMAGE.click();}
    @And("^Close Light Load Mode tip$")
    public void close_light_load_tip(){ CLOSE_LIGHT_LOAD_MODE_TIP.click(); }
    @And("^Close Compensation tip$")
    public void close_compensation_tip(){ CLOSE_COMPENSATION_TIP.click();}
    @And("^Close Switching tip$")
    public void close_switching_tip(){ CLOSE_SWITCHING_TIP.click();}
    @And("^Close Protection tip$")
    public void close_protection_tip(){ CLOSE_INPUT_PROTECTION_TIP.click();}
    @And("^Close SS Time tip$")
    public void close_ss_time_tip(){ CLOSE_SS_TIME_TIME.click();}
    @And("^Close Valley Current Limit tip$")
    public void close_valley_current_limit_tip(){ CLOSE_VALLEY_CURRENT_LIMIT_TIP.click();}
    @And("^Close Peak Current Limit tip$")
    public void close_peak_current_limit_tip(){ CLOSE_PEAK_CURRENT_LIMIT_TIP.click();}
    @And("^Close OC Response Mode tip$")
    public void close_oc_response_mode_tip(){ CLOSE_OC_RESPONSE_MODE_TIP.click();}
    @And("^Close OVP tip$")
    public void close_ovp_tip(){ CLOSE_OVP_TIP.click();}
    @And("^Close UVP tip$")
    public void close_uvp_tip(){ CLOSE_UVP_TIP.click();}
    @Then("^Light Load Mode tip pop up$")
    public void light_load_mode_tip_popup() { operationalUtil.assertElementPresent(LIGHT_LOAD_MODE_TIP); }
    @Then("^Compensation tip pop up$")
    public void compensation_tip_popup() { operationalUtil.assertElementPresent(COMPENSATION_TIP); }
    @Then("^Switching tip pop up$")
    public void switching_tip_popup() { operationalUtil.assertElementPresent(SWITCH_TIP); }
    @Then("^Protection tip pop up$")
    public void protection_tip_popup() { operationalUtil.assertElementPresent(VIN_THRESHOLD_TIP); }
    @Then("^SS Time tip pop up$")
    public void ss_time_tip_popup() { operationalUtil.assertElementPresent(SS_TIME_TIP); }
    @Then("^Valley Current Limit tip pop up$")
    public void valley_current_limit_tip_popup() { operationalUtil.assertElementPresent(VALLEY_CURRENT_LIMINIT_THRESHOLD_TIP); }
    @Then("^Peak Current Limit tip pop up$")
    public void peak_current_limit_tip_popup() { operationalUtil.assertElementPresent(PEAK_CURRENT_LIMIT_THRESHOLD_TIP); }
    @Then("^OC Response Mode tip pop up$")
    public void oc_response_mode_tip_popup() { operationalUtil.assertElementPresent(OC_RESPONSEMODE_TIP); }
    @Then("^OVP tip pop up$")
    public void ovp_tip_popup() { operationalUtil.assertElementPresent(VO_OVP_MODE_TIP); }
    @Then("^UVP tip pop up$")
    public void uvp_tip_popup() { operationalUtil.assertElementPresent(VO_UVP_MODE_TIP); }
    @And("^Close cover image on the down-left corner$")
    public void close_cover_image_down_left(){
        operationalUtil.waitElementVisibility(INT_TIMEOUT_LIVE_CHAT_SHOWN,DOWN_LEFT_CORNER);
        DOWN_LEFT_CORNER.click();
    }
    @And("^I select \"([^\"]*)\" in the RTH of mezdpd4506as$")
    public void i_select_value_in_the_RTH(String value){ operationalUtil.clickOptionWithValue(RTH_SELECT,value); }
    @And("^I select \"([^\"]*)\" in the CTH of mezdpd4506as$")
    public void i_select_value_in_the_CTH(String value){ operationalUtil.clickOptionWithValue(CTH_SELECT,value); }
    @And("^I select \"([^\"]*)\" in the CTHP of mezdpd4506as$")
    public void i_select_value_in_the_CTHP(String value){ operationalUtil.clickOptionWithValue(CTHP_SELECT,value); }
    @And("^I select \"([^\"]*)\" in the Slope of mezdpd4506as$")
    public void i_select_value_in_the_Slope(String value){ operationalUtil.clickOptionWithValue(SLOPE_SELECT,value); }
    @And("^I select \"([^\"]*)\" in the GM of mezdpd4506as")
    public void i_select_value_in_the_GM(String value){ operationalUtil.clickOptionWithValue(GM_SELECT,value); }
    @And("^Click Ok on Advanced Specs$")
    public void close_advaned_specs(){
        operationalUtil.moveToElementAction(ADVANCED_SPECS_OK);
        ADVANCED_SPECS_OK.click();
    }
    @And("^Close View Waveforms$")
    public void close_view_waveforms(){ operationalUtil.clickDirectlyByDOM(VIEW_WAVEFORMS_OK); }
    @And("^Wait loading image disppear in WaveForms$")
    public void wait_loading_image_disppear(){ operationalUtil.waitElementInvisibility(INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT, LOADING_IN_WAVEFORMS); }
    @And("^Wait loading design image disppear$")
    public void wait_loading_design_image_disppear(){ operationalUtil.waitElementInvisibility(INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT, LOADING_DESIGN_WAVEFORMS); }
    @And("^I click Bode Plot$")
    public void i_click_bode_plot(){ BODE_PLOT_BUTTON.click(); }
    @Then("RTH value should be \"([^\"]*)\" in mezdpd4506as")
    public void rth_value_should_be_as_value(String value){ operationalUtil.assertSelectedValue(RTH_SELECT, value); }
    @Then("CTH value should be \"([^\"]*)\" in mezdpd4506as")
    public void cth_value_should_be_as_value(String value){ operationalUtil.assertSelectedValue(CTH_SELECT, value); }
    @Then("CTHP value should be \"([^\"]*)\" in mezdpd4506as")
    public void cthp_value_should_be_as_value(String value){ operationalUtil.assertSelectedValue(CTHP_SELECT, value); }
    @Then("Slope value should be \"([^\"]*)\" in mezdpd4506as")
    public void slope_value_should_be_as_value(String value){ operationalUtil.assertSelectedValue(SLOPE_SELECT, value); }
    @Then("GM value should be \"([^\"]*)\" in mezdpd4506as")
    public void gm_value_should_be_as_value(String value){ operationalUtil.assertSelectedValue(GM_SELECT, value); }
    @And("^I select \"([^\"]*)\" in the Rcomp of mezdpd3603as$")
    public void i_select_value_in_the_Rcomp(String value){ operationalUtil.clickOptionWithValue(RCOMP_SELECT,value); }
    @And("^I select \"([^\"]*)\" in the RT of mezdpd3603as$")
    public void i_select_value_in_the_RT(String value){ operationalUtil.clickOptionWithValue(RT_SELECT,value); }
    @And("^I select \"([^\"]*)\" in the Ccomp1 of mezdpd3603as$")
    public void i_select_value_in_the_Ccomp1(String value){ operationalUtil.clickOptionWithValue(CCOMP1_SELECT,value); }
    @And("^I select \"([^\"]*)\" in the Slope of mezdpd3603as")
    public void i_select_value_in_the_Slope_mezdpd3603as(String value){ operationalUtil.clickOptionWithValue(VPP_SELECT,value); }
    @Then("Rcomp value should be \"([^\"]*)\" in mezdpd3603as")
    public void rcomp_value_should_be_as_value(String value){ operationalUtil.assertSelectedValue(RCOMP_SELECT, value); }
    @Then("RT value should be \"([^\"]*)\" in mezdpd3603as")
    public void rt_value_should_be_as_value(String value){ operationalUtil.assertSelectedValue(RT_SELECT, value); }
    @Then("Ccomp1 value should be \"([^\"]*)\" in mezdpd3603as")
    public void ccomp1_value_should_be_as_value(String value){ operationalUtil.assertSelectedValue(CCOMP1_SELECT, value); }
    @Then("Slope value should be \"([^\"]*)\" in mezdpd3603as")
    public void mezdpd3603as_slope_value_should_be_as_value(String value){ operationalUtil.assertSelectedValue(VPP_SELECT, value); }
    @And("^I click Effciency button$")
    public void i_click_effciency_button(){ operationalUtil.clickDirectlyByDOM(EFFICIENCY_BUTTON); }
    @And("^I select \"([^\"]*)\" from FswSelect$")
    public void i_select_value_from_FswSelect(String FswValue){ operationalUtil.clickOptionWithValue(FSW_SELECT,FswValue); }
    @And("^I select \"([^\"]*)\" from SWITCHING_FRE$")
    public void i_select_value_from_SWITCHING_FRE(String FswValue){ operationalUtil.clickOptionWithValue(SWITCHING_FRE,FswValue); }
    @And("^Close down-left IMAGE$")
    public void close_down_left_image(){
        if(DOWN_LEFT_IMAGE.getAttribute("class").contains("_hj-f5b2a1eb-9b07_widget_open")){
            DOWN_LEFT_CORNER.click();
        }
    }
    @And("^I select option \"([^\"]*)\" from VoutRange$")
    public void i_select_value_3_from_VoutRange(String VoutRangeValue){ operationalUtil.clickOptionWithValue(VOUTRANGE_SELECT,VoutRangeValue); }
    @Then("^VOUT_SCALE change to \"([^\"]*)\"$")
    public void VoutScale_change_to_value_3(String Vout_Scale_value){ assertEquals("0.125",VOSCALE_INPUT.getAttribute("value")); }
    @Then("^No alert pop up after click OK on Advanced Specification$")
    public void no_alert_pop_up_after_click_ok(){ operationalUtil.assertElementNotPresent(AD_SPE_ALERT_WINDOW, INT_TIMEOUT_WAIT_PRESENT_VISIBLE_CLICKABLE_ORNOT); }
    @And("^I input invalid higher value on VoMarginHigh$")
    public void i_input_invalid_higher_value_on_VoMarginHigh(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(VoMarginHigh); }
    @And("^I input invalid higher value on VoMarginLow$")
    public void i_input_invalid_higher_value_on_VoMarginLow(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(VoMarginLow); }
    @And("^I input invalid higher value on voMin$")
    public void i_input_invalid_higher_value_on_voMin(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(voMin); }
    @And("^I input invalid higher value on voMax$")
    public void i_input_invalid_higher_value_on_voMax(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(voMax); }
    @And("^I input invalid higher value on vinOn$")
    public void i_input_invalid_higher_value_on_vinOn(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(vinOn); }
    @And("^I input invalid higher value on vinOff$")
    public void i_input_invalid_higher_value_on_vinOff(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(vinOff); }
    @And("^I input invalid higher value on TonDelay$")
    public void i_input_invalid_higher_value_on_TonDelay(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(TonDelay); }
    @And("^I input invalid higher value on hiccupItv$")
    public void i_input_invalid_higher_value_on_hiccupItv(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(hiccupItv); }
    @And("^I input invalid higher value on ocLimit$")
    public void i_input_invalid_higher_value_on_ocLimit(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(ocLimit); }
    @And("^I input invalid higher value on iOutOcFaultLimit$")
    public void i_input_invalid_higher_value_on_iOutOcFaultLimit(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(iOutOcFaultLimit); }
    @And("^I input invalid higher value on iOutOcWarnLimit$")
    public void i_input_invalid_higher_value_on_iOutOcWarnLimit(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(iOutOcWarnLimit); }
    @And("^I input invalid higher value on vinOvFaultLimit$")
    public void i_input_invalid_higher_value_on_vinOvFaultLimit(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(vinOvFaultLimit); }
    @And("^I input invalid higher value on vinOvWarnLimit$")
    public void i_input_invalid_higher_value_on_vinOvWarnLimit(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(vinOvWarnLimit); }
    @And("^I input invalid higher value on vinUvWarnLimit$")
    public void i_input_invalid_higher_value_on_vinUvWarnLimit(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(vinUvWarnLimit); }
    @And("^I input invalid higher value on OTFaultLimit$")
    public void i_input_invalid_higher_value_on_OTFaultLimit(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(OTFaultLimit); }
    @And("^I input invalid higher value on OTWarnLimit$")
    public void i_input_invalid_higher_value_on_OTWarnLimit(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(OTWarnLimit); }
    @And("^I input invalid higher value on ADDR$")
    public void i_input_invalid_higher_value_on_ADDR(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(ADDR); }
    @And("^I input invalid higher value on TSS$")
    public void i_input_invalid_higher_value_on_TSS(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(TSS); }
    @And("^I input invalid higher value on ToffDelay$")
    public void i_input_invalid_higher_value_on_ToffDelay(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(ToffDelay); }
    @And("^I input invalid higher value on TOFFFALL$")
    public void i_input_invalid_higher_value_on_TOFFFALL(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(TOFFFALL); }
    @And("^I input invalid higher value on VOMARGINHIGH$")
    public void i_input_invalid_higher_value_on_VOMARGINHIGH(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(VOMARGINHIGH); }
    @And("^I input invalid higher value on VOMARGINLOW$")
    public void i_input_invalid_higher_value_on_VOMARGINLOW(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(VOMARGINLOW); }
    @And("^I input invalid higher value on VOMAX$")
    public void i_input_invalid_higher_value_on_VOMAX(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(VOMAX); }
    @And("^I input invalid higher value on OTLIMIT$")
    public void i_input_invalid_higher_value_on_OTLIMIT(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(OTLIMIT); }
    @And("^I input invalid higher value on RAILADDRESS$")
    public void i_input_invalid_higher_value_on_RAILADDRESS(){ operationalUtil.inputInvalidHighValueFromWebEleTitle(RAILADDRESS); }
    @And("^I input invalid lower value on VoMarginHigh$")
    public void i_input_invalid_lower_value_on_VoMarginHigh() { operationalUtil.inputInvalidLowValueFromWebEleTitle(VoMarginHigh); }
    @And("^I input invalid lower value on VoMarginLow$")
    public void i_input_invalid_lower_value_on_VoMarginLow() { operationalUtil.inputInvalidLowValueFromWebEleTitle(VoMarginLow); }
    @And("^I input invalid lower value on voMin$")
    public void i_input_invalid_lower_value_on_voMin() { operationalUtil.inputInvalidLowValueFromWebEleTitle(voMin); }
    @And("^I input invalid lower value on voMax$")
    public void i_input_invalid_lower_value_on_voMax() { operationalUtil.inputInvalidLowValueFromWebEleTitle(voMax); }
    @And("^I input invalid lower value on vinOn$")
    public void i_input_invalid_lower_value_on_vinOn() { operationalUtil.inputInvalidLowValueFromWebEleTitle(vinOn); }
    @And("^I input invalid lower value on vinOff$")
    public void i_input_invalid_lower_value_on_vinOff() { operationalUtil.inputInvalidLowValueFromWebEleTitle(vinOff); }
    @And("^I input invalid lower value on TonDelay$")
    public void i_input_invalid_lower_value_on_TonDelay() { operationalUtil.inputInvalidLowValueFromWebEleTitle(TonDelay); }
    @And("^I input invalid lower value on ToffDelay$")
    public void i_input_invalid_lower_value_on_ToffDelay() { operationalUtil.inputInvalidLowValueFromWebEleTitle(ToffDelay); }
    @And("^I input invalid lower value on hiccupItv$")
    public void i_input_invalid_lower_value_on_hiccupItv() { operationalUtil.inputInvalidLowValueFromWebEleTitle(hiccupItv); }
    @And("^I input invalid lower value on ocLimit$")
    public void i_input_invalid_lower_value_on_ocLimit() { operationalUtil.inputInvalidLowValueFromWebEleTitle(ocLimit); }
    @And("^I input invalid lower value on iOutOcFaultLimit$")
    public void i_input_invalid_lower_value_on_iOutOcFaultLimit() { operationalUtil.inputInvalidLowValueFromWebEleTitle(iOutOcFaultLimit); }
    @And("^I input invalid lower value on iOutOcWarnLimit$")
    public void i_input_invalid_lower_value_on_iOutOcWarnLimit() { operationalUtil.inputInvalidLowValueFromWebEleTitle(iOutOcWarnLimit); }
    @And("^I input invalid lower value on vinOvFaultLimit$")
    public void i_input_invalid_lower_value_on_vinOvFaultLimit() { operationalUtil.inputInvalidLowValueFromWebEleTitle(vinOvFaultLimit); }
    @And("^I input invalid lower value on vinUvWarnLimit$")
    public void i_input_invalid_lower_value_on_vinUvWarnLimit() { operationalUtil.inputInvalidLowValueFromWebEleTitle(vinUvWarnLimit); }
    @And("^I input invalid lower value on OTFaultLimit$")
    public void i_input_invalid_lower_value_on_OTFaultLimit() { operationalUtil.inputInvalidLowValueFromWebEleTitle(OTFaultLimit); }
    @And("^I input invalid lower value on vinOvWarnLimit$")
    public void i_input_invalid_lower_value_on_vinOvWarnLimit() { operationalUtil.inputInvalidLowValueFromWebEleTitle(vinOvWarnLimit); }
    @And("^I input invalid lower value on OTWarnLimit$")
    public void i_input_invalid_lower_value_on_OTWarnLimit() { operationalUtil.inputInvalidLowValueFromWebEleTitle(OTWarnLimit); }
    @And("^I input invalid lower value on ADDR$")
    public void i_input_invalid_lower_value_on_ADDR() { operationalUtil.inputInvalidLowValueFromWebEleTitle(ADDR); }
    @And("^I input invalid lower value on VINON$")
    public void i_input_invalid_lower_value_on_VINON() { operationalUtil.inputInvalidLowValueFromWebEleTitle(VINON); }
    @And("^I input invalid lower value on VINOFF$")
    public void i_input_invalid_lower_value_on_VINOFF() { operationalUtil.inputInvalidLowValueFromWebEleTitle(VINOFF); }
    @And("^I input invalid lower value on VINOVFAULTLIMIT$")
    public void i_input_invalid_lower_value_on_VINOVFAULTLIMIT() { operationalUtil.inputInvalidLowValueFromWebEleTitle(VINOVFAULTLIMIT); }
    @And("^I input invalid lower value on TONDELAY$")
    public void i_input_invalid_lower_value_on_TONDELAY() { operationalUtil.inputInvalidLowValueFromWebEleTitle(TONDELAY); }
    @And("^I input invalid lower value on TSS$")
    public void i_input_invalid_lower_value_on_TSS() { operationalUtil.inputInvalidLowValueFromWebEleTitle(TSS); }
    @And("^I input invalid lower value on TOFFDELAY$")
    public void i_input_invalid_lower_value_on_TOFFDELAY() { operationalUtil.inputInvalidLowValueFromWebEleTitle(TOFFDELAY); }
    @And("^I input invalid lower value on TOFFFALL$")
    public void i_input_invalid_lower_value_on_TOFFFALL() { operationalUtil.inputInvalidLowValueFromWebEleTitle(TOFFFALL); }
    @And("^I input invalid lower value on VOMARGINHIGH$")
    public void i_input_invalid_lower_value_on_VOMARGINHIGH() { operationalUtil.inputInvalidLowValueFromWebEleTitle(VOMARGINHIGH); }
    @And("^I input invalid lower value on VOMARGINLOW$")
    public void i_input_invalid_lower_value_on_VOMARGINLOW() { operationalUtil.inputInvalidLowValueFromWebEleTitle(VOMARGINLOW); }
    @And("^I input invalid lower value on VOMAX$")
    public void i_input_invalid_lower_value_on_VOMAX() { operationalUtil.inputInvalidLowValueFromWebEleTitle(VOMAX); }
    @And("^I input invalid lower value on OTLIMIT$")
    public void i_input_invalid_lower_value_on_OTLIMIT() { operationalUtil.inputInvalidLowValueFromWebEleTitle(OTLIMIT); }
    @And("^I input invalid lower value on RAILADDRESS$")
    public void i_input_invalid_lower_value_on_RAILADDRESS() { operationalUtil.inputInvalidLowValueFromWebEleTitle(RAILADDRESS); }
    @And("^I input valid value on VoMarginHigh$")
    public void i_input_valid_value_on_VoMarginHigh() { operationalUtil.inputValueFromWebEleTitle(VoMarginHigh); }
    @And("^I input valid value on voMin$")
    public void i_input_valid_value_on_voMin() { operationalUtil.inputValueFromWebEleTitle(voMin); }
    @And("^I input valid value on voMax$")
    public void i_input_valid_value_on_voMax() { operationalUtil.inputValueFromWebEleTitle(voMax); }
    @And("^I input valid value on vinOn$")
    public void i_input_valid_value_on_vinOn() { operationalUtil.inputValueFromWebEleTitle(vinOn); }
    @And("^I input valid value on vinOff$")
    public void i_input_valid_value_on_vinOff() { operationalUtil.inputValueFromWebEleTitle(vinOff); }
    @And("^I input valid value on TonDelay$")
    public void i_input_valid_value_on_TonDelay() { operationalUtil.inputValueFromWebEleTitle(TonDelay); }
    @And("^I input valid value on VoMarginLow$")
    public void i_input_valid_value_on_VoMarginLow() { operationalUtil.inputValueFromWebEleTitle(VoMarginLow); }
    @And("^I input valid value on hiccupItv$")
    public void i_input_valid_value_on_hiccupItv() { operationalUtil.inputValueFromWebEleTitle(hiccupItv); }
    @And("^I input valid value on ocLimit$")
    public void i_input_valid_value_on_ocLimit() { operationalUtil.inputValueFromWebEleTitle(ocLimit); }
    @And("^I input valid value on iOutOcFaultLimit$")
    public void i_input_valid_value_on_iOutOcFaultLimit() { operationalUtil.inputValueFromWebEleTitle(iOutOcFaultLimit); }
    @And("^I input valid value on iOutOcWarnLimit$")
    public void i_input_valid_value_on_iOutOcWarnLimit() { operationalUtil.inputValueFromWebEleTitle(iOutOcWarnLimit); }
    @And("^I input valid value on vinOvFaultLimit$")
    public void i_input_valid_value_on_vinOvFaultLimit() { operationalUtil.inputValueFromWebEleTitle(vinOvFaultLimit); }
    @And("^I input valid value on ToffDelay$")
    public void i_input_valid_value_on_ToffDelay() { operationalUtil.inputValueFromWebEleTitle(ToffDelay); }
    @And("^I input valid value on vinOvWarnLimit$")
    public void i_input_valid_value_on_vinOvWarnLimit() { operationalUtil.inputValueFromWebEleTitle(vinOvWarnLimit); }
    @And("^I input valid value on vinUvWarnLimit$")
    public void i_input_valid_value_on_vinUvWarnLimit() { operationalUtil.inputValueFromWebEleTitle(vinUvWarnLimit); }
    @And("^I input valid value on OTFaultLimit$")
    public void i_input_valid_value_on_OTFaultLimit() { operationalUtil.inputValueFromWebEleTitle(OTFaultLimit); }
    @And("^I input valid value on OTWarnLimit$")
    public void i_input_valid_value_on_OTWarnLimit() { operationalUtil.inputValueFromWebEleTitle(OTWarnLimit); }
    @And("^I input valid value on ADDR$")
    public void i_input_valid_value_on_ADDR() { operationalUtil.inputValueFromWebEleTitle(ADDR); }
    @And("^I input valid value on VINON$")
    public void i_input_valid_value_on_VINON() { operationalUtil.inputValueFromWebEleTitle(VINON); }
    @And("^I input valid value on VINOFF$")
    public void i_input_valid_value_on_VINOFF() { operationalUtil.inputValueFromWebEleTitle(VINOFF); }
    @And("^I input valid value on VINOVFAULTLIMIT$")
    public void i_input_valid_value_on_VINOVFAULTLIMIT() { operationalUtil.inputValueFromWebEleTitle(VINOVFAULTLIMIT); }
    @And("^I input valid value on TONDELAY$")
    public void i_input_valid_value_on_TONDELAY() { operationalUtil.inputValueFromWebEleTitle(TONDELAY); }
    @And("^I input valid value on TSS$")
    public void i_input_valid_value_on_TSS() { operationalUtil.inputValueFromWebEleTitle(TSS); }
    @And("^I input valid value on TOFFDELAY$")
    public void i_input_valid_value_on_TOFFDELAY() { operationalUtil.inputValueFromWebEleTitle(TOFFDELAY); }
    @And("^I input valid value on TOFFFALL$")
    public void i_input_valid_value_on_TOFFFALL() { operationalUtil.inputValueFromWebEleTitle(TOFFFALL); }
    @And("^I input valid value on VOMARGINHIGH$")
    public void i_input_valid_value_on_VOMARGINHIGH() { operationalUtil.inputValueFromWebEleTitle(VOMARGINHIGH); }
    @And("^I input valid value on VOMARGINLOW$")
    public void i_input_valid_value_on_VOMARGINLOW() { operationalUtil.inputValueFromWebEleTitle(VOMARGINLOW); }
    @And("^I input valid value on VOMAX$")
    public void i_input_valid_value_on_VOMAX() { operationalUtil.inputValueFromWebEleTitle(VOMAX); }
    @And("^I input valid value on OTLIMIT$")
    public void i_input_valid_value_on_OTLIMIT() { operationalUtil.inputValueFromWebEleTitle(OTLIMIT); }
    @And("^I input valid value on RAILADDRESS$")
    public void i_input_valid_value_on_RAILADDRESS() { operationalUtil.inputValueFromWebEleTitle(RAILADDRESS); }
    @And("^Click Ok button on alert of Advaned Specification$")
    public void click_ok_on_alert_of_ad_spe(){
        operationalUtil.scrollIntoView(OK_BUTTON_ON_ALERT_OF_ADVANED);
        operationalUtil.moveToElementByActions(OK_BUTTON_ON_ALERT_OF_ADVANED);
        OK_BUTTON_ON_ALERT_OF_ADVANED.click();
    }
    @And("^I select \"([^\"]*)\" from Peak Current Limit$")
    public void i_select_option_from_peak_current_limit(String peakCurrentLimitValue){ operationalUtil.clickOptionWithValue(PEA_CURLIM_THRSELECT,peakCurrentLimitValue); }
    @And("^I select \"([^\"]*)\" from UVLO Rising Threshold$")
    public void i_select_option_from_UVLO_Rising_Threshold(String UVLORisingThresholdValue){ operationalUtil.clickOptionWithValue(vin_UVLO_Ris_Thr_Select,UVLORisingThresholdValue); }
    @Then("^Accept terms of service$")
    public void accept_terms_of_service_from_url_of(){
       operationalUtil.switchToFrame(STR_DC_DC_ONLINE_FRAME);
        operationalUtil.moveToElementAction(MPS_TERM_SERVICE_PROTOCOL_CHECK);
       MPS_TERM_SERVICE_PROTOCOL_CHECK.click();
       MPS_ACCEPT_TOS_BUTTON.click();
    }
    @Then("^\"([^\"]*)\" loaded$")
    public void partnumber_page_loaded(String partnumber){
        operationalUtil.switchToDefaultFrame();
        operationalUtil.switchToFrame(STR_DC_DC_ONLINE_FRAME);
        operationalUtil.assertOptionSelected(DESIGN_PART_SELECT_FINAL_WEBELEMENT, partnumber);
    }
    @Then("^The information should appear in the Alert in Advanced Specs, as \"([^\"]*)\"$")
    public void infor_should_in_the_alert(String inforInAlert){ assertEquals(inforInAlert,AD_SPE_ALERT_CONTENT.getText()); }
    @And("^I select part number \"([^\"]*)\" in part number select$")
    public void i_select_part_number(String partnumber){
        operationalUtil.switchToDefaultFrame();
        operationalUtil.switchToFrame(STR_DC_DC_ONLINE_FRAME);
        operationalUtil.clickOptionWithValue(DESIGN_PART_SELECT, partnumber);
    }
    @Then("^I should see value of mp1462 is right in Design Target,the values should be \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" orderly$")
    public void i_should_see_value_of_mp1462_in_design_target(String VIN,String VIN_MIN,String VIN_MAX,String VOUT,String IOUT,String FSS,String RIPPLE,String OUTPUT_RIPPLE,String UVLO,String DESIRED_BAND_WIDTH,String HIGH_COT,String LOW_COTT,String SLOW_RATE_OF_TRANSIENT,String CAP_TYPE_DROP,String COMPENSATION_DROP,String VIN_RIPPLE,String VO_RIPPLE,String BANDWIDTH,String PHASE_MARGIN,String GAIN_MARGIN, String OUTPUT_TRANSIENT_RIPPLE,String EFFICIENCY){
        operationalUtil.switchToDefaultFrame();
        operationalUtil.switchToFrame(STR_DC_DC_ONLINE_FRAME);
        operationalUtil.waitElementVisibility(INT_TIMEOUT_DCDC_ONLINE_LOAD,VIN_VALUE);
        assertEquals(VIN,VIN_VALUE.getAttribute("value"));
        assertEquals(VIN_MIN,VIN_MIN_VALUE.getAttribute("value"));
        assertEquals(VIN_MAX,VIN_MAX_VALUE.getAttribute("value"));
        assertEquals(VOUT,VOUT_VALUE.getAttribute("value"));
        assertEquals(IOUT,IOUT_VALUE.getAttribute("value"));
        assertEquals(FSS,FSS_VALUE.getAttribute("value"));
        assertEquals(RIPPLE,RIPPLE_VALUE.getAttribute("value"));
        assertEquals(OUTPUT_RIPPLE,OUTPUT_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(UVLO,UVLO_VALUE.getAttribute("value"));
        assertEquals(DESIRED_BAND_WIDTH,DESIRED_BAND_WIDTH_VALUE.getAttribute("value"));
        assertEquals(HIGH_COT,HIGH_COT_VALUE.getAttribute("value"));
        assertEquals(LOW_COTT,LOW_COTT_VALUE.getAttribute("value"));
        assertEquals(SLOW_RATE_OF_TRANSIENT,SLOW_RATE_OF_TRANSIENT_VALUE.getAttribute("value"));
        operationalUtil.assertOptionSelected(CAP_TYPE_DROP_VALUE_FINAL_WEBELEMENT,CAP_TYPE_DROP);
        operationalUtil.assertOptionSelected(COMPENSATION_DROP_VALUE_FINAL_WEBELEMENT,COMPENSATION_DROP);
        assertEquals(VIN_RIPPLE,VIN_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(VO_RIPPLE,VO_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(BANDWIDTH,BANDWIDTH_VALUE.getAttribute("value"));
        assertEquals(PHASE_MARGIN,PHASE_MARGIN_VALUE.getAttribute("value"));
        assertEquals(GAIN_MARGIN,GAIN_MARGIN_VALUE.getAttribute("value"));
        assertEquals(OUTPUT_TRANSIENT_RIPPLE,OUTPUT_TRANSIENT_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(EFFICIENCY,EFFICIENCY_VALUE.getAttribute("value"));
    }
    @Then("^I should see value of mp3425 is right in Design Target,the values should be \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" orderly$")
    public void i_should_see_value_of_MP3425_in_design_target(String VIN,String VIN_MIN,String VIN_MAX,String VOUT,String IOUT,String FSS,String RIPPLE,String OUTPUT_RIPPLE,String UVLO,String DESIRED_BAND_WIDTH,String HIGH_COT,String LOW_COTT,String SLOW_RATE_OF_TRANSIENT,String CAP_TYPE_DROP,String COMPENSATION_DROP,String VIN_RIPPLE,String VO_RIPPLE,String BANDWIDTH,String PHASE_MARGIN,String GAIN_MARGIN, String OUTPUT_TRANSIENT_RIPPLE,String EFFICIENCY){
        operationalUtil.switchToDefaultFrame();
        operationalUtil.switchToFrame(STR_DC_DC_ONLINE_FRAME);
        operationalUtil.waitElementVisibility(INT_TIMEOUT_DCDC_ONLINE_LOAD,VIN_VALUE);
        assertEquals(VIN,VIN_VALUE.getAttribute("value"));
        assertEquals(VIN_MIN,VIN_MIN_VALUE.getAttribute("value"));
        assertEquals(VIN_MAX,VIN_MAX_VALUE.getAttribute("value"));
        assertEquals(VOUT,VOUT_VALUE.getAttribute("value"));
        assertEquals(IOUT,IOUT_VALUE.getAttribute("value"));
        assertEquals(FSS,FSS_VALUE.getAttribute("value"));
        assertEquals(RIPPLE,RIPPLE_VALUE.getAttribute("value"));
        assertEquals(OUTPUT_RIPPLE,OUTPUT_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(UVLO,UVLO_VALUE.getAttribute("value"));
        assertEquals(DESIRED_BAND_WIDTH,DESIRED_BAND_WIDTH_VALUE.getAttribute("value"));
        assertEquals(HIGH_COT,HIGH_COT_VALUE.getAttribute("value"));
        assertEquals(LOW_COTT,LOW_COTT_VALUE.getAttribute("value"));
        assertEquals(SLOW_RATE_OF_TRANSIENT,SLOW_RATE_OF_TRANSIENT_VALUE.getAttribute("value"));
        operationalUtil.assertOptionSelected(CAP_TYPE_DROP_VALUE_FINAL_WEBELEMENT,CAP_TYPE_DROP);
        operationalUtil.assertOptionSelected(COMPENSATION_DROP_VALUE_FINAL_WEBELEMENT,COMPENSATION_DROP);
        assertEquals(VIN_RIPPLE,VIN_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(VO_RIPPLE,VO_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(BANDWIDTH,BANDWIDTH_VALUE.getAttribute("value"));
        assertEquals(PHASE_MARGIN,PHASE_MARGIN_VALUE.getAttribute("value"));
        assertEquals(GAIN_MARGIN,GAIN_MARGIN_VALUE.getAttribute("value"));
        assertEquals(OUTPUT_TRANSIENT_RIPPLE,OUTPUT_TRANSIENT_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(EFFICIENCY,EFFICIENCY_VALUE.getAttribute("value"));
    }
    @Then("^I should see value of MPM3805 is right in Design Target,the values should be \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" orderly$")
    public void i_should_see_value_of_MPM3805_in_design_target(String COT_VIN,String COT_VINMIN,String COT_VINMAX,String COT_VINOUT,String COT_IOUT,String COT_SWITCHINGFREQ,String COT_INPUT_RIPPLE,String COT_OUTPUT_RIPPLE,String COMBOX_OUTPUT_CAP,String COMBOX_COMPENSATION,String COT_OUTPUT_SLOPE_RIPPLE,String COT_OUTPUT_VINRIPPLE,String COT_OUTPUT_VORIPPLE,String COT_EFFICIENCY){
        operationalUtil.switchToDefaultFrame();
        operationalUtil.switchToFrame(STR_DC_DC_ONLINE_FRAME);
        operationalUtil.waitElementVisibility(INT_TIMEOUT_DCDC_ONLINE_LOAD,COT_VIN_VALUE);
        assertEquals(COT_VIN,COT_VIN_VALUE.getAttribute("value"));
        assertEquals(COT_VINMIN,COT_VINMIN_VALUE.getAttribute("value"));
        assertEquals(COT_VINMAX,COT_VINMAX_VALUE.getAttribute("value"));
        assertEquals(COT_VINOUT,COT_VINOUT_VALUE.getAttribute("value"));
        assertEquals(COT_IOUT,COT_IOUT_VALUE.getAttribute("value"));
        assertEquals(COT_SWITCHINGFREQ,COT_SWITCHINGFREQ_VALUE.getAttribute("value"));
        assertEquals(COT_INPUT_RIPPLE,COT_INPUT_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(COT_OUTPUT_RIPPLE,COT_OUTPUT_RIPPLE_VALUE.getAttribute("value"));
        operationalUtil.assertOptionSelected(COMBOX_OUTPUT_CAP_SELECT_FINAL_WEBELEMENT,COMBOX_OUTPUT_CAP);
        operationalUtil.assertOptionSelected(COMBOX_COMPENSATION_SELECT_FINAL_WEBELEMENT,COMBOX_COMPENSATION);
        assertEquals(COT_OUTPUT_SLOPE_RIPPLE,COT_OUTPUT_SLOPE_RIPPLE_VALUE.getAttribute("value"));
        assertEquals(COT_OUTPUT_VINRIPPLE,COT_OUTPUT_VINRIPPLE_VALUE.getAttribute("value"));
        assertEquals(COT_OUTPUT_VORIPPLE,COT_OUTPUT_VORIPPLE_VALUE.getAttribute("value"));
        assertEquals(COT_EFFICIENCY,COT_EFFICIENCY_VALUE.getAttribute("value"));
    }
    @Then("^I should see several tabs in main top navigation, which are in \"([^\"]*)\"$")
    public void i_should_see_all_tabs_in_main_top_navigation(String combinedTabs){
        String[] expectedTabNames = combinedTabs.split("-");
        operationalUtil.switchToDefaultFrame();
        operationalUtil.switchToFrame(STR_DC_DC_ONLINE_FRAME);
        operationalUtil.waitElementVisibility(INT_TIMEOUT_DCDC_ONLINE_LOAD,MAIN_TOP_NAVIGATION);
        String[] ids = MAIN_TOP_NAVIGATION.findElements(By.xpath("li")).stream().filter(e -> operationalUtil.elementIsVisibleInViewport(e)).map(e -> e.getAttribute("id")).toArray(String[]::new);
        assertEquals(ids.length,expectedTabNames.length);
        Stream.of(expectedTabNames).forEach(tab -> assertTrue(Stream.of(ids).anyMatch(id -> id.contains(tab))));
    }
    @Then("^The information should appear as \"([^\"]*)\" in efficiency$")
    public void it_should_show_no_data_in_efficiency(String infor){ assertEquals(infor,EFFICIENCY_WAVE.getText()); }
    @Given("^I visit DC DC Designer online page with supplied part number \"([^\"]*)\" and slash$")
    public void i_am_on_DC_DC_Designer_online_with_supplied_part_number_slash(String partnumber) {
        operationalUtil.getURL("/" + "en/design-tools/design-tools/dc-dc-designer-online.html?PN=" + partnumber+"/");
        operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
    }
    @Given("^I visit DC DC Designer online page with supplied part number \"([^\"]*)\"$")
    public void i_am_on_DC_DC_Designer_online_with_supplied_part_number(String partnumber) {
        operationalUtil.getURL("/" + "en/design-tools/design-tools/dc-dc-designer-online.html?PN=" + partnumber);
        operationalUtil.waitForAttributeToBe(BODY_CONTAINER, "aria-busy", "false");
    }
    @Then("^The digikey element show up$")
    public void buthon_addtodigikeycart_show_up(){
        operationalUtil.assertElementPresent(PRODUCT_DIGIKEY);
        operationalUtil.assertElementPresent(EVAL_KIT_OPTION);
    }
}
