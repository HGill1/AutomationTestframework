package com.poc.testframwork.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectBrowser {

	protected static WebDriver driver;
	public static WebDriverWait driverWait= null;

	public SelectBrowser() {
		// TODO Auto-generated constructor stub
	}

	public static WebDriver getDriver() throws IOException {

		if (driver == null) {
			ReadConfigFile conf =  new ReadConfigFile();
			String browser = conf.getBrowser();

			switch (browser) {
			case "1":
				 driver = new FirefoxDriver();
				break;
				
			case "2":
				 driver = getChromeDriver();
				break;
				
			case "3":
				 driver = new HtmlUnitDriver(true);
				break;
				
			case "4":
				 driver = getIEDriver();
				break;

			default:
				break;
			}
			int driverTimeWait = StringUtils.isEmpty(conf.getDriverWait()) ? 10 : Integer.parseInt(conf.getDriverWait());
			driverWait =  new WebDriverWait(driver, driverTimeWait);
		}
		
		return driver;

	}

	private static WebDriver getChromeDriver() throws IOException {
		ReadConfigFile conf =  new ReadConfigFile();
		String chromeDriverPath =  conf.getChromeDriver();
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capabilities =  DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static WebDriver getIEDriver() throws IOException{		
		ReadConfigFile conf =  new ReadConfigFile();
		DesiredCapabilities ieCapabilities =DesiredCapabilities.internetExplorer(); 
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", conf.getIEDriver());
		WebDriver driver = new InternetExplorerDriver(ieCapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return driver;
	}

}
