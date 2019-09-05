package com.qa.ING.Base;

import java.awt.AWTException;
import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

import com.qa.ING.AutoConstant.AutoConstant;
import com.qa.ING.LiveOakBank_TestScripts.LoginPageOakBank_Test;
import com.qa.ING.Utility.WebEventListener;

public class BaseTest implements AutoConstant {
	public  static EventFiringWebDriver e_driver;
	 
	public static WebEventListener eventListener;
	
	public static WebDriver driver;
	public static Properties config;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	 
	

	static {
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
		System.setProperty("atu.reporter.config", "./atu.properties");
	}

	public BaseTest() {

		try {

			config = new Properties();

			FileInputStream fis = new FileInputStream(CONFIG_PATH);

			config.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty("atu.reporter.config", "./atu.properties");
	}

	public static void initialization()

	{

		String browserName = config.getProperty("Browser1");

		if (browserName.equals("chrome")) {

			Map<String, Object> prefs = new HashMap<String, Object>();
			// Set the notification setting it will override the default setting
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-notifications");

			prefs.put("download.default_directory", "C:/Users/user1/Documents");
			prefs.put("download.prompt_for_download", false);

			prefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");

			prefs.put("plugins.always_open_pdf_externally", true);

			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);
			
			ATUReports.setWebDriver(driver);
			  
			ATUReports.indexPageDescription = "ING Project Description";
			 
			ATUReports.setAuthorInfo("Sharoon roja", Utils.getCurrentTime(), "1.0");
			
			Reporter.log("Browser is opened");

		}
		 else if(browserName.equals("FF")){
			 
			 
			 
			  
			 driver = new
			   FirefoxDriver();
		 }
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		  
		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.get(config.getProperty("Test_URL"));
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT,
		 TimeUnit.SECONDS);

		Reporter.log("URL is hitted");

	}

	public static void waitForLoad(WebDriver driver) {

		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {

				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

			}
		};

		WebDriverWait wait = new WebDriverWait(driver, 2000);

		wait.until(pageLoadCondition);

	}

	public static void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + ".//test-output/Extent.html", true);
		extent.addSystemInfo("Host Name", "HCL Technologies").addSystemInfo("Environment", "Automation(QA)")
				.addSystemInfo("User Name", "Sharooon roja");
		
	}
	

	public static String getScreenshot(WebDriver driver, String screenShotName) throws Exception {
		
		Date d = new Date();
		String currentDate = d.toString().replaceAll(":", "-");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String destFilePath = System.getProperty("user.dir") + "\\FailedScreenshots\\" + currentDate + "\\" + screenShotName
				+ "_screenshot.png";
		File destFile = new File(destFilePath);
		FileUtils.copyFile(srcFile, destFile);
		return destFilePath;
	}
	

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
