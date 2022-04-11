package DemoApp;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import Utilities.base;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class TC_003_Swiping extends base {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		AndroidDriver<AndroidElement> driver = capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Touch Actions
		TouchAction TA = new TouchAction(driver);
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='2. Inline']").click();
		driver.findElementByXPath("//*[@content-desc='9']").click();
		
		// Swiping
		// Longpress on element (2 secs) -> move to another element then release 
		WebElement fromElement = driver.findElementByXPath("//*[@content-desc='15']");
		WebElement toElement = driver.findElementByXPath("//*[@content-desc='45']");
		
		TA.longPress(longPressOptions().withElement(element(fromElement)).withDuration(ofSeconds(2))).moveTo(element(toElement)).release().perform();
		
	}
}