package Utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

//import EcommerceApp.AppiumServiceBuilder;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class base {
	
	public static AndroidDriver<AndroidElement> driver;

	public static AndroidDriver<AndroidElement> capabilities() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		// apk , device , 4723
		//commands
		// Android driver
		
		//Implementing properties concept
		System.getProperty("user.dir");
		System.out.println(System.getProperty("user.dir"));
		FileReader reader=new FileReader(System.getProperty("user.dir")+"\\Config.properties");  

		Properties p=new Properties();  
		p.load(reader);  
		System.out.println(p.getProperty("App"));
		System.out.println(p.getProperty("device"));
		
		File appDir = new File("src");
		File app = new File(appDir, p.getProperty("App"));    //Getting app name from properties file
		DesiredCapabilities  DC = new DesiredCapabilities();
		
		startEmulator();
		DC.setCapability(MobileCapabilityType.DEVICE_NAME, p.getProperty("device")); //Getting device name from properties file
		
		DC.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		//UI Automator -> Android Apps
		// uiautomator2
		
		DC.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),DC);
		
		return driver;

	}
	
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\Utilities\\startEmulator.bat");
		Thread.sleep(6000);
	}
	
	public static AppiumDriverLocalService service;

	public static AppiumDriverLocalService startServer()  {

		boolean flag = CheckIfServerIsRunning(4723);

		if (!flag) {
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withAppiumJS
					(new File("C:\\Windows\\System32\\node_modules\\appium\\build\\lib\\main.js"))); 
			service.start();
		}
		return service;  
	}
	
	public static boolean CheckIfServerIsRunning(int port) {

		boolean isServerRunning=false;
		ServerSocket serverSocket;
		try {
			serverSocket=new ServerSocket(port);
			serverSocket.close();
		}
		catch(IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		}
		finally {
			serverSocket=null;
		} return isServerRunning;
	}
	
	public static void getScreenshot(String s) throws IOException {
		 
		try {
			
		// To create reference of TakesScreenshot
		//TakesScreenshot screenshot = (TakesScreenshot) driver;
		// Call method to capture screenshot
		DesiredCapabilities  DC = new DesiredCapabilities();
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),DC);
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Copy files to specific location
		// result.getName() will return name of test case so that screenshot name will
		// be same as test case name
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

		FileUtils.copyFile(src,new File(System.getProperty("user.dir")+"\\Screenshots\\"+s+ timeStamp +".jpeg"));
		System.out.println("Successfully captured a screenshot");
		} catch (Exception e) {
		System.out.println("Exception while taking screenshot " + e.getMessage());
		}
		 
		} 
	
	public static AndroidDriver<AndroidElement> cloudCapabilities() throws IOException, InterruptedException {
		
		FileReader reader=new FileReader(System.getProperty("user.dir")+"\\Config.properties");  
		Properties p=new Properties();  
		p.load(reader);  
		System.out.println(p.getProperty("App"));
		System.out.println(p.getProperty("device"));
		File appDir = new File("src");
		File app = new File(appDir, p.getProperty("App"));    //Getting app name from properties file
		DesiredCapabilities  DC = new DesiredCapabilities();
	
		DC.setCapability("browserstack.user", "paarthiswarann_kbULy6");
		DC.setCapability("browserstack.key", "5iysEpqSyYEHf38uhois");
		startEmulator();
		if(p.getProperty("App").equalsIgnoreCase("General-Store.apk"))
		{
		DC.setCapability("app", "bs://6ea194a3fd41d2d5ccdf978b8de105c5c5f7be1a");
		}
		DC.setCapability("device", "Samsung Galaxy S22 Ultra");
		DC.setCapability("os_version", "12.0");
		DC.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hub"),DC);
		
		return driver;

	}
		 
		

}
