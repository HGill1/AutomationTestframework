package com.poc.testframwork.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {

	protected static Properties properties = null;
	protected InputStream fis = ReadConfigFile.class.getClassLoader()
			.getResourceAsStream("config.properties");

	public ReadConfigFile() throws IOException {

		properties = new Properties();
		properties.load(fis);

	}

	public static String getUrl() {

		return properties.getProperty("url");

	}

	public  String getDriverWait() {
		return properties.getProperty("driver_wait");
	}

	public  String getMaxWindow() {
		return properties.getProperty("maximize_browser");
	}
	
	public  String getBrowser(){
		
		return properties.getProperty("browser_name");
	}

	public  String getChromeDriver() {
		return properties.getProperty("chrome_server_location_ubuntu");
	}
	
	public  String getIEDriver() {
		return properties.getProperty("ie_server_location");
	}

}
