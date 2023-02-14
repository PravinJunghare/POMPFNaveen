package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
// 1st :private driver
	private WebDriver driver;// here we are not using webdriver so for page chaining need driver;
	private ElementUtil elemtutil;
	// made private because should be use within this class only
	// In any Page class need intialize private webdriver

	// 2nd: create Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;// this is most important
		elemtutil = new ElementUtil(driver);
	}

	// 3rd: By locators (private)
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By registerLink = By.linkText("Register");
	private By forgottenPasswordLink = By.linkText("Forgotten Password");
	private By loginErrorMsg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	// 4th: Page Actions

	@Step("Getting Login Page Tttle")
	public String getLoginPageTitle() {
		// return driver.getTitle();
		return elemtutil.waitforTitletobebepresent(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isforgottenPasswordLinkExist() {
		return driver.findElement(forgottenPasswordLink).isDisplayed();
	}

	public boolean isregisterLinkExist() {
		return driver.findElement(registerLink).isDisplayed();
	}

	@Step("Login with username:{0} and password{1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Login with" + un + ":" + pwd);
		// driver.findElement(emailId).sendKeys(un);
		// driver.findElement(password).sendKeys(pwd);
		// driver.findElement(loginButton).click();
		elemtutil.dosendKey(emailId, un);
		elemtutil.dosendKey(password, pwd);
		elemtutil.doClick(loginButton);
		return new AccountsPage(driver);
	}

	// Here when we are user clicks on login button or link user moving to
	// registration page
	// so returning object of AccountsPage(AccountsPage)
	// So Method Returntype of Method will be AccoutsPage
	// This concept is called page chaning
	@Step("Navigating to registration page")
	public RegistrationPage doClickRegistrationLink() {
		elemtutil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
// Here when we are  user clicks on registration link user moving to registration page
	// so returning object of Registration Page(new RegistrtionPage)
	// So Method Returntype of Method will be RegistrationPage
	// This concept is called page chaning

	public boolean dologinWrongCredential(String un, String pwd) {
		System.out.println("Try to login with " + un + " : " + pwd);
		elemtutil.dosendKey(emailId, un);
		elemtutil.dosendKey(password, pwd);
		elemtutil.doClick(loginButton);
		String errrorMsg = elemtutil.doGettext(loginErrorMsg);
		System.out.println(errrorMsg);

		if (errrorMsg.contains(Constants.LOGIN_PAGE_ERRORMESSAGE)) {
			System.out.println("Login Unsuccesful");
			return false;
		}
		return true;

	}

}