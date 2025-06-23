@GoogleSearch
Feature: Google Search Functionality

  Scenario: User searches for a term on Google
    Given User is on the Google homepage
    When User searches for "Selenium WebDriver"
    Then search results for "WebDriver" should be displayed
