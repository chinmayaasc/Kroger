package com.pack.page;

import static com.pack.test.AppiumTestBase.getAndroidDriver;












import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.base.Verify;
import com.pack.SeleniumHelpers.SeleniumBusiness;
import com.pack.utility.SeleniumAutomationException;

public class Recharge extends SeleniumBusiness{
	static Logger log=Logger.getLogger("Recharge");
	static String rechargeamount1;
	static String rechargeam;
	static String specialRecharge=null;
	static String homePageRecharge=null;
	private By firststentryinSpecialRecharge = By.id("net.one97.paytm:id/price_text");
	protected By phoneNumberText = By.id("net.one97.paytm:id/edit_no");
	private By operatorText = By.id("net.one97.paytm:id/text_mob_op");
	private By BrowsePlans = By.name("Browse Plans");
	private By SpecialRecharge = By.name("Special Recharge");
	private By firstEntry = By.id("net.one97.paytm:id/row_container");
	private By rechargeAmount = By.id("net.one97.paytm:id/edit_amount");
	private By proceedToRecharge = By.name("Proceed to Recharge");
	private By proceedToPay = By.xpath(("//android.widget.Button[@index='2']"));
	private By backFrameLayout = By.id(("android:id/action_bar"));
	private By cancelTransactionText = By.name(("Cancel Transaction"));
	private By clickOnYes = By.id(("android:id/button1"));
	private By failedMessage = By.name(("Payment Confirmation Awaited"));
	//private By payNowButton = By.xpath("//*[@content-desc='Pay now']");
	private By payNowButton = By.className("android.widget.Button");
	public Recharge() {
		log.info("In Recharge Page");
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	// Enter phonnumber in the textfield
	// Enter phonnumber in the textfield
	public void enterPhoneNumber(String phoneNumber) {

	    log.info("Phone Number Entered");
	    enterTextField(phoneNumberText, phoneNumber);	
		
	
	}
 
	//Get operator name 
	
	public String getOperatorName(){
		 
		System.out.println("Operator selected is " +getTextFromElement(operatorText));
		return getTextFromElement(operatorText);
	}	

	//Verifying operator name
	
	public void VerifyOperatorName(){
		if(getOperatorName().equals("Airtel")){
			System.out.println("Phone Operator displayed is: AIRTEL");
			log.info("Phone Operator displayed is: AIRTEL");
			Reporter.log("Phone Operator displayed is: AIRTEL");
					
		}
		else{
			System.out.println("Phone Operator is not: AIRTEL");
			log.info("Phone Operator is not: AIRTEL");
			Reporter.log("Phone Operator is not: AIRTEL");
		}
		
		Assert.assertEquals("Airtel",getOperatorName() );
	}
	//Click on browse plan
	
public void clickOnBrowsePlans(){
	 log.info("PlanBrowsed");
	clickButtonOrLink(BrowsePlans);
}

//Click on special recharge

public void clickOnSpecial() throws InterruptedException{
	 log.info("Clicked on Special");
	 ScrollToTab();
	 WebElement sp=getAndroidDriver().findElement(SpecialRecharge);
	 waitForElementToBeClickable(SpecialRecharge);
	 if(sp.isDisplayed()){
			clickButtonOrLink(SpecialRecharge);
			System.out.println("Item visible in first swipe");
		}
	//clickButtonOrLink(SpecialRecharge);
	 verifyRechargeAmountSpecialRecharge();
	waitForElementToBeClickable(firstEntry);
	clickButtonOrLink(firstEntry);

}

//Verifying amount selected in special recharge tab and showing in enteramount textbox are both equal

public String getRechargeAmount(){
	rechargeam=getTextFromElement(rechargeAmount);
	System.out.println("It's a recharge of total " +getTextFromElement(rechargeAmount));
	String[] splitting=rechargeamount1.split(" ");
	//specialRecharge=Recharge.rechargeamount1;
	String amount=splitting[1];
	specialRecharge=amount;
	log.info(amount);
	homePageRecharge=Recharge.rechargeam;
	log.info(specialRecharge);
	 log.info(homePageRecharge);
	if(specialRecharge.equals(homePageRecharge)){
		System.out.println("Two cards are of equal values");
		 //log.equals(specialRecharge.equals(homePageRecharge)) ;
		 log.info("Two cards are of equal values");
	}else{
		System.out.println("Two cards are not of equal values");
		 log.info("Two cards are not  of equal values");	
	}
	if(!rechargeam.isEmpty()){
		System.out.println("Recharge amount displayed is: "+rechargeam);
		log.info("Recharge amount displayed is: "+rechargeam);
		Reporter.log("Recharge amount displayed is: "+rechargeam);
	}
	else{
		System.out.println("Recharge amount is Empty");
		log.info("Recharge amount is Empty");
		Reporter.log("Recharge amount is Empty");
	}
	
	Assert.assertTrue(!rechargeam.isEmpty());
	Assert.assertEquals(specialRecharge, homePageRecharge, "Amount entered matched to selected amount in the special recharge");
	return rechargeam;
}

//Verifying ProcedToRecharge button is displayed or not ,If displayed click the button
	
public void clickOnProcedToRecharge(){
	 log.info("Clicked on ProcedToRecharge");
	
	 waitForElementToBeClickable(proceedToRecharge);
	 WebElement proceedToRechargeButton =  getAndroidDriver().findElement(proceedToRecharge);
	
	String text=proceedToRechargeButton.getAttribute("text");
	log.info("ProcedToRecharge button displayed");
	Assert.assertEquals(text, "Proceed to Recharge");
	//clickButtonOrLink(proceedToRecharge);
	proceedToRechargeButton.click();
	sleep(6000);

}

//Verifying proceedToPay button  displayed or not ,If displayed click the button

public void clickOnProcedToPay(){
	 log.info("Clicked on ProcedToPay");
	
	waitForElementToBeClickable(proceedToPay);
	WebElement proceedToPayButton =  getAndroidDriver().findElement(proceedToPay);
	
	if(proceedToPayButton.isDisplayed())
		 log.info("proceedToPay button displayed");
		proceedToPayButton.click();
	
	sleep(7000);

}

//Scrolling up to SpecialRecharge tab.

public void ScrollToTab() throws SeleniumAutomationException {
	  
	 
	  //Used for loop to scroll tabs until Tab 11 displayed.
	  for(int i=0; i<=5; i++){
	   //Set implicit wait to 2 seconds for fast horizontal scrolling.
		  getAndroidDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);   
	   if(getAndroidDriver().findElements(SpecialRecharge).size()!= 0){
	    //If Exclusive Products Is displayed then click on It.
	    System.out.println("Special Recharge");
	    getAndroidDriver().findElement(SpecialRecharge).click();
	    getAndroidDriver().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);  
	    System.out.println("Clicked here");
	    break;
	   }else{
	    //If Exclusive Products Is not displayed then scroll tabs from right to left direction by calling ScrollTabs() method.
	    ScrollTabs();
	    log.info("Scrolling");
	    System.out.println("Method called");
	   }
	  }
	  
	 }

