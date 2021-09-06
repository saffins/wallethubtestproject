package Pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import Utils.BasicUtils;

public class WalletHubPage {

	WebDriver driver;
	BasicUtils basicUtils;

	public WalletHubPage(WebDriver driver) {

		this.driver = driver;
		basicUtils = new BasicUtils(driver);

	}

	public void loginToWallethub(String username, String password, Logger log) {

		basicUtils.clickOnElement(driver, By.xpath("//li/a[contains(text(),'Login')]"));
		log.debug("clicked on login button");
		basicUtils.sendKeysToElement(driver, By.xpath("//input[@type='text' and contains(@placeholder,'Email')]"),
				username);
		log.debug("entered username");
		basicUtils.sendKeysToElement(driver,
				By.xpath("//input[@type='password' and contains(@placeholder,'Password')]"), password);
		log.debug("entered password");
		basicUtils.clickOnElement(driver, By.xpath("//span[contains(text(),'Login')]"));
		log.debug("clicked on login button");
		basicUtils.waitForElement(driver, By.xpath("//*[@class='profile-name']"));

	}

	public void submitRating(Logger log) throws Exception {

		basicUtils.clickOnElement(driver, By.xpath("(//*[text()='Reviews'])[1]"));
		log.debug("clicked on review button to navigate to reviews section");
		basicUtils.waitForElement(driver,
				By.xpath("//*[contains(@class,'review-action')]/review-star/div/*[local-name()='svg'][4]"));
		basicUtils.moveToElement(driver,
				By.xpath("//*[contains(@class,'review-action')]/review-star/div/*[local-name()='svg'][4]"));
		log.debug("hover over 4th star");

		basicUtils.moveToElement(driver,
				By.xpath("//*[contains(@class,'review-action')]/review-star/div/*[local-name()='svg'][4]"));
		log.debug("hover over 4th star");

		List<WebElement> highlightedStars = driver.findElements(By.xpath(
				"//*[contains(@class,'review-action')]/review-star/div/*[local-name()='svg']/*[local-name()='g']/*[local-name()='path'][2]"));
		log.debug("storing number of highlighted starts in List type of webelement");

		log.debug("Number of highlighted stars are " + highlightedStars.size());

		for (WebElement star : highlightedStars) {

			if (!star.isDisplayed()) {
				log.debug("Highlighted star is not displayed");

				throw new Exception();

			}

			System.out.println("highlighted");
			log.debug("all " + highlightedStars.size() + " stars highlighted");

		}

		basicUtils.clickOnElement(driver,
				By.xpath("//*[contains(@class,'review-action')]/review-star/div/*[local-name()='svg'][4]"));
		log.debug("Clicked on 4th Star rating");

		basicUtils.clickOnElement(driver,
				By.xpath("//*[contains(@class,'progress-indicator-container')]//div[contains(@class,'second')]"));

		log.debug("clicked on dropdown menu");

		basicUtils.selectFromDropdown(driver, By.xpath("//*[contains(@class,'progress-indicator-container')]//ul/li"),
				"Health Insurance");
		log.debug("selecting 2nd item Health Insurance from dropdown");

		basicUtils.sendKeysToElement(driver, By.xpath("//*[contains(@placeholder,'review')]"),
				"This is the sample review for test automation. Test insurance company sample review.This is the sample review for test automation. Test insurance company sample review.This is the sample review for test automation.");
		log.debug("entered review comments");

		basicUtils.moveToElement(driver, By.xpath("//*[text()='Submit']"));

		basicUtils.clickOnElement(driver, By.xpath("//*[text()='Submit']"));
		log.debug("Clicked on Submit Button");
		
		basicUtils.waitForElement(driver, By.xpath("//*[@class='rvc-header']/h4"));

		
	}

}
