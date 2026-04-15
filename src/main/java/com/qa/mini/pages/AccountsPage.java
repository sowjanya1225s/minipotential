package com.qa.mini.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.mini.utils.Constants;
import com.qa.mini.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By header = By.cssSelector("div#logo img");
	private By sections = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// Public Page Actions

	public String getAccountsPageTitle() {
		return eleutil.doGetPageTitleIs(Constants.ACCONT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountPageUrl() {
		return eleutil.WaitForURLContains(Constants.ACCOUNT_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountsPageHeader() {
		return eleutil.doGetAttribute(header, "title");
	}

	public boolean isLogoutLinkExist() {
		return eleutil.doIsDisplayed(logoutLink);
	}

	public boolean logout() {
		if (isLogoutLinkExist()) {
			eleutil.doclick(logoutLink);
			return true;
		}
		return false;
	}

	public List<String> getAccountsPageSections() {
		List<WebElement> elementList = eleutil.waitForElementsVisible(sections, Constants.DEFAULT_TIME_OUT);
		List<String> list = new ArrayList<String>();
		for (WebElement e : elementList) {
			String text = e.getText();
			list.add(text);
		}
		return list;
	}

	public boolean searchExist() {
		return eleutil.doIsDisplayed(search);
	}

	public ResultsPage doSearch(String prodcutName) {
		if (searchExist()) {
			eleutil.doSendKeys(search, prodcutName);
			eleutil.doclick(searchIcon);
		}
		return new ResultsPage(driver);
	}
}
