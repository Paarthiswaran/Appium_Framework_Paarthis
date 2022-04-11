package EcommerceApp;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.TestData;
import Utilities.base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Ecommerce_TC_001 extends base {
	
			@BeforeTest
			public void killAllNodes() throws InterruptedException, IOException {
				Runtime.getRuntime().exec("taskkill /F /IM node.exe");
				Thread.sleep(6000);
			}

			@Test(dataProvider="InputData",dataProviderClass=TestData.class)
			public void signUpRegistration(String dataFromDataProviderClass) throws IOException, InterruptedException {
			service=startServer();
			AndroidDriver<AndroidElement> driver = capabilities();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys(dataFromDataProviderClass);
			driver.hideKeyboard();
			driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));");
			driver.findElementByXPath("//android.widget.TextView[@text='India']").click();
			driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
			service.stop();
			}
			
			
	}

