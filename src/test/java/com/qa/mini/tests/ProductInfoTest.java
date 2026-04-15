package com.qa.mini.tests;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductInfoTest extends BaseTest {

	private WebDriver driver;

	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("user").trim(), prop.getProperty("pwd").trim());
	}

	@DataProvider
	public Object[][] HeaderTestData() {
		return new Object[][] {
			{"Macbook ", "MacBook Pro" },{"Macbook", "MacBook Air"}
		};
	}
	@Test(dataProvider = "HeaderTestData" )
	public void productHeaderTest(String product , String mainProduct) {
		resultsPage = accPage.doSearch(product);
		prodInfoPage = resultsPage.selectProduct(mainProduct);
		Assert.assertEquals(prodInfoPage.getProductHeaderName(), mainProduct);
	}

	@DataProvider
	public Object[][] prodData() {
		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "Macbook", "MacBook Air", 4 },
				{ "iMac", "iMac", 3 } };
	}

	@Test(dataProvider = "prodData")
	public void productImageTest(String product, String mainProduct, int count) {
		resultsPage = accPage.doSearch(product);
		prodInfoPage = resultsPage.selectProduct(mainProduct);
		Assert.assertEquals(prodInfoPage.getProductImagecount(), count);
	}
	
	@Test
	public void productInformationTest() {
		resultsPage = accPage.doSearch("macbook");
		prodInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String,String> actualInfoMap = prodInfoPage.getProductInfo();
		actualInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		softassert.assertEquals(actualInfoMap.get("Brand"), "Apple");
		softassert.assertEquals(actualInfoMap.get("Name"), "MacBook Pro");
		softassert.assertAll();
	}
}
