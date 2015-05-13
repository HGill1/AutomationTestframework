package com.poc.testframwork.teststepdefinations;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;

import cucumber.api.java.en.Then;

public class AnimalNotAvailibleSd extends AbstractSd {
	

	public static final Logger logger = Logger
			.getLogger(AnimalNotAvailibleSd.class);
	
	
	@Then("^I check animal not available$")
	public void i_check_animals_not_available() throws Throwable {
		assertEquals("SORRY, ANIMAL NOT AVAILABLEee", CommonSd.adoptionResultPage.checkAnimalMessage());
	}	

}
