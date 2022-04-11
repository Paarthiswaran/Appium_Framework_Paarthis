package EcommerceApp;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.base;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

public class Ecommerce_TC_004_CheckSumInCart extends base {
	
	@BeforeTest
	public void killAllNodes() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(6000);
	}
	
	@Test
	public static void checkSumInCart() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		AndroidDriver<AndroidElement> driver = capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Paarthis");
		driver.hideKeyboard();
		driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));");
		driver.findElementByXPath("//android.widget.TextView[@text='Belgium']").click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		Thread.sleep(4000);

		int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		double sum = 0;
		for(int i=0;i<count;i++)
		{
		String amount1= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
		double amount=getAmount(amount1);
		sum=sum+amount;//280.97+116.97
		}
		System.out.println(sum+"sum of products");
		String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double totalValue=getAmount(total);
		System.out.println(totalValue+"Total value of products");

		Assert.assertEquals(sum, totalValue); 

		//Mobile Gestures - Click on checkbox , longpress on the text , close the pop-up and click on Proceed button

		WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t=new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();

		WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		}
		//Method to remove $ from text obtained in script and convert the text to double
		public static double getAmount(String value)
		{
		value= value.substring(1);
		double amount2value=Double.parseDouble(value);
		return amount2value;
		}
	}

