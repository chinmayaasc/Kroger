package com.pack.base;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class AppiumServer {
	static Logger logger=Logger.getLogger("AppiumServer");

	 Process p;
	 // Set path of your node.exe file.
	 // Progra~1 represents Program Files folder.
	 //String nodePath = "C:/Progra~1/Appium/node.exe";
	 String nodePath = "C:/Progra~2/Appium/node.exe";
	 // Set path of your appium.js file.
	 //String appiumJSPath = "C:/Progra~1/Appium/node_modules/appium/bin/appium.js";
	 String appiumJSPath = "C:/Progra~2/Appium/node_modules/appium/bin/appium.js";
	 String cmd = nodePath + " " + appiumJSPath;
	 public static AndroidDriver driver;
	 public static AndroidDriver getDriver() {
		 PropertyConfigurator.configure("Log4j.properties");
			return driver;
		}
	 // This method Is responsible for starting appium server.
	 /*public void appiumStart() throws IOException, InterruptedException {
	  // Execute command string to start appium server.
	  p = Runtime.getRuntime().exec(cmd);
	  // Provide wait time of 10 mins to start appium server properly.
	  // If face any error(Could not start a new session...) then Increase
	  // this time to 15 or 20 mins.
	  Thread.sleep(10000);
	  if (p != null) {
	   System.out.println("Appium server Is started now.");
	  }
	 }*/
	
	 public void appiumStart() throws IOException, InterruptedException {
		  // Created object of apache CommandLine class.
		  // It will start command prompt In background.
		  CommandLine command = new CommandLine("cmd");
		  // Add different arguments In command line which requires to start appium server.
		  command.addArgument("/c");
		  command.addArgument(nodePath);
		  command.addArgument(appiumJSPath);
		  //Set Server address
		  command.addArgument("--address");
		  command.addArgument("127.0.0.1");
		  //Set Port
		  command.addArgument("--port");
		  command.addArgument("4723");
		  command.addArgument("--no-reset");
		  command.addArgument("--log");
		  //Set path to store appium server log file.
		  command.addArgument("D://appiumLogs.txt");
		  // Execute command line arguments to start appium server.
		  DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		  DefaultExecutor executor = new DefaultExecutor();
		  executor.setExitValue(1);
		  executor.execute(command, resultHandler);
		  // Wait for 15 minutes so that appium server can start properly before going for test execution.
		  // Increase this time If face any error.
		  Thread.sleep(15000);
		 }
	 
	 public  void appiumStop() throws IOException {
		  // Add different arguments In command line which requires to stop appium server.
		  CommandLine command = new CommandLine("cmd");
		  command.addArgument("/c");
		  command.addArgument("taskkill");
		  command.addArgument("/F");
		  command.addArgument("/IM");
		  command.addArgument("node.exe");
		  // Execute command line arguments to stop appium server.
		  DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		  DefaultExecutor executor = new DefaultExecutor();
		  executor.setExitValue(1);
		  executor.execute(command, resultHandler);
		 }
	 
}
