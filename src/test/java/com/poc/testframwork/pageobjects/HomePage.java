
package com.poc.testframwork.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
	
	

	@FindBy(id = "adoption_link1")
	private WebElement adoptionLink;
	
	@FindBy(id = "contact_link")
	private WebElement contactLink;
	
	@FindBys({ @FindBy(tagName = "a"),@FindBy(tagName = "p") })
	private List<WebElement> allanchor;
	
	public HomePage(WebDriver driver) {
		super(driver);	
	}
	
	@SuppressWarnings("unchecked")
	public <T> T  clickOnAdoptionPage(String link){
		for(WebElement a: allanchor){
			System.out.println(a.getText());
		}
		System.out.println(allanchor);
		
		switch (link) {
		case "contact":
			getElement(contactLink).click();
			return (T) getPage(ContactUsPage.class);
		default:
			getElement(adoptionLink).click();
			return (T) getPage(AdoptionPage.class);
		}		
	}
}
