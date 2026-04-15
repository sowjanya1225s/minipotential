package com.qa.mini.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.mini.utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPagetTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageURLTest() {
		Assert.assertTrue(loginPage.getLoginPageurl().contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	@Test(priority = 2)
	public void ForgotpwdLinkTest()
	{
		Assert.assertTrue(loginPage.isForgortPwdLinkExist());
	}
	@Test(priority = 3)
	public void logOutTest()
	{
		accPage = loginPage.doLogin(prop.getProperty("user").trim(), prop.getProperty("pwd").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	


}
