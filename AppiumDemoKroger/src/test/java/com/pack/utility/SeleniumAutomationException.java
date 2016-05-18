package com.pack.utility;

import javax.naming.directory.NoSuchAttributeException;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class SeleniumAutomationException extends RuntimeException{
	
	static Logger log=Logger.getLogger("SeleniumAutomationException");
	public SeleniumAutomationException(Exception e) {

        super(e.getMessage(), e);

        if (e instanceof ElementNotFoundException) {

            log.error("The element was not found at the given location");

        } else if (e instanceof NoSuchElementException) {

            log.error("An element could not be located on the page using the given search parameters.");

        } else if (e instanceof NullPointerException) {

            log.error("Null value was returned while trying to find a value");

        } else if (e instanceof TimeoutException) {

            log.error("The system timed out while trying to find an element");

        } else if (e instanceof StaleElementReferenceException) {

            log.error("The DOM would have changed during the load time");

        } else if (e instanceof ElementNotVisibleException) {

            log.error("The element you are trying to access is not visible in your page");

        } else if (e instanceof NoSuchAttributeException) {

            log.error("The attribute which you are looking for is not present");

        } else if (e instanceof WebDriverException) {

            log.error("It might be an issue with your web driver");

        } else {
            if (e.getCause() != null)
                log.error(e.getCause().toString());
            else
                log.error(e.getMessage());
        }
    }
}
