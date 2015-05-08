@web
Feature: animal website tests

  Scenario: tests animal avialbality
    Given I navigate to website
    When I click on adoption link
    Then I select adoption date
    And I check animal availablity
