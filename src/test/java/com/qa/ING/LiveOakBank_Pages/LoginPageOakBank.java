package com.qa.ING.LiveOakBank_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.ING.Base.BaseTest;
import com.qa.ING.Utility.TestUtil;

public class LoginPageOakBank extends BaseTest {
	public LoginPageOakBank(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//*[text()='Speed']")
	public WebElement SpeedSec;
	
	@FindBy(xpath="//*[text()='Personal Banking']")
	public  WebElement PersonalBankingBtn;
	
	@FindBy(xpath="/html/body/div/section/section/div/div/h4")
	public WebElement POSavingsec;
	
	@FindBy(xpath="//a[text()='Resource Center']")
	public WebElement ResourceCenterBtn;
	
	@FindBy(xpath="/html/body/div[1]/section/section[2]/div[1]/div[1]/h4")
	public WebElement PersonalCDRatesSec;
	@FindBy(xpath="/html/body/div[1]/section/section/div/div[1]/select")
	public WebElement SelectACatBtn;
	
	String SecsPath="/html/body/div/section/section/div/div/ul/li/div/h5";
	String descPath="/html/body/div/section/section/div/div/ul/li/div/p";
	String POSavingsxpth="/html/body/div[1]/section/section[2]/div[1]/div[1]/h4";
	String PCDRatesxpth="/html/body/div[1]/section/section[2]/div[1]";
	String POSavingsFeaturesxpth="/html/body/div[1]/section/div[2]/div/div/div/ul/li";
	String PCDRatesFeaturesxpth="/html/body/div[1]/section/div[3]/div/div/div/ul/li";
	String PersonalBankingSections="/html/body/div[1]/section/section/div/div/h4";
	String POSavingsIRate_APY="/html/body/div[1]/section/section[1]/a/div";
	String PersonalCDRatesIRates_APY="/html/body/div[1]/section/section[2]/a/div";
	String CategoryImageDesc="//*[@id='page-full-width']/div/div/div/div/div/h3/a";

	public void scrolltoSections() throws Exception{
	TestUtil.scrolltoElement( SpeedSec);
	}
	public void writeSectionsIntoExcel() throws Exception{
	TestUtil.writeDataToExcel(SecsPath,"/LiveOakBankFetchedData/LoginPageSections.xlsx","loginPageSections");
	}
	public void writeSecsDescIntoExcel() throws Exception{
		TestUtil.writeDataToExcel(descPath,"/LiveOakBankFetchedData/LoginPageSectionsDesc.xlsx","loginPageSecsDesc");
		}
	public void clickPersonalBaking(){
		PersonalBankingBtn.click();
	}
	public void fetchPersonalBankingSections() throws Exception{
		TestUtil.scrolltoElement(POSavingsec);
		TestUtil.writeDataToExcel(PersonalBankingSections,"/LiveOakBankFetchedData/PersonalBankingSecs.xlsx","PersonalBankSecs");
	}
	public void writePOSavingsFeatures() throws Exception{
		TestUtil.writeDataToExcel(POSavingsFeaturesxpth,"/LiveOakBankFetchedData/POSavingsFeatures.xlsx","POSavingsFeatures");
	}
	public void writePersonalCDRatesFeatures() throws Exception{
		TestUtil.scrolltoElement(PersonalCDRatesSec);
		TestUtil.writeDataToExcel(POSavingsFeaturesxpth,"/LiveOakBankFetchedData/PersonalCDRatesFeatures.xlsx","PersonalCDRatesFeatures");
	}
	public void writePOSavingsIRate_APY() throws Exception{
		TestUtil.writeDataToExcel(POSavingsIRate_APY,"/LiveOakBankFetchedData/POSavingsIRate_APY.xlsx","POSavingsIRate_APY");
	}
	public void writePCDRatesIRate_APY() throws Exception{
		TestUtil.writeDataToExcel(PersonalCDRatesIRates_APY,"/LiveOakBankFetchedData/PersonalCDRatesIRate_APY.xlsx","PCDRatesIRate_APY");
	}
	public void clickResourceCenter(){
		ResourceCenterBtn.click();
	}
	public void selectACategory(){
		SelectACatBtn.click();
	}
	public void FetchingcCategoryimageDetails() throws Exception{
		TestUtil.writeDataToExcel(CategoryImageDesc,"/LiveOakBankFetchedData/CategoryImageDesc.xlsx","CategoryImageDesc");
	}
}
