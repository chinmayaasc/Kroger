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
import org.testng.IClass;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.pack.test.AppiumTestBase;

public class ListenerClass extends TestListenerAdapter {
	WebDriver driver=null;
	String filePath = "D:\\AppiumFrameWorkScreenShot\\SCREENSHOTS";
	DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
	Date date = new Date();
	@Override
	public void onTestStart(ITestResult tr) {
		log("Test Started....");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {

		log("Test '" + tr.getName() + "' PASSED");

		// This will print the class name in which the method is present
		log(tr.getTestClass());

		// This will print the priority of the method.
		// If the priority is not defined it will print the default priority as
		// 'o'
		log("Priority of this method is " + tr.getMethod().getPriority());

		System.out.println(".....");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		System.out.println("***** Error "+tr.getName()+" test has failed *****");
		String methodName=tr.getName().toString().trim();
    	takeScreenShot(methodName);
		log("Test '" + tr.getName() + "' FAILED");
		log("Priority of this method is " + tr.getMethod().getPriority());
		System.out.println(".....");
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log("Test '" + tr.getName() + "' SKIPPED");
		System.out.println(".....");
	}

	private void log(String methodName) {
		System.out.println(methodName);
	}

	private void log(IClass testClass) {
		System.out.println(testClass);
	}
	public void takeScreenShot(String methodName) {
    	//get the driver
    	driver=AppiumTestBase.getAndroidDriver();
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with test method name 
            try {
				FileUtils.copyFile(scrFile, new File(filePath + methodName +dateFormat.format(date) + ".png"));
				System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
}
