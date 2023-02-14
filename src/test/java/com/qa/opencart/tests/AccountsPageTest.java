package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

public class AccountsPageTest extends BaseTest {

	// Here for performing action on AccountsPage user should be on AccountsPag
	// so we are setting condtion @beforeClass for only AccountsPag
	// If we write this on BaseTest class it will also impact on Login Test
	@BeforeClass
	public void accountPageSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	// Here loginPage.doClickLogink() is returning AccountPage object so;
	// we are storing in accountPage variable

	@Test(priority = 1)
	public void verifyAccountPageTitleTest() {
		String actTitle = accountPage.getAccountPageTitle();
		System.out.println("Account page Title is: " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE, Errors.ACCOUNT_PAGE_ERROR_MESG);
	}

	@Test(priority = 2)
	public void accountPagHeaderTest() {
		String actaccountPageheader = accountPage.getAccountsPageHeader();
		System.out.println("Accountpage Header is: " + actaccountPageheader);
		Assert.assertEquals(actaccountPageheader, Constants.ACCOUNT_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void accPageSectionTest() {
		List<String> actualAccSectiionList = accountPage.getAccountSecList();
		Assert.assertEquals(actualAccSectiionList, Constants.getExpectedAccSectionList());

		// here it will compare two list by its own no need of comparison logic
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "Macbook" }, { "Apple" }, { "Samsung" }

		}; // here @DatProvider will return 2D objectArray
	}

	@Test(priority = 4, dataProvider = "productData")
	public void searchProductTest(String producName) {
		searchResultPage = accountPage.dosearch(producName);
		Assert.assertTrue(searchResultPage.getProductResultCount() > 0);
		// test data this should not be hardcoded
		// here after serch user is moving to SerchResult Page
		// (ie getting SearchResultpageobject so we can store that in searchResultpage
		// ref varible;
	}

	// Here dataprovider='method name' is sending data from productData()
	// but here we need any holding parameter so we created productName to supply
	// data to doserch()
	// here do search wil excecute 3 times

	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "iMac", "iMac" },
				{ "Samsung", "Samsung SyncMaster 941BW" }, { "apple", "Apple Cinema 30\"" }

		}; // here @DatProvider will return 2D objectArray
	}

	@Test(priority = 5, dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		searchResultPage = accountPage.dosearch(productName);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);

	}

}
