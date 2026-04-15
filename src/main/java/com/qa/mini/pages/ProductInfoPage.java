package com.qa.mini.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.mini.utils.Constants;
import com.qa.mini.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private Map<String, String> productMap;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By metadata = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By priceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By cartButton = By.id("button-cart");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getProductHeaderName() {
		return eleutil.doGetText(productHeader).trim();
	}

	public int getProductImagecount() {
		return eleutil.waitForElementsVisible(productImages, Constants.DEFAULT_TIME_OUT).size();
	}

	public Map<String, String> getProductInfo() {
		productMap = new LinkedHashMap<String, String>();
		productMap.put("Name", getProductHeaderName());
		productMap.put("ImageCount", String.valueOf(getProductImagecount()));
		getProdMetadata();
		getProdPricedata();
		return productMap;

	}

	private void getProdMetadata() {
		List<WebElement> metadataList = eleutil.getElements(metadata);
		for (WebElement e : metadataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String Value = meta[1].trim();
			productMap.put(key, Value);
		}
	}
	
	private void getProdPricedata() {
		List<WebElement> metaPriceList = eleutil.getElements(priceData);
		String price =metaPriceList.get(0).getText().trim();
		String taxPrice =metaPriceList.get(1).getText().trim();
		productMap.put("Price", price);
		productMap.put("Taxprice", taxPrice);
	
	}
}
