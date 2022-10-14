package com.qa.opencart.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By newsLetterYes = By.xpath("//input[@name='newsletter' and @value='1']");
	private By newsLetterNo = By.xpath("//input[@name='newsletter' and @value='0']");
	private By privacyPolicy = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	private By sucessMsg = By.cssSelector("div#content h1");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	}
	
	public void enterFirstName(String fName) {
		elementutil.doSendKeys(firstName, fName, Constants.DEFAULT_TIME_OUT);
	}
	
	public void enterLastName(String lName) {
		elementutil.doSendKeys(lastName, lName);
	}
	
	public void enterEmailId(String email) {
		elementutil.doSendKeys(emailId, email);
	}
	
	public void enterTelephone(String tele) {
		elementutil.doSendKeys(telephone, tele);
	}
	
	public void enterPassword(String pwd) {
		elementutil.doSendKeys(password, pwd);
	}
	
	public void enterConfirmPassword(String confirmPwd) {
		elementutil.doSendKeys(confirmPassword, confirmPwd);
	}
	
	public void selectNewsLetter(String newsletter) {
		if(newsletter.equalsIgnoreCase("yes")) {
			elementutil.doClick(newsLetterYes);
		}
		else {
			elementutil.doClick(newsLetterNo);
			
		}
	}
	
	public void selectPrivacyPolicy() {
		elementutil.doClick(privacyPolicy);
	}
	
	public void clickOnContinueBtn() {
		elementutil.doClick(continueButton);
	}
	
	public String getrandomEmail() {
		Random randomGenerator = new Random();
		String email = "testemail"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	public void doClickLogout() {
		elementutil.doClick(logoutLink);
	}
	
	public void doClickRegister() {
		elementutil.doClick(registerLink);
	}
	
	
	  public boolean doRegistrationAndLogout(String fname, String lname, String email, String
	  telephone, String pwd, String newsletter ) {
		  enterFirstName(fname);
		  enterLastName(lname); 
		  enterEmailId(email); 
		  enterTelephone(telephone);
		  enterPassword(pwd); 
		  enterConfirmPassword(pwd); 
		  selectNewsLetter(newsletter);
		  selectPrivacyPolicy(); 
		  clickOnContinueBtn();
		  String message = elementutil.waitForElementToBeVisible(sucessMsg, 5).getText();
		  System.out.println("Registration success message is : "+message);
		  if(message.contains(Constants.REGISTER_SUCCESS_MESAGE)) {
			  doClickLogout();
			  doClickRegister();
			  return true;
		  }
		  return false;
		  
	  }
	 
	
	public String getRegisterPageTitle() {
		return elementutil.doGetTtileWithWait(Constants.REGISTER_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
}
