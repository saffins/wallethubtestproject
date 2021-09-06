package Tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.WalletHubPage;
import Utils.BasicUtils;

public class WalletHubTest {

	WebDriver driver;
	String baseurl = "https://wallethub.com/join/light";
	String review_sub_url = "http://wallethub.com/profile/test_insurance_company/";
	// change username in below URL
	String review_verification_url = "https://wallethub.com/profile/username/reviews/";

	// change username and password here for login
	String username = "";
	String password = "";
	WalletHubPage wlhubpage;
	BasicUtils basicUtils;
    Logger log = Logger.getLogger("devLogger");


	@BeforeTest
	public void setup() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		// set chrome driver path here
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get(baseurl);
		log.debug("launching Wallethub application");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wlhubpage = new WalletHubPage(driver);
		basicUtils = new BasicUtils(driver);


	}
	
	@Test
	public void postReview() throws Exception {
		
		//Login to Wallethub
		wlhubpage.loginToWallethub(username, password,log);
		log.debug("logged into wallethub");
		
		//open review sub url
		driver.navigate().to(review_sub_url);
		log.debug("opened review URL to post and submit the review");
		
		//Submit rating
		wlhubpage.submitRating(log);
		log.debug("submitted the review");
		
		
		Assert.assertEquals(basicUtils.getTextFromElement(driver, By.xpath("//*[@class='rvc-header']/h4")), "Your review has been posted.");
		log.debug("review successfully submitted and verified");
		
		
		
		
	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

	@AfterMethod
	public void takeScreenshot(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {

			try {

				// To create reference of TakesScreenshot
				TakesScreenshot screenshot = (TakesScreenshot) driver;
				// Call method to capture screenshot
				File src = screenshot.getScreenshotAs(OutputType.FILE);

				// Copy files to specific location
				// result.getName() will return name of test case so that screenshot name will
				// be same as test case name
				FileUtils.copyFile(src, new File("screenshots\\" + result.getName() + ".png"));
				System.out.println("Successfully captured a screenshot");

			} catch (Exception e) {

				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}

	}

}