//Click on  backbutton on orderdetails page.

public void cancelRecharge(){
	 
	waitFindElement(backFrameLayout);
	WebElement view = getAndroidDriver().findElement(backFrameLayout);
	List<WebElement> LinearLayout = view.findElements(By.className("android.widget.LinearLayout"));
	System.out.println("Items inside layout is"+LinearLayout.size());
	LinearLayout.get(0).click();
	log.info("Clicked on backbutton");
	sleep(3000);
}

//Verifying alert text 

public String getAlertTextName(){
	WebElement  cancelText = getAndroidDriver().findElement(cancelTransactionText); 
	String text=cancelText.getAttribute("text");
	Assert.assertEquals(text, "Cancel Transaction");
	System.out.println("Text in alert is " +getTextFromElement(cancelTransactionText));
	log.info("AlertText Captured " + text);
	return getTextFromElement(cancelTransactionText);
}

//Click on Yes 

public void clickYes(){
	WebElement  yesText = getAndroidDriver().findElement(clickOnYes); 
	String text=yesText.getAttribute("text");
	Assert.assertEquals(text, "Yes");
	waitForElementToBeClickable(clickOnYes);
	clickButtonOrLink(clickOnYes);
	log.info("ButtonText Captured is " + text);
	log.info("Yes button clicked on Alert");
	sleep(6000);
}

//Verifying failed message in Ordersummary page

public String getFailedMessage(){
	
	System.out.println("Text in failed message  is " +getTextFromElement(failedMessage));
	 Assert.assertEquals(getTextFromElement(failedMessage), "Payment Confirmation Awaited");
	 log.info("Failed statement verified");
	return getTextFromElement(failedMessage);
}

//Failing testcase due to assertion fail.

public String getFailedMessage1(){
	
	System.out.println("Text in failed message  is " +getTextFromElement(failedMessage));
	 Assert.assertEquals(getTextFromElement(failedMessage), "Payment Confirmation Awaited1");
	 log.info("Failed statement verified");
	return getTextFromElement(failedMessage);
}

//Geting amount from the selected option from the special recharge

public String verifyRechargeAmountSpecialRecharge(){
	waitFindElement(firststentryinSpecialRecharge);
	List<WebElement> findEleme =getAndroidDriver().findElementsById("net.one97.paytm:id/price_text");
	
	 System.out.println("Size is"+findEleme.size());
	
		 rechargeamount1	 =findEleme.get(0).getAttribute("text");
	 System.out.println(rechargeamount1);
	 log.info("rechargeamount is "+rechargeamount1);
	return rechargeamount1;
	
}

}
