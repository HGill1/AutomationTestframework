package com.poc.testframwork;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
plugin = {"pretty", "html:target/cucumber", "json:C:/Users/Harjinder Gill/.jenkins/jobs/testframework/workspace/cucumber.json"},
features = {"src/cucumber"},
monochrome = true
)
public class TestRunner {
	
	
}
