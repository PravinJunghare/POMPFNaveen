package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("JIRA ID 101 Design login page")
@Story("Login page story no 101")
public class LoginPageTest extends BaseTest {

	@Description("Login page test")
	@Severity(SeverityLevel.MINOR)

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTile = loginPage.getLoginPageTitle();
		System.out.println(actTile);
		Assert.assertEquals(actTile, Constants.LOGIN_PAGE_TITLE, "Login page title is not correct");// only print msg
																									// when assertion
																									// gets failed

		// insted of hardcoding we are going to create CONSTANTS in utils package
		// where all values which are not going to change are stored
	}

	@Description("Login page test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		System.out.println(actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRICTION));
	}

	@Description("Reglink test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void regiterlinkTest() {
		Assert.assertTrue(loginPage.isregisterLinkExist());
	}

	@Test(priority = 4)
	public void forgotPassLinkTest() {
		Assert.assertTrue(loginPage.isforgottenPasswordLinkExist());
	}

	@Description("Login correct test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)

	public void loginTest() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

		Assert.assertEquals(accountPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);

		// Here after login user moving to account page so we are storing that in
		// accountPage ref var
		// getAccountTitle() will return the title of accountpage so we are asserting
		// that with our
		// Constant.accountpage title
		// Test will not complete without assertion
	}

	/*
	 * @Test(priority = 5) public void registerlinkclickTest() {
	 * loginPage.doClickRegistrationLink(); }
	 * 
	 */

}
