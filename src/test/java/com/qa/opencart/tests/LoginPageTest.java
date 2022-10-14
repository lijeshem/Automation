package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.list.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Epic : 100 - Design openCart login feature")
@Story("US 101 :  As a user they should be able to loginto the opencart app")
@Listeners(TestAllureListener.class) // add if screenshot is not capturing for the faiure tests
public class LoginPageTest extends BaseTest {

	@Description("This is login page title test") //this is from allure library and optional
	@Severity(SeverityLevel.MINOR) // this is from allure library
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("The Login page title is : " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}
	@Description("This is login page URL test") //this is from allure library and optional
	@Severity(SeverityLevel.NORMAL) // this is from allure library
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		System.out.println("The login page URL is : " + actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}

	@Description("This is forgot password exist test") //this is from allure library and optional
	@Severity(SeverityLevel.CRITICAL) // this is from allure library
	@Test(priority = 3, enabled = false)
	public void forgotPasswordLinkTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());

	}

	@Test(priority = 4)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}

	@Test(priority = 5)
	public void loginTest()  {
		accountpage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String accPageTitle = accountpage.getAccountPageTitle();
		Assert.assertEquals(accPageTitle, Constants.MyAccount_PAGE_TITLE);
	}
}
