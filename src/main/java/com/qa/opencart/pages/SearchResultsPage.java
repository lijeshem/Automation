package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By productResults = By.cssSelector("div.caption a");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public int getProductsresultsCount() {
		int resCount =  eleutil.waitForElementsToBeVisible(productResults, 10, 2000).size();
		System.out.println("The search product count :"+resCount);
		return resCount;
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("The product to be selected as : "+mainProductName);
		List<WebElement> prodList = eleutil.waitForElementsToBeVisible(productResults, 10, 2000);
		for(WebElement e : prodList) {
			String prodName = e.getText();
			if(prodName.equalsIgnoreCase(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	public void getSearchResultPageTitle() {
	//	eleutil.get
	}
	
	
	

}
