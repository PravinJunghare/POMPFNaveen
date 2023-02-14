package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public java.util.Properties prop;
	public static String highlight;
	public OptionsManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webdriver
	 * 
	 * @param browserName
	 * @return this will return the driver
	 */

	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser Name is: " + browserName);
		highlight = prop.getProperty("highlight");
		// to get value from proprty file
		optionManager = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));

		}

		else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));

		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());

		}

		else {
			System.out.println("enter valid browserName" + browserName);
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	/**
	 * This method is used to getdriver
	 * 
	 * @return
	 * 
	 * @return this will return the thread local copy of webdriver
	 */

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the properies
	 * 
	 * @return this will return the properties reference
	 * @throws IOException
	 */

	public java.util.Properties init_prop() throws IOException {
		prop = new java.util.Properties();
		FileInputStream fip = null;
		String envName = System.getProperty("env");// qa//dev//prod
		if (envName == null) {
			System.out.println("Running on production" + envName);
			fip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");

		} else {
			System.out.println("Running onn env" + envName);
			switch (envName) {
			case "qa":
				fip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
				break;
			case "test":
				fip = new FileInputStream(".\\src\\test\\resources\\config\\test.config.properties");
				break;

			default:
				System.out.println("Please pass right env");
				break;
			}
		}
		prop.load(fip);
		return prop;
	}
	
	/*
	 * take ScreenShot
	 */
	
	public String getScreenShot() {
		File source=((TakesScreenshot )getDriver()).getScreenshotAs(OutputType.FILE);
		// here we are coverting getDriver() becase mutiple thread are runnning
		String path=System.clearProperty("user dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		// here we are giving path to store scrrenshot 
		// System.clearProperty("user dir")+"/screenshot will create folder in curren project
		//+System.currentTimeMillis()+".png"; this will give the name on base of current time
		
		File destination= new File(path);
		// move file
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	
	
	
}
