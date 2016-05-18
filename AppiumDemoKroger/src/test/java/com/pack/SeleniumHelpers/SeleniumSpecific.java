package com.pack.SeleniumHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.pack.test.AppiumTestBase.getAndroidDriver;

public class SeleniumSpecific {
	static Dimension size;
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {
        }
    }

    
    public static void moveToElement(WebElement whereToGo){
        Actions act = new Actions(getAndroidDriver());
        act.moveToElement(whereToGo).perform();
    }

      
    public static WebDriverWait getWebDriverWait(int timeOut) {
        int timeOutInSeconds = timeOut / 1000;
        int sleepInMillis = 200;
        return new WebDriverWait(getAndroidDriver(), timeOutInSeconds, sleepInMillis);
    }

    //TODO: Review use and document
    public static void windowScrollUpByPixels(int pixels){
        //scroll up N pixels to prevent the scroll-to-top-arrow and invitation prompt receiving the click
        WebDriver driver = getAndroidDriver();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0,-" + pixels + ");");
    }

    //TODO: Review use and document
    public static void scrollOneElementToInView(WebElement element){
        ((JavascriptExecutor) getAndroidDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }
   
    //TODO: Review use and document
    public static void windowScrollDownByPixels(int pixels){
        //scroll down 350 pixels to prevent the scroll-to-top-arrow and invitation prompt receiving the click
        WebDriver driver = getAndroidDriver();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, " + pixels + ");");
    }
    

    public static void waitForElementToBeVisible(By locator) {
        //getWebDriverWait(TimeoutMSForConditionToExist()).until(visibilityOfElementLocated(locator));
    
    	getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(locator));

}
    

    public static WebElement waitFindElement(By locator) {
        return getWebDriverWait(20).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementToBeClickable(By locator) {
        getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(locator)); 
    }
    
    public static void waitForElementToBeClickable(final WebElement element) {
        getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(element));
    }


    public static void waitForElementToBeVisible(final WebElement element) {
        getWebDriverWait(20).until(ExpectedConditions.visibilityOf(element));
    }

    public static void ScrollTabs() { 
		//Get the size of screen. 
		size = getAndroidDriver().manage().window().getSize(); 
		//Find swipe start and end point from screen's with and height. 
		//Find startx point which is at right side of screen. 
		int startx = (int) (size.width * 0.70); 
		//Find endx point which is at left side of screen. 
		int endx = (int) (size.width * 0.30); 
		//Set Y Coordinates of screen where tabs display. 
		int YCoordinates = 150; 
		//Swipe tabs from Right to Left. 
		getAndroidDriver().swipe(startx, YCoordinates, endx, YCoordinates, 3000);
		
		
		
		
}
    
    
}
