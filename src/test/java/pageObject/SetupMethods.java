package pageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SetupMethods {

	static WebDriver driver;

	public static WebDriver setup(String browser) throws Exception {
		// Check if parameter passed as 'chrome'
		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\PC\\Desktop\\Testiranje\\Selenium\\Chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		//Check if parameter passed from TestNG is 'firefox'
		else if(browser.equalsIgnoreCase("firefox")) {
            
			//create firefox instance
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\PC\\Desktop\\Testiranje\\Selenium\\Firefox\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
		
		//Check if parameter passed as 'Edge'
        else if(browser.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver","C:\\Users\\PC\\Desktop\\Testiranje\\Selenium\\InternetExplorer\\IEDriverServer.exe");
            InternetExplorerDriver driver;
            driver = new InternetExplorerDriver();
        }

		else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

}