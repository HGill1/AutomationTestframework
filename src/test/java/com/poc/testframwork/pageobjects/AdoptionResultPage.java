package com.poc.testframwork.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdoptionResultPage extends AbstractPage {

	public AdoptionResultPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(tagName = "p")
	private WebElement message;
	
	public Boolean checkAnimalMessage(){
		System.out.println("message.getText()"+ message.getText());
		return message.getText().contains("SORRY, ANIMALssss NOT AVAILABLE");
		
	}

}
