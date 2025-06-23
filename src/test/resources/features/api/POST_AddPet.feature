@POSTAddPet
Feature: Add Pet to the Store
  #https://petstore.swagger.io

  Scenario: Add a new pet with valid details
    Given I am an authorized User
    When I send a POST request to add the pet with details
      | id | name   | status    |
      | 1  | doggie | available |
    Then I receive status code 200
#    And The response should contain the pet name "Rex"
