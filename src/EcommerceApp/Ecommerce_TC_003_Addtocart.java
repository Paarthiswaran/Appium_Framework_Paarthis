package EcommerceApp;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.base;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Ecommerce_TC_003_Addtocart extends base {
	
	@BeforeTest
	public void killAllNodes() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(6000);
	}

	@Test
	public static void addToCart() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		service=startServer();
		AndroidDriver<AndroidElement> driver = capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Paarthis");
		driver.hideKeyboard();
		driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));");
		driver.findElementByXPath("//android.widget.TextView[@text='Belgium']").click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		
		// Getting the list of all products
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()."
				+ "resourceId(\"com.androidsample.generalstore:id/rvProductList\"))."
				+ "scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));

		int count=    driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

		for(int i=0;i<count;i++)
		{
			String text=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			
			if(text.equalsIgnoreCase("Jordan 6 Rings"))
			{
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		String lastpageText = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		System.out.println(lastpageText);
		Assert.assertEquals("Jordan 6 Rings", lastpageText); 
		service.stop();
	}

}
