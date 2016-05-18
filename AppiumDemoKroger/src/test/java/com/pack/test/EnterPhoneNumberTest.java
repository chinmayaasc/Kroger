package com.pack.test;

import static com.pack.test.AppiumTestBase.getAndroidDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;













import com.pack.base.AppiumServer;
import com.pack.page.Recharge;

public class EnterPhoneNumberTest extends Recharge{
	AppiumServer as;
	AppiumTestBase ab;
	
	
	public static String PropertyFilePath = ".//resources\\config.properties"; 

	
	@BeforeTest
	public void initialize() throws IOException{
		 as=new AppiumServer();
		 ab=new AppiumTestBase();
		 
		try {
			as.appiumStart();
		} catch (IOException e) {
		
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		ab.initializeAndroidDriverPayTM();
		//ab.loadproperties();
		

	}
	
	
	
	@Test(dataProvider="createAccount")
	public void phoneNumberText(String phoneNumber) {
		
		getAndroidDriver().findElement(phoneNumberText).sendKeys(phoneNumber);
		VerifyOperatorName();
		
		
		clickOnBrowsePlans();
		try {
			clickOnSpecial();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	
		getRechargeAmount();
		
		
		clickOnProcedToRecharge();
		clickOnProcedToPay();
	}
	 @Test(priority=1)
	    public void cancelRechargeTest(){
	    	cancelRecharge();
			getAlertTextName();
			clickYes();
			getFailedMessage();	
	    }
	    
	    @Test(priority=2)
	    public void failedTest(){
	    	getFailedMessage1();
				
	    }
	    @Test(priority=3)
	    public void testCaseSkip(){
			System.out.println("Im in Conditional Skip");
			int a=1;
			if(a>0)
			throw new SkipException("Skipping this exception");
			System.out.println("Executed Successfully");
		}
	@DataProvider(name="createAccount")
	public Object[][] createAccData() throws IOException {
	
		// load a properties file
		 Properties prop=new Properties();  
     	
    	 InputStream	 input = new FileInputStream(PropertyFilePath); 
    	 prop.load(input);
	
	String	excelpath = prop.getProperty("excelpath");
	String	excelSheetname = prop.getProperty("sheetname");
		System.out.println(excelpath);
		System.out.println(excelSheetname);
		Object[][] arrayObject = getExcelData(excelpath,excelSheetname);
		return arrayObject;
	}

	
	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();
			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	@AfterTest
	public void tearDown(){
		getAndroidDriver().quit();

		try {
			as.appiumStop();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			}
}
