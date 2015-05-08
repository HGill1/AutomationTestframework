package com.poc.testframwork.teststepdefinations;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.poc.testframwork.pageobjects.AdoptionPage;
import com.poc.testframwork.pageobjects.AdoptionResultPage;
import com.poc.testframwork.pageobjects.HomePage;
import com.poc.testframwork.util.SelectBrowser;
import com.poc.testframwork.util.TestManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AnimalAvailiblitySd {
	protected WebDriver driver;
	private HomePage onHomePage;
	private AdoptionPage adoptionPage;
	private AdoptionResultPage adoptionResultPage;

	public static final Logger logger = Logger
			.getLogger(AnimalAvailiblitySd.class);

	@Before
	public void setUp() throws Exception {
		driver = SelectBrowser.getDriver();
		onHomePage = new HomePage(driver);
	}

	@After
	public void tearDown(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			TestManager.getscreenshot(driver, this.getClass().getSimpleName());
		}
		driver.quit();
	}

	@Given("^I navigate to website$")
	public void I_navigate_to_website() throws Throwable {
		onHomePage = onHomePage.navigateToTestSite();
	}

	@When("^I click on adoption link$")
	public void I_click_on_adoption_link() throws Throwable {
		adoptionPage = onHomePage.clickOnAdoptionPage();
	}

	@Then("^I select adoption date$")
	public void I_select_adoption_date() throws Throwable {
		adoptionResultPage = adoptionPage.checkAnimal();
	}

	@Then("^I check animal availablity$")
	public void I_check_animal_availablity() throws Throwable {
		assertTrue(adoptionResultPage.checkAnimalMessage());
	}

}
