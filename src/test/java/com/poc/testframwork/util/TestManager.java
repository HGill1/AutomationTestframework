package com.poc.testframwork.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TestManager extends TestUtil {
	
	
	
	
	public static WebElement waitForElement(WebElement element) {	
		
		try{
			element = SelectBrowser.driverWait.until(ExpectedConditions.visibilityOf(element));						
		} catch(StaleElementReferenceException sle){
			System.out.println("The element is not attached to the DOM");
			element = null;
		}
		catch(ElementNotVisibleException env){
			System.out.println("The element is not Visible");
			element = null;
		}		
		catch(NoSuchElementException nse){
			System.out.println("There is no such element");
			element = null;
		}				
		return element;
	}
	
	
	public static void getscreenshot(WebDriver driver, String pageName) throws Exception 
    {
			if(!driver.getClass().getSimpleName().equalsIgnoreCase("HtmlUnitDriver")){
		
				File dirName = new File(".\\"+pageName);
				
				
				
				if(!dirName.exists())
					dirName.mkdir();
			
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            
				FileUtils.copyFile(scrFile, new File(dirName+"\\screenshot.png"));
				
				/*File jenkinsPath =new File("C:/Users/Harjinder Gill/.jenkins/jobs/testframework/cucumber-html-reports/animalTest.feature.html");
				
				FileWriter fileWritter = new FileWriter(jenkinsPath.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write("<div><img src="+dirName+"\\screenshot.png'/></div>");
    	        bufferWritter.close();*/
			}
    }

}
