package com.qa.opencart.tests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	LoginPage loginPage;
	Properties prop;
	RegistrationPage registrationPage;
	AccountsPage accountPage;
	SearchResultPage searchResultPage;
	ProductInfoPage productInfoPage;
	SoftAssert softAssert;

	@BeforeTest
	public void setup() throws IOException {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		// setup method where we initialize the diver but now it is in DriverFactory
		// Class so
		// We need to create object of DriverFactory class to access init_driver()
		// As setup method is returning driver so need to store in Webdriver driver;

		loginPage = new LoginPage(driver);
		// as TestBase is superclass of all PageTest classes
		// so we are going to crate Page class object here

		softAssert = new SoftAssert();

	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
