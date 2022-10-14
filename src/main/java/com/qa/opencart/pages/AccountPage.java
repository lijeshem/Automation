package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By header = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By searchBox = By.xpath("//input[@name='search']");
	private By searchBtn= By.cssSelector("div#search button");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public String getAccountPageTitle() {
		return eleutil.doGetTtileWithWait(Constants.MyAccount_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public void clickOnLogoutLink() {
		if(isLogoutLinkDisplayed()) {
			eleutil.doClick(logoutLink);
		}
		else {
			System.out.println("Logout link is not present on the page");
		}
	} 
	
	public boolean isLogoutLinkDisplayed() {
		return eleutil.doIsDisplayed(logoutLink);
	}
	
	public List<String> getAccountSecList() {
		List<WebElement> accSecList = eleutil.waitForElementsToBeVisible(header, 10);
		List<String> accSecListName = new ArrayList<String>();
		for(WebElement ele : accSecList) {
			accSecListName.add(ele.getText());
		}
		return accSecListName;
	}
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching the product : "+productName);
		eleutil.doSendKeys(searchBox, productName);
		eleutil.doClick(searchBtn);
		return new SearchResultsPage(driver);
	}
	

}
