package com.qa.ING.Trello_Tests;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.ING.Base.BaseTest;
import com.qa.ING.Trello_Pages.TrelloLoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class TrelloTest extends BaseTest {
	public TrelloLoginPage Tlogin = new TrelloLoginPage(driver);
	@BeforeTest
	public void setUp() {
		initialization();
		startReport();
		Reporter.log("Browser is opened and url is entered successully");
	}
	@Test
	public void TC_001(){
		logger = extent.startTest("login to the application");
		Tlogin.enterUserName();
		Tlogin.enterPassword();
		Tlogin.clickLogin();
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
