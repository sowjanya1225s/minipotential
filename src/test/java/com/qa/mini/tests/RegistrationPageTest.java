package com.qa.mini.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.mini.utils.Constants;
import com.qa.mini.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void RgistrationSetUp() {
		regPage = loginPage.gotoRegisterPage();
	}

	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.EXCEL_SHEET_NAME);

	}

	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName, email, telephone, password, subscribe));
	}

}
