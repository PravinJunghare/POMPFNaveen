package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	//private WebDriver driver; here we are not using webdriver using elemenutil so driver is not needed
	private ElementUtil elementUtil;

	private By prodcutHeadr = By.xpath("//div[@id='content']//h1");

	private By productImages = By.xpath("//a[@class='thumbnail']//img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver)// use to return Oject
	{
		//this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	public String getProductHeader() {
		String header = elementUtil.doGettext(prodcutHeadr);
		System.out.println("Product Header is:" + header);
		return header;
	}

	public int getImageCount() {
		return elementUtil.waitForElementsToBeVisible(productImages, 10).size();

	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();

		productInfoMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = elementUtil.getElements(productMetaData);

		/*
		 * MacBook Pro Brand: Apple Product Code: Product 18 Reward Points: 800
		 * Availability: In Stock
		 */
		for (WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metakey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metakey, metaValue);

		}

	}

	private void getProductPriceData()// Encapsulation
	{

		List<WebElement> metaPriceList = elementUtil.getElements(productPriceData);

		/*
		 * $2,000.00 Ex Tax: $2,000.0
		 */

		String price = metaPriceList.get(0).getText().trim();
		String exPrice = metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exTaxprice", exPrice);
	}

}
