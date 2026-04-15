package com.qa.mini.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.mini.utils.Constants;
import com.qa.mini.utils.ElementUtil;

public class RegisterationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// Private By locators

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit']");
	private By sucessMessage = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By register = By.linkText("Register");

	public RegisterationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean accountRegistration(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {

		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPwd, password);
		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doclick(subscribeYes);
		} else {
			eleUtil.doclick(subscribeNo);
		}
		eleUtil.doclick(agreeCheckBox);
		eleUtil.doclick(continueButton);
		String successMessage = eleUtil.doGetText(sucessMessage);
		System.out.println(firstName+" : "+successMessage);
		if(successMessage.contains(Constants.SUCCESS_MESSAGE)) {
			eleUtil.doclick(logoutLink);
			eleUtil.doclick(register);
			return true;
		}
		return false;

	}
}
