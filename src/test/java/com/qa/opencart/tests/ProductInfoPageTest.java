package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {
	@BeforeClass // precondtion use should be logged In
	public void accountProductInfoSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void productHeaderTest() {
		searchResultPage = accountPage.dosearch("MacBook Pro");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}

	@Test(priority = 2)
	public void productImagesCountTest() {

		searchResultPage = accountPage.dosearch("iMac");
		productInfoPage = searchResultPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getImageCount(), Constants.IMAC_IMAGE_COUNT);// imag counts dyamic but
																							// constatnt
	}

	@Test(priority = 3)
	public void productInfoTest() {
		searchResultPage = accountPage.dosearch("MacBook Pro");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductInfoMap = productInfoPage.getProductInfo();
		actualProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v)); // no order is maintained
		// so changed to LinkedHashMap in Page
		// TreeMap in sorted order in alphabetical Order

		// Assert.assertEquals(actualProductInfoMap.get("name"), "MacBook Pro");
		// Assert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		// Assert.assertEquals(actualProductInfoMap.get("price"), "$2,000.00");

		// these are hard assertion if MacBook Pro is not found and then test wiil be
		// fail
		// but Apple and price are not changed still test will be fail so insted of hard
		// assertion
		// we need to use Soft assetion

		softAssert.assertEquals(actualProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();// compulsary to write last to report all fail/error at last

		// here we have created Softassert object in Basetest and insted of using hard
		// we used softAssert
		// If method has only one assertion then go for Hard Assertion if more than 1 Go
		// for soft assertion
	}

}
