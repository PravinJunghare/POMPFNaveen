package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil elementUtil;// for page actions need it so initliase it

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);// create object for access elementutil methods
	}

	private By header = By.xpath("//*[@id=\"account-account\"]/ul/li[2]/a");
	private By accountSectionhear = By.xpath("//div[@id='content']/h2");
	private By searchfield = By.name("search");
	private By searchbutton = By.xpath("//div[@id='search']//button");
	private By logoutlink = By.linkText("Logout");

	public String getAccountPageTitle() {
		return elementUtil.doGetTitle(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountsPageHeader() {
		return elementUtil.doGettext(header);
	}

	public boolean islogoutlinkExits() {
		return elementUtil.doIsDisplyed(logoutlink);
	}

	public void logout() {
		elementUtil.doClick(logoutlink);
	}

	public List<String> getAccountSecList() {
		List<WebElement> accSecList = elementUtil.waitForElementsToBeVisible(accountSectionhear, 10);
		List<String> accSecvalueList = new ArrayList<String>();
		for (WebElement e : accSecList) {
			String text = e.getText();
			accSecvalueList.add(text);
		}
		return accSecvalueList;
	}

	public SearchResultPage dosearch(String productName) {
		System.out.println("Searching for Prodcut " + productName);
		elementUtil.dosendKey(searchfield, productName);
		elementUtil.doClick(searchbutton);
		return new SearchResultPage(driver);// returning new landing page clas object
		// here on the basis of requirement
		// creating the new page object this is the test driven develpoment
	}
}
