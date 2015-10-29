package com.poc.testframwork.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectBrowser {

	public static  String httpProxy = "10.224.23.8:3128";
	public static  String sslProxy = "";
	public static  String ftpProxy = "";
	protected static WebDriver driver;
	public static WebDriverWait driverWait= null;

	 public static DesiredCapabilities addProxyCapabilities(DesiredCapabilities capability, String httpProxy, String sslProxy,
	            String ftpProxy) {
	        Proxy proxy = new Proxy();
	        proxy.setProxyType(ProxyType.MANUAL);
	        proxy.setHttpProxy(httpProxy);
	        proxy.setSslProxy(sslProxy);
	        proxy.setFtpProxy(ftpProxy);
	 
	        capability.setCapability(CapabilityType.PROXY, proxy);
	        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        return capability;
	    }
	 
	public static WebDriver getDriver() throws IOException {

		if (driver == null || driver.toString().contains("(null)")) {
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
			if(conf.getMaxWindow().equalsIgnoreCase("Y") && !browser.equalsIgnoreCase("3"))
				getWindowSize();	
		}
		
		return driver;

	}
	
	private static WebDriver getChromeDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities capabilities =  DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static WebDriver getIEDriver() throws IOException{		
		DesiredCapabilities ieCapabilities =DesiredCapabilities.internetExplorer(); 
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
		driver = new InternetExplorerDriver(ieCapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	private static void getWindowSize() {
		driver.manage().window().maximize();
	}

}
