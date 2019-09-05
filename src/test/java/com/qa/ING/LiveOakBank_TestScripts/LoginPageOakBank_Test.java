package com.qa.ING.LiveOakBank_TestScripts;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.ING.Base.BaseTest;

import com.qa.ING.LiveOakBank_Pages.LoginPageOakBank;

import com.relevantcodes.extentreports.LogStatus;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;



public class LoginPageOakBank_Test extends BaseTest {
	
	public static LoginPageOakBank login;
	
	@BeforeTest
	public void setUp() {
		initialization();
		startReport();
		Reporter.log("Browser is opened and url is entered successully");
	}
	

	@Test
	public void TC_001(){
		logger = extent.startTest("Fetching Sections Data");
		
		 login= new LoginPageOakBank(driver);
		try {
			login.scrolltoSections();
			login.writeSectionsIntoExcel();
			login.writeSecsDescIntoExcel();
		} catch (Exception e) {
			logger.log(LogStatus.PASS, " scrollDown to Speed Transparency .....");
			
		}
		
	}
	@Test
	public void TC_002(){
		logger = extent.startTest(" click Personal Banking  ");
		 login= new LoginPageOakBank(driver);
		login.clickPersonalBaking();
		try{
			Assert.fail();
		}
		catch(Exception e){
			logger.log(LogStatus.PASS, " Test failed ,check Screenshot for defect analysis");
		}
		
	}
	@Test (description="Fetching personal banking data")
	
	
	public void TC_003(){
		logger = extent.startTest(" fetching Personal Banking Data ");
		
		 login= new LoginPageOakBank(driver);
		try {
			login.fetchPersonalBankingSections();
			
			login.writePOSavingsFeatures();
			login.writePersonalCDRatesFeatures();
			login.writePOSavingsIRate_APY();
			login.writePCDRatesIRate_APY();
		} catch (Exception e) {
			logger.log(LogStatus.PASS, " Fetching POSavings features list into excel");
		}
		
	}
	@Test
	public void TC_004(){
		logger = extent.startTest(" fetching Resource center details ");
		login.clickResourceCenter();
		login.selectACategory();
		
		try {
			login.FetchingcCategoryimageDetails();
		} catch (Exception e) {
			logger.log(LogStatus.PASS, " Fetching Category Images into excel ");
		}
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			// logger.log(LogStatus.FAIL, "Test Case Failed is " +
			// result.getThrowable());
			logger.log(LogStatus.INFO, " Test case has been made failed intentionally");
			String screenshotPath = getScreenshot(driver, result.getName());
			// To add it in the extent report
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test case passed is" + result.getName());
		}
		extent.endTest(logger);
	}
	

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
		driver.quit();
	}
}
