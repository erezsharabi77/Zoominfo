package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.CalculatorPage;
import utilities.ListenerClass;


@Listeners({ListenerClass.class})
public class CalculatorTests extends BaseTest {

//    private CalculatorPage calculatorPage;

	@Test(priority = 1, description = "tc01_testAddition")
	@Description("2 + 3 = 5")
	public void tc01_testAddition() throws IOException, InterruptedException
	{
		System.out.println("Addition Test begins");
		System.out.println("*******START ADDITION TEST****************");
		CalculatorPage cp = new CalculatorPage(driver);
		String expectedResult = "5";
		String expectedExercise = "2+3";
		System.out.println("Formula = " + expectedExercise);
		Assert.assertTrue(cp.AddFormula("2", "3"), "Add Forumla was failed");
		//Get the result - Expected result is 5
		String result = cp.getResult();
		//Check if actual result is equal to 5
		System.out.println("Result = " + result);
		Assert.assertEquals(result, "5");
		Assert.assertTrue(cp.ClearCalc(), "calculator wasn't cleared");
		Assert.assertTrue(cp.openHistoryList(), "History list was not open");
        Assert.assertEquals(cp.ReturnHistoryExercise(), expectedExercise);
        Assert.assertEquals(cp.ReturnHistoryResult(), expectedResult);
		System.out.println("*******END ADDITION TEST****************");
	}


	@Test(priority = 2, description = "tc02_testSubtraction")
	@Description("10 - 2 = 8")
	public void tc02_Subtraction() throws IOException, InterruptedException
	{
		System.out.println("Subtraction Test begins");
		System.out.println("*******START SUBSTRACTION TEST****************");
		CalculatorPage cp = new CalculatorPage(driver);
		String expectedResult = "8";
		String expectedExercise = "10-2";
		System.out.println("Formula = " + expectedExercise);
		Assert.assertTrue(cp.SubFormula("10", "2"), "Sub Forumla was failed");
		//Get the result - Expected result is 8
		String result = cp.getResult();
		System.out.println("Result = " + result);
		//Check if actual result is equal to 8
		Assert.assertEquals(result, expectedResult);
		Assert.assertTrue(cp.ClearCalc(), "calculator wasn't cleared");
		Assert.assertTrue(cp.openHistoryList(), "History list was not open");
		Assert.assertEquals(cp.ReturnHistoryExercise(), expectedExercise);
        Assert.assertEquals(cp.ReturnHistoryResult(), expectedResult);
		System.out.println("*******END SUBSTRACTION TEST****************");
	}
	
	
	@Test(priority = 3, description = "tc03_Multi")
	@Description("(10 - 2) * 2 != 20")
	public void tc03_Multi() throws IOException, InterruptedException
	{
		System.out.println("Multi Test begins");
		System.out.println("*******START MULTI TEST****************");
		CalculatorPage cp = new CalculatorPage(driver);
		String expectedResult = "16";
		String expectedExercise = "(10-2)*2";
		System.out.println("Formula = " + expectedExercise);
		Assert.assertTrue(cp.MultiFormula("10", "2"), "Multi Forumla was failed");
		//Get the result - Expected result is 16, not 20
		String result = cp.getResult();
		//Check if actual result is not equal to 20
		System.out.println("Result = " + result);
		Assert.assertNotEquals(result, "20");
		Assert.assertTrue(cp.ClearCalc(), "calculator wasn't cleared");
		Assert.assertTrue(cp.openHistoryList(), "History list was not open");
		Assert.assertEquals(cp.ReturnHistoryExercise(), expectedExercise);
        Assert.assertEquals(cp.ReturnHistoryResult(), expectedResult);
		System.out.println("*******END MULTI TEST****************");
	}
	
	
	@Test(priority = 4, description = "tc04_SinTest")
	@Description("sin(30) = 0.5")
	public void tc04_SinTest() throws IOException, InterruptedException
	{
		System.out.println("Sin Test begins");
		System.out.println("*******START SIN TEST****************");
		CalculatorPage cp = new CalculatorPage(driver);
		String expectedExercise = "sin(30)";
		String expectedResult = "0.5";
		System.out.println("Formula = " + expectedExercise);
		Assert.assertTrue(cp.SinFormula("30"),"Sin Forumla was failed");
		//Get the result - Expected result is 0.5
		String result = cp.getResult();
		//Check if actual result is equal to 0.5
		System.out.println("Result = " + result);
		Assert.assertEquals(result, expectedResult);
		Assert.assertTrue(cp.ClearCalc(), "calculator wasn't cleared");
		Assert.assertTrue(cp.openHistoryList(), "History list was not open");
		Assert.assertEquals(cp.ReturnHistoryExercise(), expectedExercise);
        Assert.assertEquals(cp.ReturnHistoryResult(), expectedResult);
		System.out.println("*******END SIN TEST****************");
	}
	
	
	@Test(priority = 5, description = "tc05_HistoryTest")
	@Description("Validate History of all formulas")
	public void tc05_HistoryTest() throws IOException, InterruptedException
	{
		System.out.println("History Test begins");
		System.out.println("*******START HISTORY TEST****************");
		CalculatorPage cp = new CalculatorPage(driver);
		//Perform 2+3
		String[] expectedHistory = {"sin(30)", "(10-2)*2", "10-2", "2+3"};
        Assert.assertTrue(cp.isHistoryCorrect(expectedHistory),  "Is History correct was failed");
//		cp.ClickEqualBtn();
//		Thread.sleep(300);
		System.out.println("*******END HISTORY TEST****************");
	}
	
}
