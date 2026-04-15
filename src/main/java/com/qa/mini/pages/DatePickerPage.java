package com.qa.mini.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.mini.utils.ElementUtil;

public class DatePickerPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public DatePickerPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// By locators
	//
	private By widegtsLink = By.xpath("//h5[text()='Widgets']");
	private By datePicker = By.xpath("//span[text()='Date Picker']");
	private By dateAndTime = By.id("dateAndTimePickerInput");
	private By today = By.xpath("//div[@class='react-datepicker__month']//div[@role='gridcell' and text()='25']");
	private By timeSlot = By.cssSelector("div.react-datepicker__time-box li");
	private By loc= By.id("dateAndTimePickerInput");

	public void clickonWidgets() {
		WebElement element = driver.findElement(widegtsLink);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(widegtsLink));

	}

	public void selectDate() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(datePicker));
		eleUtil.WaitForElementToBeClickable(5, dateAndTime).click();
		eleUtil.doclick(today);
	}

	public void selectTime(String time) {
		List<WebElement> list = driver.findElements(timeSlot);
		for (WebElement e : list) {
			if (e.getText().equals(time)) {
				e.click();
				break;
			}
		}
	}
	public String getSelectedDate() {
		String text = eleUtil.doGetAttribute(loc, "value");
		System.out.println(text);
		return text;
	}

}
