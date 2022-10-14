package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {
	
	
	@DataProvider
	public Object [] [] negativeLoginData() {
		return new Object[][] {
			{"test@gmail.com","12315454"},
			{"dsfhg@sdjhf.com","sjdfg%^"}
		};
	}
	
	@Test(dataProvider = "negativeLoginData")
	public void loginNegativeTest(String un, String pwd) {
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(un, pwd));
		
	}

}
