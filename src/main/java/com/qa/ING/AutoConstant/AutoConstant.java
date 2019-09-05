
package com.qa.ING.AutoConstant;

public interface AutoConstant {
	String CONFIG_PATH = System.getProperty("user.dir")
			+ ".\\src\\test\\java\\com\\qa\\ING\\Resources\\com.qa.ING.properties\\CONFIG.properties";
	String EXCEL_PATH = System.getProperty("user.dir")
			+ ".\\src\\test\\java\\com\\qa\\ING\\Resources\\com.qa.ING.Excel\\Test_Data_Excel.xlsx";
	String CHROME_KEY = "webdriver.chrome.driver";
	String CHROME_VALUE = ".\\drivers\\chromedriver.exe";
	String GECKO_KEY = "webdriver.gecko.driver";
	String GECKO_VALUE = ".\\drivers\\geckodriver.exe";
	String IE_KEY = "webdriver.ie.driver";
	String IE_VALUE = ".\\drivers\\IEDriverServer.exe";
	
	int IMPLICIT_WAIT = 10;
	int PAGE_LOAD_TIMEOUT = 20;
	int WEBDRIVER_WAIT = 10;
}
