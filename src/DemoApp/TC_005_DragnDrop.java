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

public class TC_005_DragnDrop extends base {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		AndroidDriver<AndroidElement> driver = capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Touch Actions
		TouchAction TA = new TouchAction(driver);
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();
		
		// Drag and drop
		WebElement fromElement = driver.findElementByXPath("//*[@resource-id='io.appium.android.apis:id/drag_dot_1']");
		WebElement toElement = driver.findElementByXPath("//*[@resource-id='io.appium.android.apis:id/drag_dot_2']");

		TA.longPress(longPressOptions().withElement(element(fromElement)).withDuration(ofSeconds(2))).moveTo(element(toElement)).release().perform();
		//Another way (without seconds)
		TA.longPress(element(fromElement)).moveTo(element(toElement)).release().perform();

	}
}