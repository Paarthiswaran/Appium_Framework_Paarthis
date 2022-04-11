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

public class TC_002_Gestures extends base {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
			AndroidDriver<AndroidElement> driver = capabilities();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			// Touch Actions
				TouchAction TA = new TouchAction(driver);
				
			driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
			WebElement expandableList = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
			TA.tap(tapOptions().withElement(element(expandableList))).perform();
			
			//driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']").click();
			driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
		
			WebElement peopleNames = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
			TA.longPress(longPressOptions().withElement(element(peopleNames)).withDuration(ofSeconds(2))).release().perform();
			
			if(driver.findElementByXPath("//android.widget.TextView[@text='Sample action']").isDisplayed()) 
			{
				System.out.println("Element is visible - Pass");
			}
			else
			{
				System.out.println("Element is NOT visible - Fail");
			}
					

	}

}
