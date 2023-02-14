package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNeagtiveTest extends BaseTest {

	@DataProvider
	public Object[][] loginwrongTestData() {
		return new Object[][] { { "test@gmail.com", "Test11" }, { "pravin@gmail.com", "Test122" },
				{ "  ", "Teste11111" }, { " ", " " }

		};
	}

	@Test(dataProvider = "loginwrongTestData")
	public void loginNegativeTest(String username, String password) {
		Assert.assertFalse(loginPage.dologinWrongCredential(username, password));

	}

}
