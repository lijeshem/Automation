package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.utils.JavaScriptUtil;

public class BaseTest {
	
	DriverFactory df;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	RegisterPage registerPage;
	AccountPage accountpage;
	SearchResultsPage searchrespage;
	ProductInfoPage  productInfoPage;
	SoftAssert softAssert;
	
	@BeforeTest
	public void setup() {
	df= new DriverFactory();
	prop = df.init_prop();
	driver = df.init_driver(prop);
	loginPage = new LoginPage(driver);
	softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
