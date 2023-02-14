package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_URL_FRICTION = "route=account/login";
	public static final int DEFAULT_TIME_OUT = 30;
	public static final String REGISTRATION_PAGE_TITLE = "Register Account11";
	public static final String REGISTRATION_PAGE_HEADER = "Register Account";
	public static final String ACCOUNT_PAGE_HEADER = "Account";
	public static final int IMAC_IMAGE_COUNT = 3;
	public static final int MACBOOOKPRO_IMAGE_COUNT = 4;
	public static final String LOGIN_PAGE_ERRORMESSAGE = " Warning: No match for E-Mail Address and/or Password."
			+ "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
	public static final String  REGISTRATION_SUCCESS_MESSAGE="Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "registration";

	public static List<String> getExpectedAccSectionList() {
		List<String> expectedaccSecList = new ArrayList<String>();
		expectedaccSecList.add("My Account");
		expectedaccSecList.add("My Orders");
		expectedaccSecList.add("My Affiliate Account");
		expectedaccSecList.add("Newsletter");
		return expectedaccSecList;
	}
}
