package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	// 1 private driver
	private WebDriver driver;
	//here we are not using webdriver returning new driver so kkep it
	private ElementUtil elementUtil;

	// 2 Private BY locator
	private By header = By.xpath("//div[@id='content']/h1");
	private By searchFiled = By.name("search");
	private By searchButton = By.xpath("//button[@class=\"btn btn-light btn-lg\"]");
	private By loginLink = By.linkText("Login");

	// Registration Loctors
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By PasswordConfirm = By.id("input-confirm");
	private By susbscribeYes = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input");
	private By subscribeNo = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@value='Continue']");
	private By successMesssage = By.xpath("//div[@id='common-success']//h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	// 3 Constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	// 4 Page Actions :Public

	public String getRegistrtionPageTitle() {

		return elementUtil.doGetTitle(Constants.REGISTRATION_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		// return
		// elementUtil.waitforTitletobebepresent(Constants.ACCOUNTS_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
	}

	public String getRegistrationPageHeader() {
		return elementUtil.doGettext(header);
	}

	public RegistrationPage doSearch(String productName) {
		System.out.println("ProductName is" + productName);
		elementUtil.dosendKey(searchFiled, productName);
		elementUtil.doClick(searchButton);
		return new RegistrationPage(driver);

	}

	public boolean accountRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {

		elementUtil.dosendKey(this.firstName, firstName);

		elementUtil.dosendKey(this.lastName, lastName);
		elementUtil.dosendKey(this.email, email);
		elementUtil.dosendKey(this.telephone, telephone);
		elementUtil.dosendKey(this.password, password);
		elementUtil.dosendKey(this.PasswordConfirm, password);

		if (subscribe.equals("yes")) {
			elementUtil.doClick(susbscribeYes);
		} else {
			elementUtil.doClick(subscribeNo);
		}
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continueButton);
		String message = elementUtil.waitforelementTobevisible(successMesssage, 30).getText();

		if (message.contains(Constants.REGISTRATION_SUCCESS_MESSAGE)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;

	}

}
