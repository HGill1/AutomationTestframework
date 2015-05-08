package com.poc.testframwork.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {
	
	protected WebDriver driver;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public HomePage navigateToTestSite(){
		
		driver.navigate().to("http://thetestroom.com/webapp/");
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public void closeDriver(){
		driver.close();
	}
	
	

}
