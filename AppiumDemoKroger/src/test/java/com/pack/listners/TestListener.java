package com.pack.listners;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{
	private WebDriver driver;
	String filePath = "C:\\Users\\chinmayas\\git1\\AppiumDemoFrameWork\\AppiumFrameWorkScreenShot";
	
	//C:\\Users\\chinmayas\\git1\\DemoFrameWork\\ScreenShot
	DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
	Date date = new Date();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
    	System.out.println("***** Error "+result.getName()+" test has failed *****");
    	String methodName=result.getName().toString().trim();
    	takeScreenShot(methodName);
    	
	}
    	
    	 public void takeScreenShot(String methodName) {
    	    	
    	    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	         //The below method will save the screen shot in d drive with test method name 
    	            try {
    					FileUtils.copyFile(scrFile, new File(filePath+methodName+dateFormat.format(date)+".png"));
    					System.out.println("***Placed screen shot in "+filePath+" ***");
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    	    }

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
