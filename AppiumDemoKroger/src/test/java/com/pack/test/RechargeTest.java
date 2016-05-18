package com.pack.test;

import static com.pack.test.AppiumTestBase.getAndroidDriver;

import java.io.IOException;

import junit.framework.Assert;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Verify;
import com.pack.base.AppiumServer;
import com.pack.page.Recharge;
public class RechargeTest extends Recharge{
	//String phoneNumber="9740172751";
	AppiumServer as;
	AppiumTestBase ab;
	


   // @Parameters({ "phoneNumber"})
	/*@Test(priority=1)
	public void rechargePaYTM{
		
		VerifyOperatorName();
	
		//operatorName();
		clickOnBrowsePlans();
		try {
			clickOnSpecial();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//verify Recharge amount returned by the application
		verifyRechargeAmount();
		
		clickOnProcedToRecharge();
		clickOnProcedToPay();
			
	
	}*/
    @Test(priority=2)
    public void cancelRechargeTest(){
    	cancelRecharge();
		getAlertTextName();
		clickYes();
		getFailedMessage();	
    }
    
    @Test(priority=3)
    public void failedTest(){
    	getFailedMessage1();
			
    }
    @Test(priority=4)
    public void testCaseSkip(){
		System.out.println("Im in Conditional Skip");
		int a=1;
		if(a>0)
		throw new SkipException("Skipping this exception");
		System.out.println("Executed Successfully");
	}
   
	@AfterTest
	public void tearDown(){
		getAndroidDriver().quit();

		try {
			as.appiumStop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}

}
