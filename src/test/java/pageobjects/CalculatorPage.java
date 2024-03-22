package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.BasePageFunctions;

/**
 * this class handles the main calculator page
 *
 * @author Erez
 */

public class CalculatorPage extends BasePageFunctions {

	// Constructor
	public CalculatorPage(WebDriver driver) {
//        this.driver = driver;
		super(driver);
	}

	private By PlusBtn = By.id("BtnPlus");
	private By MinusBtn = By.id("BtnMinus");
	private By EqualToBtn = By.id("BtnCalc");
	private By ClearBtn = By.id("BtnClear");
	private By OpenBrackets = By.id("BtnParanL");
	private By CloseBrackets = By.id("BtnParanR");
	private By MultBtn = By.id("BtnMult");
	private By SinBtn = By.id("BtnSin");
	private By historyElements = By.xpath("//p[@title and @class='l']");
	private By historySequenceElements = By.xpath("//p[@title and @class='r']");
	private By histBtn = By.cssSelector(".history");
	private By resultField = By.cssSelector("#input");

	// Page actions

	// perform Add formula
	public boolean AddFormula(String No1, String No2) {
		try {
			HandleCalcNumber(No1);
			clickOnElement(PlusBtn);
			HandleCalcNumber(No2);
			performCalculationAndGetResult();
			return true;
		} catch (Exception e) {
			System.out.println("Add Formula was failed" + e.getMessage());
			return false;
		}
	}

	// perform Sub formula
	public boolean SubFormula(String No1, String No2) {
		try {
			HandleCalcNumber(No1);
			clickOnElement(MinusBtn);
			HandleCalcNumber(No2);
			performCalculationAndGetResult();
			return true;
		} catch (Exception e) {
			System.out.println("Sub Formula was failed" + e.getMessage());
			return false;
		}
	}

	// click on calculate button
	public boolean ClickEqualBtn() {
		return clickOnElement(EqualToBtn);
	}

	// perform Multi formula
	public boolean MultiFormula(String No1, String No2) {
		try {
			clickOnElement(OpenBrackets);
			HandleCalcNumber(No1);
			clickOnElement(MinusBtn);
			HandleCalcNumber(No2);
			clickOnElement(CloseBrackets);
			clickOnElement(MultBtn);
			HandleCalcNumber(No2);
			performCalculationAndGetResult();
			return true;
		} catch (Exception e) {
			System.out.println("Brackets Formula was failed" + e.getMessage());
			return false;
		}
	}

	// perform Sinus formula
	public boolean SinFormula(String No1) {
		try {
			clickOnElement(SinBtn);
			HandleCalcNumber(No1);
			clickOnElement(CloseBrackets);
			performCalculationAndGetResult();
			return true;
		} catch (Exception e) {
			System.out.println("Sinus Formula was failed" + e.getMessage());
			return false;
		}

	}

	// Clear calc results
	public boolean ClearCalc() {
		return clickOnElement(ClearBtn);
	}

	
	// In case the input number is bigger than 9, split and click digits individually
	public void HandleCalcNumber(String number) {
		if (Integer.parseInt(number) > 9) {
			List<Integer> digits = splitNumberIntoDigits(Integer.parseInt(number));
			for (int item : digits) {
				String strNumber = Integer.toString(item);
				clickOnElement(By.id("Btn" + strNumber));
			}
		} else
			clickOnElement(By.id("Btn" + number));
	}

	// get and return result value
	public String getResult() {
		waitForElementToBeVisible(resultField);
		return getElementAttribute(resultField, "value");
//        return driver.findElement(resultField).getAttribute("value");
	}

	// check if history list matched the previous performed formulas
	public boolean isHistoryCorrect(String[] expectedHistory) {
		clickOnElement(histBtn);
		List<WebElement> historyItems = getDriver().findElements(historyElements);
		if (historyItems.size() != expectedHistory.length)
			return false;

		// Iterate through each element in the list
		int i = 0;
		for (WebElement element : historyItems) {
			// Retrieve the attribute "title" from each element
			String title = element.getAttribute("title");
			System.out.println("Element Title: " + title);
			if (!title.equals(expectedHistory[i]))
				return false;
			i += 1;
		}
		return true;
	}

	// wait for value to appear
	public Boolean waitForValue(String sValue) {
		try {
			waitForValueToAppear(resultField, sValue);
			return true;
		} catch (Exception e) {
			System.out.println("Wait For Value " + sValue + " was failed" + e.getMessage());
			return false;
		}
	}

	// get result from result field
	public String performCalculationAndGetResult() {
		waitForElementToBeVisible(resultField);
		String initialResult = getElementAttribute(resultField, "value");
		clickOnElement(EqualToBtn);
		waitForElementAttributeToChange(resultField, "value", initialResult);
		return getElementAttribute(resultField, "value").trim();
	}

	// open history list
	public Boolean openHistoryList() {
		return clickOnElement(histBtn);
	}

	// get history calculation
	public String ReturnHistoryExercise() {
		return getElementAttribute(historyElements, "title");
	}

	// get history result
	public String ReturnHistoryResult() {
		return getElementAttribute(historySequenceElements, "title");
	}

}
