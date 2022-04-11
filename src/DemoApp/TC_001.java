package DemoApp;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import Utilities.base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TC_001 extends base {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
			AndroidDriver<AndroidElement> driver = capabilities();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			// Xpath , id , class name , AndroidUIAutomator
			
			driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
			driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();

			/*driver.findElementByXPath("//android.widget.CheckBox[@resource-id='android:id/checkbox']").click();
			  driver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();
			  driver.findElementByXPath("//android.widget.EditText[@resource-id='android:id/edit']").sendKeys("hello");
			  driver.findElementByXPath("//android.widget.Button[@text='OK']").click();*/

			driver.findElementById("android:id/checkbox").click();
			driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
			driver.findElementByClassName("android.widget.EditText").sendKeys("hello");
			driver.findElementsByClassName("android.widget.Button").get(1).click();

			//driver.findElementByAndroidUIAutomator("attribute("value")");

			driver.findElementByAndroidUIAutomator("text(\"Views\")");

	}

}
