package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	// Here for performing action on Registrtion page user should be on registration
	// page
	// so we are setting condtion @beforeClass for only Registration page
	// If we write this on BaseTest class it will also impact on Login Test
	@BeforeClass
	public void RegitrationPageSetup() {
		registrationPage = loginPage.doClickRegistrationLink();
	}
	// Here loginPage.doClickRegistrationLink() is returning RegistrtionPage object
	// so;
	// we are storing in registrtion varible

	@Test(priority = 1)
	public void registrationpageTitleTest() {
		String actTitle = registrationPage.getRegistrtionPageTitle();
		System.out.println("Registration Page Title is: " + actTitle);
		Assert.assertEquals(actTitle, Constants.REGISTRATION_PAGE_TITLE);

	}

	@Test(priority = 2)
	public void registrationPageHeaderTest() {
		String header = registrationPage.getRegistrationPageHeader();
		System.out.println("Reg page Header is " + header);
		Assert.assertEquals(header, Constants.REGISTRATION_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void doSearch() {
		registrationPage.doSearch("mackbook pro");
	}

	public String getRandomemail() {
		Random randomgenerator = new Random();
		String email = "test" + randomgenerator.nextInt(10000) + "@gmail.com";
		return email;

	}

	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}

	@Test(dataProvider = "getRegisterData", priority = 4)
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration(firstName, lastName, getRandomemail(), telephone,
				password, subscribe));
	}

}
// problem : while registrtion we are using email id to register
//user but when you run it will show alredy exit 
// for avoid this we delete email column from sheet also from method