package Tests;

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

import Pages.FacebookPage;
import Utils.BasicUtils;

public class FacebookTest {
	
		 
		WebDriver driver;
		String baseurl="http://www.facebook.com";
		//change username and password here for login
		String email="";
		String password="";
		String post_msg="Hello World";
		//created object of POM
		FacebookPage fbpage;
		BasicUtils baseutils;
        Logger log = Logger.getLogger("devLogger");

		
		
		@BeforeTest
		public void setup(){
			
			//disabling chrome notifications
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			//set chrome driver path here
			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
			driver=new ChromeDriver(options);
			driver.get(baseurl);
			log.debug("launching facebook application");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Create Login Page object
			fbpage = new FacebookPage(driver);
			log.debug("created object for login class");
			//Create Basic utils object
			baseutils = new BasicUtils(driver);
			log.debug("created object for Basic utils class");
			
		}
		
		@Test
		public void test_facebook_login_and_post() throws InterruptedException{
			
			//login to application
			fbpage.loginToFacebook(email, password,log);
			log.debug("successfully logged into facebook");
			
			//post status
			fbpage.postStatus(post_msg,log);
			log.debug("posted message on facebook");
			
			Assert.assertEquals(baseutils.getTextFromElement(driver, By.xpath("//*[text()='Hello World']")), post_msg);
			log.debug("Hello World successfully posted and verified");
		
		}
		
		
		@AfterTest
		public void tearDown() {
			
			driver.quit();
			log.debug("closed the browser session");
			
		}
		
		
		@AfterMethod
		public void takeScreenshot(ITestResult result){
			
			if(ITestResult.FAILURE==result.getStatus()){
				
				try{
					
					// To create reference of TakesScreenshot
					TakesScreenshot screenshot=(TakesScreenshot)driver;
					// Call method to capture screenshot
					File src=screenshot.getScreenshotAs(OutputType.FILE);
					
					// Copy files to specific location 
					// result.getName() will return name of test case so that screenshot name will be same as test case name
					FileUtils.copyFile(src, new File("screenshots\\"+result.getName()+".png"));
					System.out.println("Successfully captured a screenshot");
					
				}catch (Exception e){
					
					System.out.println("Exception while taking screenshot "+e.getMessage());
				} 
		}
			
			
		}

}
