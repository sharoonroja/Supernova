package com.qa.ING.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.TestException;

import com.google.common.collect.Table.Cell;
import com.qa.ING.Base.BaseTest;

public class TestUtil extends BaseTest {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = EXCEL_PATH;
	public static WebDriver _driver;
	public static WebDriverWait wait;
	public Actions actions;
	public Select select;

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	// switch to frame
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	// COMMON DATA PROVIDER
	/*
	 * public static Object[][] getTestData(String sheetName) { FileInputStream
	 * file = null; try { file = new FileInputStream(TESTDATA_SHEET_PATH); }
	 * catch (FileNotFoundException e) { e.printStackTrace(); } try { book =
	 * WorkbookFactory.create(file); } catch (Exception e) {
	 * e.printStackTrace(); } sheet = book.getSheet(sheetName); Object[][] data
	 * = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; //
	 * System.out.println(sheet.getLastRowNum() + "--------" + //
	 * sheet.getRow(0).getLastCellNum()); for (int i = 0; i <
	 * sheet.getLastRowNum(); i++) { for (int k = 0; k <
	 * sheet.getRow(0).getLastCellNum(); k++) { data[i][k] = sheet.getRow(i +
	 * 1).getCell(k).toString(); System.out.println(data[i][k]); } } return
	 * data; }
	 */
	public static void countPdf(String xPath, String message) throws Exception {

		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.xpath(xPath));
		int count = 0;
		for (WebElement e : list) {
			if (e.getText().endsWith("PDF")) {
				count++;
			}
		}
	}
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/FailesScreenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void downloadListOfPDFs(String xPath, String message) throws Exception {

		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.xpath(xPath));
		int count = 0;
		for (WebElement e : list) {

			if (e.getText().endsWith("PDF")) {
				e.click();
				count++;
			}
		}
		List<String> arrayOptions = new ArrayList<String>();
		for (WebElement option : list) {
			arrayOptions.add(option.getText());
		}
		int listSize = arrayOptions.size();
		System.out.println(message + listSize);
		for (int i = 0; i < listSize; i++) {

			String text = arrayOptions.get(i);
			System.out.println(text);
		}

	}

	public static void scrolltoElement(WebElement element) throws Exception {

		int x = element.getLocation().getX();
		int y = element.getLocation().getY();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + "," + y + ")");
		Thread.sleep(2000);
	}

	public void getTextOfElement(WebElement element) {
		String str = element.getText();
		System.out.println(str);

	}

	public static void writeDataToNotePad(String xpath, String path) throws Exception {

		FileOutputStream f = new FileOutputStream(System.getProperty("user.dir") + path, false);
		List<WebElement> list = driver.findElements(By.xpath(xpath));

		List<String> arrayOptions = new ArrayList<String>();
		for (WebElement option : list) {
			arrayOptions.add(option.getText());
		}
		int listSize = arrayOptions.size();

		for (int i = 0; i < listSize; i++) {

			String text = arrayOptions.get(i);

			byte[] contentInBytes = text.getBytes();
			f.write(contentInBytes);
		}
		f.flush();

		System.out.println("written successfully..");

	}

	public static void listOfElements(String xpath) {
		List<WebElement> list = driver.findElements(By.xpath(xpath));

		List<String> arrayOptions = new ArrayList<String>();

		for (WebElement option : list) {
			arrayOptions.add(option.getText());
		}
		int listsize = arrayOptions.size();
		System.out.println("count of sections:-" + listsize);
		for (int i = 0; i < listsize; i++) {

			String text = arrayOptions.get(i);
			System.out.println(text);
		}

	}

	public static void copyTable(String filePath, String tableXpath) throws Exception {
		FileOutputStream f = new FileOutputStream(System.getProperty("user.dir") + filePath, false);
		// To locate table.
		WebElement mytable = driver.findElement(By.xpath(tableXpath));
		// To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		// To calculate no of rows In table.
		int rows_count = rows_table.size();
		// Loop will execute till the last row of table.
		for (int row = 0; row < rows_count; row++) {
			// To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			// To calculate no of columns (cells). In that specific row.
			int columns_count = Columns_row.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);
			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Columns_row.get(column).getText();

				byte[] contentInBytes = celtext.getBytes();
				f.write(contentInBytes);
				System.out
						.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
				// .setExcelData("BoozangTable",row,column,celtext);
			}
			System.out.println("-------------------------------------------------- ");
		}
		f.flush();
		System.out.println(" ----written succesfully---");
	}

	
	public  static void writeTabletoExcel( String tableXpath,String filePath,String sheetName) throws Exception{
		WebElement mytable = driver.findElement(By.xpath(tableXpath));
		// To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		// To calculate no of rows In table.
		int iRowsCount = rows_table.size();     
		

		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+filePath);                                 

		XSSFWorkbook wkb = new XSSFWorkbook();       
		XSSFSheet sheet1 = wkb.createSheet(sheetName); 

		for (int i=0;i<iRowsCount;i++)      
		{               
			List<WebElement> Cols_row = rows_table.get(i).findElements(By.tagName("td"));
			int iColsCount = Cols_row.size();     
			System.out.println("Selected web table has " +iRowsCount+ " Rows and " +iColsCount+ " Columns");     
			System.out.println();      
			XSSFRow excelRow = sheet1.createRow(i);
		for (int j=0; j<iColsCount;j++)                    
		{           
			                    
			String celtext = Cols_row.get(j).getText();
		    System.out.println(celtext);
			XSSFCell excelCell = excelRow.createCell(j);                  
			excelCell.setCellValue(celtext);  
			}                    
		 
		}
		fos.flush();     
		wkb.write(fos);     
		fos.close();
	}
	public static void writeDataToExcel(String xpath,String filepath,String sheetName) throws Exception{
		
         XSSFWorkbook workbook = new XSSFWorkbook();
         XSSFSheet sheet = workbook.createSheet(sheetName);
//I am creating and adding list just for illustration purpose only
         List<WebElement> list = driver.findElements(By.xpath(xpath));
         List<String> arrayOptions = new ArrayList<String>();
         File f= new File(System.getProperty("user.dir")+filepath);
         int rowCount=0;
 
 		for (WebElement option : list) {
 			
 			arrayOptions.add(option.getText());
 		}
 		System.out.println("Size of list:"+arrayOptions.size());
 		XSSFRow row =sheet.createRow(rowCount);
 		int columnCount=0;
 		
 		for(String str: arrayOptions){
 			System.out.println("Elements text:"+str);
 			XSSFCell cell= row.createCell(columnCount++);
 				cell.setCellValue(str);
 			
 		}
 		 FileOutputStream out = new FileOutputStream(f);
         workbook.write(out);
         out.close();
         workbook.close();
		
         
	}
	 public static int fetchSectionsWithCount(String xPath,String filePath) throws IOException

     {

            FileWriter fr;

            fr = new FileWriter(System.getProperty("user.dir"+filePath));

            BufferedWriter br=new BufferedWriter(fr);

            int rownum=driver.findElements(By.xpath(xPath)).size();

            br.write("Disclosures document count: "+rownum);

            System.out.println("count of documents available under Disclosures is "+rownum);
            List<WebElement> list = driver.findElements(By.xpath(xPath));

    		List<String> arrayOptions = new ArrayList<String>();
    		for (WebElement option : list) {
    			arrayOptions.add(option.getText());
    		}
    		int listSize = arrayOptions.size();

    		for (int i = 0; i < listSize; i++) {

    			String text = arrayOptions.get(i);
    			 

                 System.out.println(text);

                 br.newLine();

                 br.write(text);
     }

            br.close();
            return rownum;

     }
	
}



