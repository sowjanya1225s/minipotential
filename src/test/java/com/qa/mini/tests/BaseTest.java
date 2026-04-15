package com.qa.mini.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.mini.factory.DriverFactory;
import com.qa.mini.pages.AccountsPage;
import com.qa.mini.pages.LoginPage;
import com.qa.mini.pages.ProductInfoPage;
import com.qa.mini.pages.RegisterationPage;
import com.qa.mini.pages.ResultsPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	AccountsPage accPage;
	RegisterationPage regPage;
	ResultsPage resultsPage;
	ProductInfoPage prodInfoPage;
	
	SoftAssert softassert;
	//DatePickerPage page;
	
	@BeforeTest
	public void setUp() {
		df= new DriverFactory();
		prop = df.init_prop();
		driver=df.init_driver(prop);
		loginPage=new LoginPage(driver);
		softassert = new SoftAssert();
		//page = new DatePickerPage(driver);
		
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
		
	}
}
