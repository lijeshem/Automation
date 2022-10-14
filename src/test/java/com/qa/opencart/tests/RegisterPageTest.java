package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetUp() {
		registerPage = loginPage.goToRegistrationPage();
	}

	@Test
	public void registerPageTitleTest() {
		String title = registerPage.getRegisterPageTitle();
		System.out.println("Regitration page title is : " + title);
		Assert.assertEquals(title, Constants.REGISTER_PAGE_TITLE);
	}

	@DataProvider
	public Object[][] getRegistrationData() {
		return ExcelUtil.getTestData(Constants.REGISTRATION_DATA_SHEET_NAME);
	}

	@Test(dataProvider = "getRegistrationData")
	public void registrationTest(String fname, String lname, String tele, String pass, String subscription) {
		Assert.assertTrue(registerPage.doRegistrationAndLogout(fname, lname, registerPage.getrandomEmail(), tele, pass,
				subscription));
	}

}
