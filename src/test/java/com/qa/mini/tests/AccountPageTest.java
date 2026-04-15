package com.qa.mini.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.mini.utils.Constants;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {

		accPage = loginPage.doLogin(prop.getProperty("user").trim(), prop.getProperty("pwd").trim());

	}

	@Test
	public void accountPageTitleTest() {
		Assert.assertEquals(accPage.getAccountsPageTitle(), Constants.ACCONT_PAGE_TITLE);
	}

	@Test
	public void accountsPageURLTest() {

		String actualURL = accPage.getAccountPageUrl();
		System.out.println("Account Page URL is :" + actualURL);
		Assert.assertTrue(actualURL.contains(Constants.ACCOUNT_PAGE_URL_FRACTION));

	}

	@Test
	public void accountPageHeaderTest() {
		String header = accPage.getAccountsPageHeader();
		System.out.println("Accounts page header is :" + header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);

	}

	@Test
	public void LogOutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.searchExist());
	}

	@Test
	public void accountPageSectionsTest() {
		List<String> accSectionsList = accPage.getAccountsPageSections();
		System.out.println("Accounts page actual sections :" + accSectionsList);
		Assert.assertEquals(accSectionsList, Constants.ACCOUNTS_PAGE_SECTIONS_LIST);
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "Macbook" }, { "iMac" }, { "Apple" } };
	}

	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		Assert.assertTrue(resultsPage.getProductListCount() > 0);
	}

	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "iMac", "iMac" }, { "Apple", "Apple Cinema 30\"" } };
	}

	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String product, String mainProduct) {
		resultsPage = accPage.doSearch(product);
		prodInfoPage = resultsPage.selectProduct(mainProduct);
		Assert.assertEquals(prodInfoPage.getProductHeaderName(), mainProduct);
	}
}
