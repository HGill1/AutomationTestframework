package com.poc.testframwork.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.poc.testframwork.util.ReadConfigFile;
import com.poc.testframwork.util.SelectBrowser;

public class AbstractPage {
	
	protected WebDriver driver;
	
	public static final Logger logger = Logger
			.getLogger(AbstractPage.class);

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public HomePage navigateToTestSite(){
		
		driver.navigate().to(ReadConfigFile.getUrl());
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public <T> T  getPage( Class<T> classs) {
		return PageFactory.initElements(driver, classs);
	}
	
	public WebElement getElement(WebElement element) {	
		
		try{
			element = SelectBrowser.driverWait.until(ExpectedConditions.visibilityOf(element));	
		} 
		catch(Exception e){
			logger.error("Element not found", e);			
		}
		return element;
	}
	
	
	public void closeDriver(){
		driver.close();
	}
	
	

}
