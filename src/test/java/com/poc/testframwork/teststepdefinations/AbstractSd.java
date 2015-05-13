package com.poc.testframwork.teststepdefinations;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.poc.testframwork.util.SelectBrowser;

public class AbstractSd {
	
	protected static WebDriver driver;
	
		
	public WebDriver getWebDriver() throws IOException{		
		   return SelectBrowser.getDriver();
	}

}
