package com.pack.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;



public class AppiumTestBase {
	public static AndroidDriver ad;
	
	   public static AndroidDriver getAndroidDriver() {
        return ad;
    }

	 void initializeAndroidDriverPayTM() {
		 DesiredCapabilities ds=new DesiredCapabilities();
			ds.setCapability("deviceName", "SM-E700H");
			ds.setCapability("platformName", "Android");
			ds.setCapability("platformVersion", "4.4.2");
			//ds.setCapability("unicodeKeyboard", true);

			ds.setCapability("appPackage", "net.one97.paytm");
			ds.setCapability("appActivity", "net.one97.paytm.AJRMainActivity");
			try {
				ad=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),ds);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   
    }

	
	 void initializeAndroidDriverApiDemos() {
		 DesiredCapabilities ds=new DesiredCapabilities();
		 ds.setCapability("deviceName", "ZX1B32FFXF");
		 ds.setCapability("browserName", "Android");
		 ds.setCapability("platformVersion", "4.4.2");
		 ds.setCapability("platformName", "Android");
		 ds.setCapability("appPackage", "com.hmh.api");
		 ds.setCapability("appActivity","com.hmh.api.ApiDemos");
			try {
				ad=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),ds);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   
    }
	
}
