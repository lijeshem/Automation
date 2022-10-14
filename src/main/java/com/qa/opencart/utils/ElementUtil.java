package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsutil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsutil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsutil.flash(element);
		}
		return element;
	}

	public WebElement getElement(By locator, int timeout) {
		return doPresenceOfElementLocated(locator, timeout);
	}

	public void doClear(By locator) {
		getElement(locator).clear();
	}
	
	public void doSendKeys(By locator, String value) {
		doClear(locator);
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(By locator, String value, int timeout) {
		doClear(locator);
		doPresenceOfElementLocated(locator, timeout).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementCount(By locator) {
		return getElements(locator).size();
	}
	
	public String doGetTitle() {
		return driver.getTitle();
	}
	
	public String doGetUrl() {
		return driver.getCurrentUrl();
	}

	public List<String> getElementTextList(By locator) {
		List<WebElement> elelist = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : elelist) {
			String eleText = e.getText();
			if (!eleText.isEmpty()) {
				eleTextList.add(eleText);
			}
		}
		return eleTextList;
	}

	public List<String> getAttributeList(By locator, String attribute) {
		List<WebElement> elelist = getElements(locator);
		List<String> attrList = new ArrayList<String>();
		for (WebElement e : elelist) {
			String attrValue = e.getAttribute(attribute);
			attrList.add(attrValue);
		}
		return attrList;
	}

	public String getAttributeValue(By locator, String attribute) {
		String attrValue = getElement(locator).getAttribute(attribute);
		System.out.println(attrValue);
		return attrValue;
	}

	public boolean isElementExist(By locator) {
		int elementCount = getElements(locator).size();
		System.out.println("Total elements found : " + elementCount);
		if (elementCount >= 1) {
			System.out.println("Element is found : " + locator);
			return true;
		} else {
			System.out.println("Element is NOT found : " + locator);
			return false;
		}
	}

	/* ############################# Wait utils ############################# */

	public WebElement doPresenceOfElementLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement doPresenceOfElementLocated(By locator, int timeout, long intervaltime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervaltime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementToBeVisible(By locator, int timeout, long interval) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(interval));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeout, long interval) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(interval));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<String> getElementsTextListWithWait(By locator, int timeout) {
		List<WebElement> elelist = waitForElementsToBeVisible(locator, timeout);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : elelist) {
			eleTextList.add(e.getText());
		}
		return eleTextList;
	}

	/*
	 * ####################### Wait utils for non WebElements
	 * ##########################
	 */

	public boolean waitForUrlContain(String urlFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}

	public boolean waitForUrlToBE(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlToBe(url));
	}
	

	public boolean waitForTitleContains(String titleFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.titleContains(titleFraction));
	}

	public String doGetTtileWithWait(String titleFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		return null;
	}

	public boolean waitForTitleToBe(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.titleIs(title));
	}

	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertTextWithWait(int timeout) {
		return waitForAlert(timeout).getText();
	}

	public void doAlertAccept(int timeout) {
		waitForAlert(timeout).accept();
	}

	public void doAlertDismiss(int timeout) {
		waitForAlert(timeout).dismiss();
	}

	public void enterAlertText(String text, int timeout) {
		waitForAlert(timeout).sendKeys(text);
	}

	public void clickElementWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public WebElement waitForElementPresentUsingFluentWait(By locator, int timeout, int pollintTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollintTime))
				.ignoring(NoSuchElementException.class)
				.withMessage("Element is not found...");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementPresentUsingWebDriverWait(By locator, int timeout, int pollintTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait
		.pollingEvery(Duration.ofSeconds(pollintTime))
		.ignoring(NoSuchElementException.class)
		.withMessage("Element is not found...");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

}
