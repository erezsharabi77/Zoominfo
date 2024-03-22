package utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * this class handles the main functions for all the pages
 *
 * @author Erez
 */
public abstract class BasePageFunctions {

	protected WebDriver driver;
	// protected WebDriverWait wait;

	// constructor
	public BasePageFunctions(WebDriver driver) {
		this.driver = driver;
		// wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// get the driver
	public WebDriver getDriver() {
		return this.driver;
	}

	// click on element
	public Boolean clickOnElement(By elem) {
		try {
			waitForElementToBeClickableAndClickIt(elem);
			return true;
		} catch (Exception e) {
			System.out.println("Element " + elem + " was not clicked");
			return false;
		}
	}

	// Split number into digits
	public static List<Integer> splitNumberIntoDigits(int number) {
		List<Integer> digits = new ArrayList<>();

		// Handle negative numbers by converting them to positive
		number = Math.abs(number);

		// Special case for handling number 0
		if (number == 0) {
			digits.add(0);
			return digits;
		}

		// Extract digits by continuously dividing the number by 10
		while (number > 0) {
			int digit = number % 10;
			digits.add(digit);
			number /= 10;
		}

		// Reverse the list to get the digits in the correct order
		Collections.reverse(digits);

		return digits;
	}

	// wait for value to appear
	public Boolean waitForValueToAppear(By elem, String sValue) {
		try {
			// Wait for the value to appear
			WebDriverWait wait = new WebDriverWait(driver, 10); // Wait up to 10 seconds
			WebElement valueElement = wait.until(ExpectedConditions.visibilityOfElementLocated(elem));

			// Get the value text
			String valueText = valueElement.getAttribute("value");
			Assert.assertEquals(valueText, sValue);
			return true;
		} catch (Exception e) {
			System.out.println("Wait for value to appear was not worked properly");
			return false;
		}
	}

	// get attribute from element
	public String getElementAttribute(By elem, String attribute) {
		return getDriver().findElement(elem).getAttribute(attribute);
	}

	// wait for element to be clickable and click it
	public Boolean waitForElementToBeClickableAndClickIt(By elem) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10); // Wait up to 10 seconds
			wait.until(ExpectedConditions.elementToBeClickable(elem)).click();
			return true;
		} catch (Exception e) {
			System.out.println("Wait for element to be clickable was not worked correct");
			return false;
		}
	}

//
	// wait for element to be visible
	public Boolean waitForElementToBeVisible(By elem) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10); // Wait up to 10 seconds
			wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
			return true;
		} catch (Exception e) {
			System.out.println("Wait for element to be visible was not worked correct");
			return false;
		}
	}

	// wait for element attribute to change
	public Boolean waitForElementAttributeToChange(By elem, String attributeName, String initialValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10); // Wait up to 10 seconds
			wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(elem, attributeName, initialValue)));
			return true;
		} catch (Exception e) {
			System.out.println("value wasn't changed");
			return false;
		}
	}

}
