package utilities;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;

import io.qameta.allure.Attachment;

public class AllureAttachment {

	@Attachment(value = "{0}", type = "text/plain")
	public static String addTextAttachment(String message) {
		return message;
	}
	
	@Attachment(value = "Page Screenshot", type = "image/png", fileExtension = ".png")
	 static byte[] attachScreenshot(WebDriver driver) {
	  return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	 }
	
	@Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
	public static byte[] getPageSource(WebDriver driver) {
		return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
	}
	
	@Attachment(value = "Console Logs", type = "text/plain", fileExtension = ".txt")
    public static String attachConsoleLogs(WebDriver driver) {
        return driver
                .manage()
                .logs()
                .get(LogType.BROWSER)
                .getAll()
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
