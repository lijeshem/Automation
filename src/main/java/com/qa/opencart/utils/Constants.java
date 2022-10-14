package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String REGISTER_PAGE_TITLE = "Register Account";
	public static final String MyAccount_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login1";
	public static final int DEFAULT_TIME_OUT = 5;
	public static final int IMAC_IAMGE_COUNT = 3;
	public static final CharSequence LOGIN_ERROR_MESSAGE = "No match for E-Mail Address and/or Password";
	public static final String REGISTRATION_DATA_SHEET_NAME = "registrationdata";
	public static final String REGISTER_SUCCESS_MESAGE = "Your Account Has Been Created!"; 
	
	public static List<String> getExpectedAccSecList() {
		List<String> expsecList = new ArrayList<String>();
		expsecList.add("My Account");
		expsecList.add("My Orders");
		expsecList.add("My Affiliate Account");
		expsecList.add("Newsletter");
		return expsecList;
		
	}

}
