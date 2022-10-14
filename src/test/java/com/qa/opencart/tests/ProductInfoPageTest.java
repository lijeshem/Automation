package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetUp() {
		accountpage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority=1)
	public void productInfoHeaderTest() {
		searchrespage = accountpage.doSearch("MacBook");
		productInfoPage = searchrespage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}
	
	@Test(priority=2)
	public void productImageCountTest() {
		searchrespage = accountpage.doSearch("iMac");
		productInfoPage = searchrespage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.IMAC_IAMGE_COUNT);
	}
	
	@Test(priority=3)
	public void productInfoTest() {
		searchrespage = accountpage.doSearch("MacBook");
		productInfoPage = searchrespage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap =  productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k +":"+v));
		softAssert.assertEquals(actProductInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfoMap.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(actProductInfoMap.get("ExTaxPrice"), "Ex Tax: $2,000.00");
		softAssert.assertAll();
	}
	
	
	

}
