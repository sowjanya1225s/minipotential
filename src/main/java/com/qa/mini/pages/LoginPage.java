package com.qa.mini.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.mini.utils.Constants;
import com.qa.mini.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	// 1. Private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By register = By.linkText("Register");

	// 2.constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	// 3. public page Actions

	public String getLoginPageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getLoginPageurl() {
		return driver.getCurrentUrl();
	}

	public boolean isForgortPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}

	public AccountsPage doLogin(String usr, String pwd) {
		eleUtil.doSendKeys(emailId, usr);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doclick(loginButton);
		return new AccountsPage(driver);
	}

	public RegisterationPage gotoRegisterPage() {
		eleUtil.doclick(register);
		return new RegisterationPage(driver);
	}

}
