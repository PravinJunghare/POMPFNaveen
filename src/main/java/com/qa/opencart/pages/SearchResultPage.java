package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By productResults = By.cssSelector("div.caption a");

	public SearchResultPage(WebDriver driver)// use to return Oject
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	public int getProductResultCount() {
		int resultCount = elementUtil.waitForElementsToBeVisible(productResults, 30).size();
		System.out.println("The Searched Product Count is: " + resultCount);
		return resultCount;
	}

	public ProductInfoPage selectProduct(String mainproductName) {
		System.out.println("ProdcuName is" + mainproductName);
		List<WebElement> searchList = elementUtil.waitForElementsToBeVisible(productResults, 10);
		for (WebElement e : searchList) {
			String text = e.getText();
			if (text.equalsIgnoreCase(mainproductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);

	}

}
