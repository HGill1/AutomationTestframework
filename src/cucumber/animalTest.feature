Feature: animal website tests

  Background:
    Given I navigate to website
    When I click on adoption link
    

  Scenario: tests animal not available
  	Then I select adoption date and check "Lion"
    And I check animal not available

  Scenario: test animal available
    Then I select adoption date and check "Turtle"
    And I check animal are available
