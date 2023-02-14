package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil

{

	WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	/*
	 * This method is used to create element on the basis of By locator
	 * 
	 * @param Loctor
	 * 
	 */

	public WebElement getElement(By locator) {

		WebElement element = driver.findElement(locator);
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;

	}
	/*
	 * This method is used to create element on the basis of By locator
	 * 
	 * @param Loctor
	 * 
	 */

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	/*
	 * This method is used to create element on the basis of By locator
	 * 
	 * @param Loctor
	 * 
	 */
	public void doClick(By locator) {
		try {
			getElement(locator).click();

		} catch (Exception e) {
			System.out.println("Some Exception while clicking webelement");
			System.out.println(e.getMessage());
		}
	}

	/*
	 * This method is used to display element on the basis of By locator
	 * 
	 * @param Loctor
	 * 
	 */
	public boolean doIsDisplyed(By locator) {
		try {
			getElement(locator).isDisplayed();

		} catch (Exception e) {
			System.out.println("Some Exception while clicking webelement");
			System.out.println(e.getMessage());
		}
		return false;
	}

	/*
	 * This method is used to gettext from element on the basis of By locator
	 * 
	 * @param Loctor
	 * 
	 */
	public String doGettext(By locator) {
		return getElement(locator).getText();
	}

	/*
	 * This method is used to send value to element on the basis of By locator also
	 * clear existing value
	 * 
	 * @param Loctor
	 * 
	 */

	public void dosendKey(By locator, String value) {
		try {
			doClear(locator);
			getElement(locator).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Some Exception sending value to webelement");
			System.out.println(e.getMessage());
		}

	}

	/*
	 * This method is used to clear the value of element on the basis of By locator
	 * 
	 * @param Loctor
	 * 
	 */

	public void doClear(By locator) {
		try {
			getElement(locator).clear();
			;
		} catch (Exception e) {
			System.out.println("Some Exception while clearing the webelement");
			System.out.println(e.getMessage());
		}

	}

	// Wait Methods;Utilites
	public void visibilityofAllElements(List<WebElement> elements, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));

	}

	public WebElement waitforelementTobepresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator);

	}

	public WebElement waitforelementTobeclickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator);

	}

	public WebElement waitforelementTobevisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = getElement(locator);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;

	}

	public String waitforUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);

		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();

	}

	public Boolean waitforAlertToBePresent(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);

		wait.until(ExpectedConditions.alertIsPresent());
		return true;

	}

	public void clickwhenReady(By locator, int timeout)

	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();

	}

	public String waitforTitletobebepresent(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();

	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	/*
	 * This method is used to gettext from element on the basis of By locator
	 * 
	 * @param Loctor
	 * 
	 */

	public String doGetTitle(String title, int timeOut) {
		if (waitForTitleToBe(title, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}

	public boolean waitForTitleToBe(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(title));
	}

}
