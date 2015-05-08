package com.poc.testframwork.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdoptionPage extends AbstractPage{
	
	@FindBy(id = "start_select")
	private WebElement adoptionStartDate;
	
	@FindBy(id = "check_btn_01")
	private WebElement  lionAvailablityCheckBtn;
	
	public AdoptionPage(WebDriver driver) {
		super(driver);
	}
	
	public AdoptionResultPage checkAnimal(){		
		
		Select dropdown = new Select(adoptionStartDate);
		dropdown.selectByVisibleText("Today");
		lionAvailablityCheckBtn.click();
		return PageFactory.initElements(driver, AdoptionResultPage.class);
		
	}
	
}
