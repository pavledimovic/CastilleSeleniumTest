package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class WebElements {

	static WebDriver driver;

	public WebElements(WebDriver driver1) {
		PageFactory.initElements(driver1, this);
	}
	
	// Base URL
	public static String URL = "https://www.vodafone.com.mt/home";
	// Landing Page URL
	public static String LandingPage = "https://www.vodafone.com.mt/myVodafone-postpaid";
	// State in balance(must reflect actual state in test account)
	public static double sum = 57.83;

	// Elements
	// Credentials
	@FindBy(how = How.XPATH, using = "//div[@class='fm-data alignText']//input[@placeholder='Username']")
	private WebElement txtbx_UserName;

	@FindBy(how = How.XPATH, using = "//input[@id='ctl00_ctl00_cphParent_ResponsiveQuickLinks_ResponsiveLogin_txtPassword_txtInput']")
	private WebElement txtbx_Password;

	@FindBy(how = How.XPATH, using = "//div[@class='fm-submit mainpage']")
	private WebElement txtbx_Submit;
	
	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'€')]")
	private WebElement txtbx_Balance;


    // Actions
	public void enter_UserName(String UserName) {
		txtbx_UserName.sendKeys(UserName);
	}
	public void enter_Password(String Password) {
		txtbx_Password.sendKeys(Password);
	}
	public void click_submit() {
		txtbx_Submit.click();
	}
	public String get_Balance() {
		return txtbx_Balance.getText();
	}
	
	//Sign In 
	public void fill_Details() throws InterruptedException {
		Thread.sleep(1000);
		enter_UserName("testqa2");
		enter_Password("Voda1234");
		click_submit();
	}
	//Get Balance
	public void balance() throws Exception {
		get_Balance();
	}
	
}
