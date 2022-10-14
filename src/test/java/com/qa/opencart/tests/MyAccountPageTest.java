package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class MyAccountPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accountpage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}

	@Test(priority = 1)
	public void accountPageTitleTest() {
		String actTitle = accountpage.getAccountPageTitle();
		System.out.println("account page title is : " + actTitle);
		Assert.assertEquals(actTitle, Constants.MyAccount_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountpage.isLogoutLinkDisplayed());
	}

	@Test(priority = 3)
	public void accpageSecTest() {
		List<String> actaccSecList = accountpage.getAccountSecList();
		Assert.assertEquals(actaccSecList, Constants.getExpectedAccSecList());
	}
	
	@DataProvider
	public Object [] [] productData() {
		return new Object[][] {
			{"MacBook Pro"},
			{"Apple"},
			{"samsung"}
		};
	}

	@Test(priority = 4, dataProvider = "productData")
	public void searchTest(String productName) {
		searchrespage = accountpage.doSearch(productName);
		Assert.assertTrue(searchrespage.getProductsresultsCount()>0);
	}
	
	@DataProvider
	public Object [][] productSelectDara() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test (priority=6, dataProvider = "productSelectDara")
	public void selectProductTest(String productName, String mainProductName) {
		searchrespage = accountpage.doSearch(productName);
		productInfoPage = searchrespage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);
	}

}
