package Utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicUtils {

	WebDriver driver;

	public BasicUtils(WebDriver driver) {
		this.driver = driver;
	}

	// Move to eleent reusable code below
	public void moveToElement(WebDriver driver, By by) {

		Actions builder = new Actions(driver);
		WebElement element = driver.findElement(by);
		builder.moveToElement(element).build().perform();

	}

	// Move to eleent reusable code below
	public void moveToElement(WebDriver driver, WebElement element) {

		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();

	}

	// Explicit wait reusable code below
	public void waitForElement(WebDriver driver, By by) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	}

	// Click on element reusable code below
	public void clickOnElement(WebDriver driver, By by) {

		driver.findElement(by).click();

	}

	// sendkeys reusable code below
	public void sendKeysToElement(WebDriver driver, By by, String keys) {

		driver.findElement(by).sendKeys(keys);

	}

	// get text from element reusable code below
	public String getTextFromElement(WebDriver driver, By by) {

		return driver.findElement(by).getText();

	}

	//select from dropdown incase the dropdown does not have select attribute we need to iterate through it and then select
	public void selectFromDropdown(WebDriver driver, By by, String selection) {

		List<WebElement> items = driver.findElements(by);

		for (WebElement item : items) {

			if (item.getText().equals(selection)) {

				item.click();

			}

		}

	}

}
