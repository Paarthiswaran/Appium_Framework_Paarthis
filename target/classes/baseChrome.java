package Utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class baseChrome {
	
	public static AndroidDriver<AndroidElement> capabilities() throws IOException {
		
				//Implementing properties concept
				FileReader reader=new FileReader("E:\\EclipseWorkspace\\Appium_Tutorial_Paarthis_udemy\\Config.properties");  

				Properties p=new Properties();  
				p.load(reader);  
		
		AndroidDriver<AndroidElement> driver;
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, p.getProperty("device"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		return driver;

	}

}