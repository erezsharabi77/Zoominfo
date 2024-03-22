package tests;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
	public WebDriver driver;

	@BeforeClass
	public void setup(ITestContext testContext) {

		WebDriverManager.chromedriver().setup();
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.addArguments("disable-infobars");
		driver = new ChromeDriver(cOptions);
        testContext.setAttribute("WebDriver", this.driver);
		driver.manage().window().maximize();
	    driver.get("https://web2.0calc.com/");
		System.out.println("BEGIN TEST SET");

	}

	@AfterClass
	public void tearDown() {
		System.out.println("The flow is completed");
		driver.quit();
	}


}
