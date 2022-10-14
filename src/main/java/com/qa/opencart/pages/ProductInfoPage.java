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
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private Map<String,String> productInfoMap;
	
	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	
	
	public String getProductHeader() {
		String productHheader = eleutil.doGetText(productHeader);
		System.out.println("product header is :"+productHheader);
		return productHheader;
	}
	
	public int getProductImagesCount() {
		return eleutil.waitForElementsToBeVisible(productImages, 10).size();
	}
	
	public Map<String, String> getProductInfo() {
		productInfoMap= new LinkedHashMap<String,String>(); 
		productInfoMap.put("productName", getProductHeader());
		getProductMetaData();
		getProductPrice();
		return productInfoMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaData = eleutil.getElements(productMetaData);
		for(WebElement e : metaData) {
			String data = e.getText();
			String dataArr[] = data.split(":");
			String metaKey = dataArr[0].trim();
			String metaVal = dataArr[1].trim();
			productInfoMap.put(metaKey, metaVal);
		}
	}
		
	private void getProductPrice() {
		List<WebElement> productPrice = eleutil.getElements(productPriceData);
		String price = productPrice.get(0).getText().trim();
		String exPrice = productPrice.get(1).getText().trim();
		productInfoMap.put("productPrice", price);
		productInfoMap.put("ExTaxPrice", exPrice);
	}
		
	}
	


