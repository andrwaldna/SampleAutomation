#Author: test
#Date Created: 	06/23/2025
#Date Modified: 06/23/2025
#Last Modified by: test
@POSTAddPet
Feature: Add Pet to the Store
  #https://petstore.swagger.io
  Scenario Outline: TS-<SceneID>-<Description>
    Given I am an authorized User
    When I send a POST request to add the pet with details
      | name   | categoryName   | tagsName   | status   |
      | <name> | <categoryName> | <tagsName> | <status> |
    Then I receive status code <expectedStatusCode>

    Examples:
      | SceneID | Description                               | name      | categoryName      | tagsName      | status      | expectedStatusCode |
      | 001     | User send POST request to add pet details | Test name | Test categoryName | Test tagsName | Test status | 200                |


