package Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.BasicUtils;

public class FacebookPage {

	WebDriver driver;
	BasicUtils basicUtils;

	public FacebookPage(WebDriver driver) {

		this.driver = driver;
		basicUtils = new BasicUtils(driver);

	}

	public void loginToFacebook(String strEmail, String strPasword,Logger log) {

		// Fill email
		basicUtils.sendKeysToElement(driver, By.id("email"), strEmail);
		log.debug("entered email id");

		// Fill Password
		basicUtils.sendKeysToElement(driver, By.id("pass"), strPasword);
		log.debug("entered password");

		// click loginBtn
		basicUtils.clickOnElement(driver, By.name("login"));
		log.debug("clicked on login button");

	}

	public void postStatus(String statusMsg, Logger log) {

		try {
			// wait for post status text area to load
			basicUtils.waitForElement(driver, By.xpath("//span[contains(text(),'on your mind')]"));
			
			
			// move to post status text area
			basicUtils.moveToElement(driver, By.xpath("//span[contains(text(),'on your mind')]"));
			//click on post status text area
			basicUtils.clickOnElement(driver, By.xpath("//span[contains(text(),'on your mind')]"));
			log.debug("clicked on post status text area");

			// wait for post status text area to load
			basicUtils.waitForElement(driver, By.xpath("//span[contains(text(),'on your mind')]"));
			log.debug("wait for post status text area to load");
			
			// move to post status text area
			basicUtils.moveToElement(driver,By.xpath("//span[contains(text(),'on your mind')]"));

			// type the message in post status text area
			basicUtils.sendKeysToElement(driver, By.xpath("//div[contains(@aria-label,'on your mind')]"), statusMsg);
			log.debug("type the message in post status text area");

			// click on post btn
			basicUtils.clickOnElement(driver, By.xpath("//span[contains(text(),'Post')]"));
			log.debug("clicked on post button");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
