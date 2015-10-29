package com.poc.testframwork.teststepdefinations;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.poc.testframwork.pageobjects.AdoptionPage;
import com.poc.testframwork.pageobjects.AdoptionResultPage;
import com.poc.testframwork.pageobjects.HomePage;
import com.poc.testframwork.util.TestManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CommonSd extends AbstractSd {
	//WebDriver driver;
	private static HomePage onHomePage;
	private static AdoptionPage adoptionPage;
	public static AdoptionResultPage adoptionResultPage;
	
	@Before
	public void setUp() throws Exception {
		driver = getWebDriver();
		onHomePage = new HomePage(driver);
	}
	
	@After
	public void tearDown(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
		//	TestManager.getscreenshot(driver, scenario.getId());
	
	            final byte[] screenshot = ((TakesScreenshot) driver)
	                        .getScreenshotAs(OutputType.BYTES);
	            scenario.embed(screenshot, "image/png"); //stick it in the report
 
		}
	//	recording.stopRecording();
		
		driver.quit();
		
		
	}
	
	
	@Given("^I navigate to website$")
	public void i_navigate_to_website() throws Throwable {
		onHomePage = onHomePage.navigateToTestSite();
	}


	@When("^I click on \"(.*?)\" link$")
	public void i_click_on_link(String arg1) throws Throwable {
		adoptionPage = onHomePage.clickOnAdoptionPage(arg1);
	}
	
	@Then("^I select adoption date and check \"(.*?)\"$")
	public void i_select_adoption_date_and_check(String animalName) throws Throwable {
		adoptionResultPage = adoptionPage.checkAnimal(animalName);
	}
	
}
