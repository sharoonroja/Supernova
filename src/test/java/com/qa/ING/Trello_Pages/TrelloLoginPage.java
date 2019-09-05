package com.qa.ING.Trello_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.ING.Base.BaseTest;

public class TrelloLoginPage extends BaseTest {
 public TrelloLoginPage(WebDriver driver){
	 this.driver= driver;
	 PageFactory.initElements(driver,this);
 }
 
 @FindBy (xpath="//input [@id='user']")
 public WebElement UNTxtBox;
 
 @FindBy (xpath="//input [@id='password']")
 public WebElement PWDTxtBox;
 @FindBy (xpath="//input [@id='login']")
 public WebElement LoginBtn;
 
 public void enterUserName(){
	 UNTxtBox.sendKeys("sharoonrose@gmail.com");
 }
 public void  enterPassword(){
	 PWDTxtBox.sendKeys("gsr1234@");
 }
 public void clickLogin(){
	 LoginBtn.click();
 }
}
