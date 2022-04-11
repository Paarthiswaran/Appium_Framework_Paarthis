package EcommerceApp;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Ecommerce_TC_002_ToastMsg extends base {
	
	
	@BeforeTest
	public void killAllNodes() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(6000);
	}

	@Test
	public void toastMsg() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		service=startServer();
		AndroidDriver<AndroidElement> driver = capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Paarthis");
		//driver.hideKeyboard();
		driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));");
		driver.findElementByXPath("//android.widget.TextView[@text='Belgium']").click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		//Identifying the toast message and getting the value
		driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");

		String toastMsg = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		System.out.println(toastMsg);

		String msgInApp = "Please enter your name";

		if(toastMsg.equals(msgInApp))
		{
			System.out.println("Pass - Toast message validated");
		}
		else
		{
			System.out.println("Fail - Toast message NOT validated");
		}
		service.stop();
	}

}
