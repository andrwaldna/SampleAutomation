@AutomationPractice
Feature: Sign Up Functionality

  Scenario: User sign up with valid credential
    Given User is on the Testing Homepage
    When User clicks on Signup link
    And User is redirected to the "New User Signup!" page
    And User inputs valid Name and Email Address
    And User clicks the Signup button
    Then User should be redirected to "Enter Account Information" Page

