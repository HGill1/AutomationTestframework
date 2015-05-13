package com.poc.testframwork.teststepdefinations;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;

import cucumber.api.java.en.Then;

public class AnimalAvailableSd extends AbstractSd {
	
	
	public static final Logger logger = Logger
			.getLogger(AnimalAvailableSd.class);
	
	
	@Then("^I check animal are available$")
	public void i_check_animals_are_available() throws Throwable {
		assertEquals("CONGRATULATIONS, ANIMAL AVAILABLE", CommonSd.adoptionResultPage.checkAnimalMessage());
	}	

}
