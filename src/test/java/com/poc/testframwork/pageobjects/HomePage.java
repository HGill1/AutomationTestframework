package com.poc.testframwork.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
	
	

	@FindBy(name = "nav_adopt")
	private WebElement adoptionLink;
	
	@FindBys({ @FindBy(tagName = "a"),@FindBy(tagName = "p") })
	private List<WebElement> allanchor;
	
	public HomePage(WebDriver driver) {
		super(driver);	
	}
	
	public AdoptionPage clickOnAdoptionPage(){
		for(WebElement a: allanchor){
			System.out.println(a.getText());
		}
		System.out.println(allanchor);
		adoptionLink.click();
		return PageFactory.initElements(driver, AdoptionPage.class);
	}
	
	

}
