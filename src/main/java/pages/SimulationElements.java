package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SimulationElements extends BaseTest {
    @FindBy(xpath = "//button[@id='login-to-select2']")
    public static WebElement LOGIN_LOAD_YOUR_DESIGN_BUTTON;

    @FindBy(xpath = "//span[@id='cfgstn-step1-status']")
    public  static WebElement LOADING_SIMULATION_IMAGE;

    @FindBy(xpath = "//input[@id='advSpec']")
    public  static WebElement ADVANCED_SPECS_BUTTON;

    @FindBy(xpath = "//input[@id='viewWave']")
    public  static WebElement VIEW_WAVEFORMS_BUTTON;

    @FindBy(xpath = "//input[@id='viewDatasheet']")
    public  static WebElement VIEW_CUSTOM_DATASHEET_BUTTON;

    @FindBy(xpath = "//input[@id='commitFinal']")
    public  static WebElement SAVE_SPECS_BUTTON;

    // Element in Customizable Specs, All suit for mezdpd4506as, mezdpd3603as, mezdpd1620as
    @FindBy(xpath = "//input[@id='VinTyp']")
    public  static WebElement VinTyp_Input;

    @FindBy(xpath = "//input[@id='VinMin']")
    public  static WebElement VinMin_Input;

    @FindBy(xpath = "//input[@id='VinMax']")
    public  static WebElement VinMax_Input;

    @FindBy(xpath = "//input[@id='Vout']")
    public  static WebElement VinVout_Input;

    @FindBy(xpath = "//input[@id='Iout']")
    public  static WebElement Iout_Input;

    //Tip image in the Advanced Specification, All suit for mezdpd4506as,part is suit for mezdpd3603as, not support for mezdpd1620as
    @FindBy(xpath = "//legend[contains(text(),'Light Load Mode')]//img")
    public  static WebElement LIGHT_LOAD_MODE_TIP_IMAGE;

    @FindBy(xpath = "//legend[contains(text(),'Compensation')]//img")
    public  static WebElement COMPENSATION_TIP_IMAGE;

    @FindBy(xpath = "//legend[contains(text(),'Switching')]//img")
    public  static WebElement SWITCHING_TIP_IMAGE;

    @FindBy(xpath = "//legend[contains(text(),'Input Protection')]//img")
    public  static WebElement INPUT_PROTECTION_TIP_IMAGE;

    @FindBy(xpath = "//legend[contains(text(),'SS Time')]//img")
    public  static WebElement SS_TIME_TIME_IAMGE;

    @FindBy(xpath = "//div[@id='AdvancedConfigDialogleftRight']//div[1]//fieldset[1]//table[1]//tbody[1]//tr[1]//td[1]//img")
    public  static WebElement VALLEY_CURRENT_LIMIT_TIP_IMAGE;

    @FindBy(xpath = "//div[@id='AdvancedConfigDialogleftRight']//div[1]//fieldset[1]//table[1]//tbody[1]//tr[2]//td[1]//img")
    public  static WebElement PEAK_CURRENT_LIMIT_TIP_IMAGE;

    @FindBy(xpath = "//div[@id='AdvancedConfigDialogleftRight']//div[1]//fieldset[1]//table[1]//tbody[1]//tr[3]//td[1]//img")
    public  static WebElement OC_RESPONSE_MODE_TIP_IMAGE;

    @FindBy(xpath = "//legend[contains(text(),'OVP')]//img")
    public  static WebElement OVP_TIP_IMAGE;

    @FindBy(xpath = "//legend[contains(text(),'UVP')]//img")
    public  static WebElement UVP_TIP_IMAGE;

    //Close button in the tip, All suit for mezdpd4506as,part is suit for mezdpd3603as, not support for mezdpd1620as
    @FindBy(xpath = "//div[@id='LightLoadModeDialog']/./..//div[3]//button[1]")
    public  static WebElement CLOSE_LIGHT_LOAD_MODE_TIP;

    @FindBy(xpath = "//div[@id='CompensationDialog']/./..//div[3]//div[1]//button[1]")
    public  static WebElement CLOSE_COMPENSATION_TIP;
    @FindBy(xpath = "//div[@id='SwitchingDialog']/./..//div[1]//button[1]")
    public  static WebElement CLOSE_SWITCHING_TIP;
    @FindBy(xpath = "//div[@id='VINThresholdDialog']/./..//div[1]//button[1]")
    public  static WebElement CLOSE_INPUT_PROTECTION_TIP;
    @FindBy(xpath = "//div[@id='SSTimeDialog']/./..//div[1]//button[1]")
    public  static WebElement CLOSE_SS_TIME_TIME;
    @FindBy(xpath = "//div[@id='valleyCurrentLimitThresholdDialog']/./..//div[1]//button[1]")
    public  static WebElement CLOSE_VALLEY_CURRENT_LIMIT_TIP;
    @FindBy(xpath = "//div[@id='PeakCurrentLimitThresholdDialog']/./..//div[1]//button[1]")
    public  static WebElement CLOSE_PEAK_CURRENT_LIMIT_TIP;
    @FindBy(xpath = "//div[@id='OCResponseModeDialog']/./..//div[1]//button[1]")
    public  static WebElement CLOSE_OC_RESPONSE_MODE_TIP;
    @FindBy(xpath = "//div[@id='VoOVPModeDialog']/./..//div[1]//button[1]")
    public  static WebElement CLOSE_OVP_TIP;
    @FindBy(xpath = "//div[@id='VoUVPModeDialog']/./..//div[1]//button[1]")
    public  static WebElement CLOSE_UVP_TIP;
    // All Tips in Advanced Specifications for mezdpd4506as,part of them suit for mezdpd3603as,not support for mezdpd1620as
    @FindBy(xpath = "//div[@id='LightLoadModeDialog']/./..")
    public static WebElement LIGHT_LOAD_MODE_TIP;
    @FindBy(xpath = "//div[@id='CompensationDialog']/./..")
    public static WebElement COMPENSATION_TIP;
    @FindBy(xpath = "//div[@id='SwitchingDialog']/./..")
    public static WebElement SWITCH_TIP;
    @FindBy(xpath = "//div[@id='VINThresholdDialog']/./..")
    public static WebElement VIN_THRESHOLD_TIP;
    @FindBy(xpath = "//div[@id='SSTimeDialog']/./..")
    public static WebElement SS_TIME_TIP;
    @FindBy(xpath = "//div[@id='valleyCurrentLimitThresholdDialog']/./..")
    public static WebElement VALLEY_CURRENT_LIMINIT_THRESHOLD_TIP;
    @FindBy(xpath = "//div[@id='PeakCurrentLimitThresholdDialog']/./..")
    public static WebElement PEAK_CURRENT_LIMIT_THRESHOLD_TIP;
    @FindBy(xpath = "//div[@id='OCResponseModeDialog']/./..")
    public static WebElement OC_RESPONSEMODE_TIP;
    @FindBy(xpath = "//div[@id='VoOVPModeDialog']/./..")
    public static WebElement VO_OVP_MODE_TIP;
    @FindBy(xpath = "//div[@id='VoUVPModeDialog']/./..")
    public static WebElement VO_UVP_MODE_TIP;
    //Waveform pop up frame for all modules
    @FindBy(xpath ="//div[@id='SimulationDialog']/./..")
    public static WebElement WAVEFORMSPOP;
    @FindBy(xpath = "//input[@id='viewDatasheet']")
    public static WebElement VIEWDATASHEET;
    //Desing name element for all modules
    @FindBy(xpath = "//div[@id='generating-custom-datasheet']")
    public static WebElement GENERATINGCUSTOMDATASHEET;
    @FindBy(xpath = "//div[@id='currentConfigDialog']/./..")
    public static WebElement NEWDESIGNNAMEPOPUP;
    @FindBy(xpath = "//div[@id='currentConfigDialog']")
    public static WebElement CURRENTCONFIGDIALOG;
    @FindBy(xpath = "//div[@id='currentConfigDialog']/./../div[3]/div[1]/button[1]")
    public static WebElement ENTER_NEW_DESIGN_NAME_OK_BUTTON;
    //Design name in the list for all modules
    @FindBy(xpath = "//div[@id='MyDesigns']//tbody//tr[1]/td[2]")
    public static WebElement FIRSTNAMEINLIST;
    @FindBy(xpath = "//div[@id='OKDialog']/./..")
    public static WebElement AD_SPE_ALERT_WINDOW;
    @FindBy(xpath = "//div[@id='loading_overlay']")
    public static WebElement LOADINGOVERLAY;
    //Element in login2 left side image for all modules
    @FindBy(xpath = "//div[@id='product-options-wrapper']")
    public static WebElement PRODUCTOPTIONWRAPPER;
    @FindBy(xpath = "//div[@id='product-options-wrapper']/div[1]/div[1]")
    public static WebElement FIELDREQUIRED;
    //Down left corner image
    @FindBy(xpath = "//div[@id='_hj-f5b2a1eb-9b07_poll']//a[1]")
    public static WebElement DOWN_LEFT_CORNER;
    @FindBy(xpath = "//div[@id='_hj-f5b2a1eb-9b07_poll']")
    public static WebElement DOWN_LEFT_IMAGE;
    //Accept Check button in Accept terms page
    @FindBy (xpath = "//input[@id='ilogin_iAcceptCheckBox']")
    public static  WebElement MPS_TERM_SERVICE_PROTOCOL_CHECK;
    // Accept button in Accept terms page for all modules
    @FindBy(xpath="//input[@id='ilogin_iAcceptButton']")
    public static WebElement MPS_ACCEPT_TOS_BUTTON;
    // Design part slect for all modules
    @FindBy(xpath="//select[@id='i_sidebar_DropDownList1']")
    public static WebElement DESIGN_PART_SELECT;
    @FindBy(xpath="//select[@id='i_sidebar_DropDownList1']//option[@selected=\"selected\"]")
    public static WebElement DESIGN_PART_SELECT_FINAL_WEBELEMENT;
    @FindBy(xpath="//input[@id='i_sidebar_inputVinTextBox']")
    public static WebElement VIN_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputVinminTextBox']")
    public static WebElement VIN_MIN_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputVinmaxTextBox']")
    public static WebElement VIN_MAX_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputVoutTextBox']")
    public static WebElement VOUT_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputIoutTextBox']")
    public static WebElement IOUT_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputFSSynTextBox']")
    public static WebElement FSS_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputInputRippleTextBox']")
    public static WebElement RIPPLE_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputOutputRippleTextBox']")
    public static WebElement OUTPUT_RIPPLE_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputUVLOTextBox']")
    public static WebElement UVLO_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputDesiredBandWidthTextBox']")
    public static WebElement DESIRED_BAND_WIDTH_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputHighCoTTextBox']")
    public static WebElement HIGH_COT_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputLowCoTTextBox']")
    public static WebElement LOW_COTT_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_inputSlowRateOfTransientTextBox']")
    public static WebElement SLOW_RATE_OF_TRANSIENT_VALUE;
    @FindBy(xpath="//select[@id='i_sidebar_inputCapTypeDropList']")
    public static WebElement CAP_TYPE_DROP_VALUE;
    @FindBy(xpath="//select[@id='i_sidebar_inputCapTypeDropList']//option[@selected=\"selected\"]")
    public static WebElement CAP_TYPE_DROP_VALUE_FINAL_WEBELEMENT;
    @FindBy(xpath="//select[@id='i_sidebar_combox_PCMCompensationType']")
    public static WebElement COMPENSATION_DROP_VALUE;
    @FindBy(xpath="//select[@id='i_sidebar_combox_PCMCompensationType']//option[@selected=\"selected\"]")
    public static WebElement COMPENSATION_DROP_VALUE_FINAL_WEBELEMENT;
    @FindBy(xpath="//input[@id='i_sidebar_outputVinRippleTextBox']")
    public static WebElement VIN_RIPPLE_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_outputVoRippleTextBox']")
    public static WebElement VO_RIPPLE_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_outputDesiredBandwidthTextBox']")
    public static WebElement BANDWIDTH_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_outputPhaseMarginTextBox']")
    public static WebElement PHASE_MARGIN_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_gainMarginTextBox']")
    public static WebElement GAIN_MARGIN_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_outputTransientRippleTextBox']")
    public static WebElement OUTPUT_TRANSIENT_RIPPLE_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_outputEfficiencyTextBox']")
    public static WebElement EFFICIENCY_VALUE;
    @FindBy(xpath="//div[@id='maintopnav']/ul")
    public static WebElement MAIN_TOP_NAVIGATION;
    @FindBy(xpath="//input[@id='i_sidebar_cot_inputVinTextBox']")
    public static WebElement COT_VIN_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_inputVinminTextBox']")
    public static WebElement COT_VINMIN_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_inputVinmaxTextBox']")
    public static WebElement COT_VINMAX_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_inputVoutTextBox']")
    public static WebElement COT_VINOUT_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_inputIoutTextBox']")
    public static WebElement COT_IOUT_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_inputSwitchingFreqTextBox']")
    public static WebElement COT_SWITCHINGFREQ_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_inputInputRippleTextBox']")
    public static WebElement COT_INPUT_RIPPLE_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_inputOutputRippleTextBox']")
    public static WebElement COT_OUTPUT_RIPPLE_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_outputSlopeRippleTextBox']")
    public static WebElement COT_OUTPUT_SLOPE_RIPPLE_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_outputVinRippleTextBox']")
    public static WebElement COT_OUTPUT_VINRIPPLE_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_outputEfficiencyTextBox']")
    public static WebElement COT_EFFICIENCY_VALUE;
    @FindBy(xpath="//input[@id='i_sidebar_cot_outputVoRippleTextBox']")
    public static WebElement COT_OUTPUT_VORIPPLE_VALUE;
    @FindBy(xpath="//select[@id='i_sidebar_combox_OutputCapType']")
    public static WebElement COMBOX_OUTPUT_CAP_SELECT;
    @FindBy(xpath="//select[@id='i_sidebar_combox_OutputCapType']//option[@selected=\"selected\"]")
    public static WebElement COMBOX_OUTPUT_CAP_SELECT_FINAL_WEBELEMENT;
    @FindBy(xpath="//select[@id='i_sidebar_combox_CompensationType']")
    public static WebElement COMBOX_COMPENSATION_SELECT;
    @FindBy(xpath="//select[@id='i_sidebar_combox_CompensationType']//option[@selected=\"selected\"]")
    public static WebElement COMBOX_COMPENSATION_SELECT_FINAL_WEBELEMENT;
    //RTH CTH CTHP SLOPE GM for mezdpd4506as Compensation Select Element
    @FindBy(xpath = "//select[@id='RTH']")
    public static WebElement RTH_SELECT;
    @FindBy(xpath = "//select[@id='CTH']")
    public static WebElement CTH_SELECT;
    @FindBy(xpath = "//select[@id='CTHP']")
    public static WebElement CTHP_SELECT;
    @FindBy(xpath = "//select[@id='Slope']")
    public static WebElement SLOPE_SELECT;
    @FindBy(xpath = "//select[@id='GM']")
    public static WebElement GM_SELECT;
    //OK button in Advanced Specificaiton for All modules.
    @FindBy(xpath = "//div[@id='AdvancedConfigDialog']/./..//div[3]//button[1]")
    public static WebElement ADVANCED_SPECS_OK;
    //OK button in View Waveforms for All modules.
    @FindBy(xpath = "//div[@id='SimulationDialog']/./..//div[3]//div[1]//button[1]")
    public static WebElement VIEW_WAVEFORMS_OK;
    //Bode plot button for all modules
    @FindBy(xpath = "//input[@id='bodePlot']")
    public static WebElement BODE_PLOT_BUTTON;
    //Simulation Three dots Loading image
    @FindBy(xpath = "//div[@id='loading2']")
    public static WebElement LOADING_IN_WAVEFORMS;
    @FindBy(xpath = "//div[@id='loadingMyDesign']")
    public static WebElement LOADING_DESIGN_WAVEFORMS;
    //Rcomp Rt Ccomp1 Vpp for ezdpd3603as Compensation Select Element
    @FindBy(xpath = "//select[@id='RcompSelect']")
    public static WebElement RCOMP_SELECT;
    @FindBy(xpath = "//select[@id='RtSelect']")
    public static WebElement RT_SELECT;
    @FindBy(xpath = "//select[@id='Ccomp1Select']")
    public static WebElement CCOMP1_SELECT;
    @FindBy(xpath = "//select[@id='SlopeCompensationSelect']")
    public static WebElement VPP_SELECT;
    //Efficiency button
    @FindBy(xpath = "//input[@id='efficiency']")
    public static WebElement EFFICIENCY_BUTTON;
    //Fsw of advanced specification for mezdpd1620as
    @FindBy(xpath = "//select[@id='switchingFre']")
    public static WebElement SWITCHING_FRE;
    @FindBy(xpath = "//span[@id='OKDialogSpan']")
    public static WebElement AD_SPE_ALERT_CONTENT;
    @FindBy(xpath = "//select[@id='VoutRange']")
    public static WebElement VOUTRANGE_SELECT;
    @FindBy(xpath = "//input[@id='voSCALE']")
    public static WebElement VOSCALE_INPUT;
    @FindBy(xpath = "//input[@id='VoMarginHigh']")
    public static WebElement VoMarginHigh;
    @FindBy(xpath = "//input[@id='VoMarginLow']")
    public static WebElement VoMarginLow;
    @FindBy(xpath = "//input[@id='voMin']")
    public static WebElement voMin;
    @FindBy(xpath = "//input[@id='voMax']")
    public static WebElement voMax;
    @FindBy(xpath = "//input[@id='vinOn']")
    public static WebElement vinOn;
    @FindBy(xpath = "//input[@id='vinOff']")
    public static WebElement vinOff;
    @FindBy(xpath = "//input[@id='TonDelay']")
    public static WebElement TonDelay;
    @FindBy(xpath = "//input[@id='ToffDelay']")
    public static WebElement ToffDelay;
    @FindBy(xpath = "//input[@id='hiccupItv']")
    public static WebElement hiccupItv;
    @FindBy(xpath = "//input[@id='ocLimit']")
    public static WebElement ocLimit;
    @FindBy(xpath = "//input[@id='iOutOcFaultLimit']")
    public static WebElement iOutOcFaultLimit;
    @FindBy(xpath = "//input[@id='iOutOcWarnLimit']")
    public static WebElement iOutOcWarnLimit;
    @FindBy(xpath = "//input[@id='vinOvFaultLimit']")
    public static WebElement vinOvFaultLimit;
    @FindBy(xpath = "//input[@id='vinOvWarnLimit']")
    public static WebElement vinOvWarnLimit;
    @FindBy(xpath = "//input[@id='vinUvWarnLimit']")
    public static WebElement vinUvWarnLimit;
    @FindBy(xpath = "//input[@id='OTFaultLimit']")
    public static WebElement OTFaultLimit;
    @FindBy(xpath = "//input[@id='OTWarnLimit']")
    public static WebElement OTWarnLimit;
    @FindBy(xpath = "//input[@id='ADDR']")
    public static WebElement ADDR;
    //OK button for alert of advanced specification
    @FindBy(xpath = "//div[@id='OKDialog']/./..//div[3]//div[1]//button[1]")
    public static WebElement OK_BUTTON_ON_ALERT_OF_ADVANED;
    //Input box of advanced specification for mezdpd4506as
    @FindBy(xpath = "//input[@id='vinOn']")
    public static WebElement VINON;
    @FindBy(xpath = "//input[@id='vinOff']")
    public static WebElement VINOFF;
    @FindBy(xpath = "//input[@id='VinOVFaultLimit']")
    public static WebElement VINOVFAULTLIMIT;
    @FindBy(xpath = "//input[@id='TonDelay']")
    public static WebElement TONDELAY;
    @FindBy(xpath = "//input[@id='Tss']")
    public static WebElement TSS;
    @FindBy(xpath = "//input[@id='ToffDelay']")
    public static WebElement TOFFDELAY;
    @FindBy(xpath = "//input[@id='ToffFall']")
    public static WebElement TOFFFALL;
    @FindBy(xpath = "//input[@id='VoMarginHigh']")
    public static WebElement VOMARGINHIGH;
    @FindBy(xpath = "//input[@id='VoMarginLow']")
    public static WebElement VOMARGINLOW;
    @FindBy(xpath = "//input[@id='VoMax']")
    public static WebElement VOMAX;
    @FindBy(xpath = "//input[@id='OTLimit']")
    public static WebElement OTLIMIT;
    @FindBy(xpath = "//input[@id='RailAddress']")
    public static WebElement RAILADDRESS;
    //mezdpd3603as
    @FindBy(xpath = "//select[@id='fswselect']")
    public static WebElement FSW_SELECT;
    @FindBy(xpath = "//select[@id='PeaCurLimThrSelect']")
    public static WebElement PEA_CURLIM_THRSELECT;
    @FindBy(xpath = "//select[@id='vinUVLORisThrSelect']")
    public static WebElement vin_UVLO_Ris_Thr_Select;
    @FindBy(xpath = "//span[@id='initWaveformMsg']")
    public static WebElement EFFICIENCY_WAVE;
    //Digikey page
    @FindBy(id ="product-digikey")
    public static WebElement PRODUCT_DIGIKEY;
    @FindBy(xpath = "//option[contains(text(),'Eval Kit')]")
    public static WebElement EVAL_KIT_OPTION;
    private final WebDriver driver;

    public SimulationElements(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
