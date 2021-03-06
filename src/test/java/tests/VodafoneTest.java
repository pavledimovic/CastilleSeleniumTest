package tests;

import static org.testng.Assert.fail;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObject.SetupMethods;
import pageObject.WebElements;
import utils.Utility;

public class VodafoneTest {

	public static WebDriver driver;

	@BeforeMethod
	public void setUp() throws Throwable {

		// Set up system properties and go to the web page
		driver = SetupMethods.setup("chrome");
		driver.get(WebElements.URL);
		driver.manage().window().maximize();
	}

	@Test
	public void vodafoneTest() throws Exception {

		// Fill credentials with valid user name and password and submit them
		WebElements action = new WebElements(driver);
		action.fill_Details();

		// Remember cookie data so we can use it in further tests by calling LoadCookie
		// method
		// This helps up with speeding up log it process in further tests
		utils.CreateCookie create = new utils.CreateCookie();
		create.createCookie1(driver);
		Thread.sleep(2000);

		// TEST Verify that we are redirected to correct URL
		String redirectURL = driver.getCurrentUrl();
		Assert.assertEquals(redirectURL, WebElements.LandingPage);

		// TEST that box with "My Pending Balance is :" is displayed
		boolean textBox = action.isTextBoxDisplayed();
		if (textBox == true) {
			System.out.println("PASS TextBox is diplayed");
		} else
			fail();

		// TEST Verify that correct number format is used and that the balance is
		// displayed in numerical form
		// Format string
		String balance = action.get_Balance();
		DecimalFormat euroFormat = new DecimalFormat("€#,##0.00");
		Number output = euroFormat.parse(balance);
		if (output.equals((WebElements.sum))) {
			System.out.println("PASS Balance number format is correct");
		} else
			fail();

		// Format string with just numerical
		balance = balance.replace("€", "");
		DecimalFormat myFormatter = new DecimalFormat("###,###.##");
		Number output2 = myFormatter.parse(balance);
		if (output2.equals((WebElements.sum))) {
			System.out.println("PASS Balance number format without € currency sign is correct");
		} else
			fail();

		// Assert that the balance is displayed in numerical form
		boolean isNumeric = balance.chars().allMatch(Character::isDigit);

		if (isNumeric = true) {
			System.out.println("PASS Balance is displayed in numerical form");
		} else
			fail();

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException {

		// If test fail Selenium will take a screenshot
		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName());
			System.out.print("FAIL VodafoneTest-");
		}
		Thread.sleep(2000);
		driver.quit();
	}

}
