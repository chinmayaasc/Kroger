package com.pack.SeleniumHelpers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import com.pack.utility.SeleniumAutomationException;

public class SeleniumBusiness extends SeleniumSpecific {



	static Logger log=Logger.getLogger("SeleniumBusiness");


    protected void enterTextField(By elementLocator, String value) throws SeleniumAutomationException {
        try {
            log.info("-Entering text: " + value + " in element located at : " + elementLocator.toString());
            waitFindElement(elementLocator);
            WebElement inputField = waitFindElement(elementLocator);
            inputField.clear();
            inputField.click();
            inputField.sendKeys(value);
        } catch (Exception e) {
            throw new SeleniumAutomationException(e);
        }
    }

    protected void enterTextField(WebElement inputField, String value) throws SeleniumAutomationException {
        try {
            log.info("-Entering text: " + value + " in element located at : " + inputField.toString());
            inputField.clear();
            inputField.click();
            inputField.sendKeys(value);
        } catch (Exception e) {
            throw new SeleniumAutomationException(e);
        }
    }

    protected void clickButtonOrLink(By elementLocator) throws SeleniumAutomationException {
        try {
            log.info("-Clicking element located at : " + elementLocator.toString());
            waitForElementToBeVisible(elementLocator);
            WebElement button = waitFindElement(elementLocator);
            waitForElementToBeClickable(button);
            button.click();
        } catch (Exception e) {
            throw new SeleniumAutomationException(e);
        }
    }

    protected void clickButtonOrLink(WebElement webElement) throws SeleniumAutomationException {
        try {
            log.info("-Clicking element located at : " + webElement.toString());
            waitForElementToBeClickable(webElement);
            webElement.click();
        } catch (Exception e) {
            throw new SeleniumAutomationException(e);
        }
    }

       protected String getTextFromElement(By elementLocator) throws SeleniumAutomationException {
        WebElement fieldDisplayed;
        try {
            log.info("-Fetching text for the element in location: " + elementLocator.toString());
            waitForElementToBeVisible(elementLocator);
            fieldDisplayed = waitFindElement(elementLocator);
            return fieldDisplayed.getText().trim();
        } catch (Exception e) {
            throw new SeleniumAutomationException(e);
        }
    }

    protected String getTextFromElement(WebElement webElement) throws SeleniumAutomationException {
        try {
            log.info("-Fetching text for the element in location: " + webElement.toString());
            return webElement.getText().trim();
        } catch (Exception e) {
            throw new SeleniumAutomationException(e);
        }
    }

    //TODO move out of base page
   
    protected String getAttributeValueFromElement(By elementLocator, String attributeName) throws SeleniumAutomationException {
        String attributeValue;
        try {
            log.info("-Fetching value for attribute " + attributeName + " from element in location: " + elementLocator);
            waitForElementToBeVisible(elementLocator);
            attributeValue = waitFindElement(elementLocator).getAttribute(attributeName);
        } catch (Exception e) {
            throw new SeleniumAutomationException(e);
        }
        return attributeValue;
    }

    protected String getAttributeValueFromElement(WebElement webElement, String attributeName) throws SeleniumAutomationException {
        String attributeValue;
        try {
            log.info("-Fetching value for attribute " + attributeName + " from element in location: " + webElement.toString());
            waitForElementToBeVisible(webElement);
            attributeValue = webElement.getAttribute(attributeName);
        } catch (Exception e) {
            throw new SeleniumAutomationException(e);
        }
        return attributeValue;
    }

    
    protected boolean isElementDisplayed(By elementLocator) {
        try {
            log.info("-Checking to see if element is displayed: " + elementLocator);
            WebElement element = waitFindElement(elementLocator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    

}
