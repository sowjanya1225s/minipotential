package com.qa.mini.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.mini.utils.Constants;
import com.qa.mini.utils.ElementUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By searchHeader = By.cssSelector("div#content h1");
	private By productResults = By.cssSelector("div.caption a");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public int getProductListCount() {
		return eleutil.waitForElementsVisible(productResults, Constants.DEFAULT_TIME_OUT).size();
	}

	public ProductInfoPage selectProduct(String mainProduct) {
		System.out.println("Main product name : " + mainProduct);
		List<WebElement> searchList = eleutil.waitForElementsVisible(productResults, 10);
		for (WebElement e : searchList) {
			String text = e.getText();
			if (text.equals(mainProduct)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
