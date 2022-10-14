package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementutil = new ElementUtil(driver);
	}
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	@Step("get the login page title....")//comes from allure 
	public String getLoginPageTitle() {
		return elementutil.doGetTtileWithWait(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("get the login page URL....")//comes from allure
	public String getLoginPageUrl() {
		return elementutil.doGetUrl();
	}
	
	@Step("Checking forgot password link exist or not ....")//comes from allure
	public boolean isForgotPasswordLinkExist() {
		return elementutil.doIsDisplayed(forgotPasswordLink);
	}
	
	@Step("Checking whether Register link exist or not...")
	public boolean isRegisterLinkExist() {
		return elementutil.doIsDisplayed(registerLink);
	}
	
	@Step("Do log in with user id :  {0} and password :  {1}....")//comes from allure
	public AccountPage doLogin(String un, String pwd)  {
		System.out.println("Login with:  "+un + ":" +pwd);
		elementutil.doSendKeys(emailId, un);
		elementutil.doSendKeys(password, pwd);
		elementutil.doClick(loginButton);
		return new AccountPage(driver);
	}
	
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("Trying to log with "+un+ " "+pwd);
		elementutil.doSendKeys(emailId, un);
		elementutil.doSendKeys(password, pwd);
		elementutil.doClick(loginButton);
		
		String errorMsg = elementutil.doGetText(loginErrorMsg);
		System.out.println("The Login error message is :"+errorMsg);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MESSAGE)) {
			System.out.println("Login is not done ...");
			return false;
		}
		return true;
	}
	
	@Step("Navigating to registration page")
	public RegisterPage goToRegistrationPage() {
		elementutil.doClick(registerLink);
		return new RegisterPage(driver);
		
	}

}
