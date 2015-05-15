package com.poc.testframwork.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.poc.testframwork.util.ReadConfigFile;

public class AbstractPage {
	
	protected WebDriver driver;

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
	
	public void closeDriver(){
		driver.close();
	}
	
	

}
