#Author: test
#Date Created: 	06/23/2025
#Date Modified: 06/23/2025
#Last Modified by: test
@POSTOrderConfirmation
Feature: Order Confirmation
  #https://api.example.com/v1/orders/confirm
  Scenario Outline: TS-<SceneID>-<Description>
    Given I am an authorized user
    When I send a POST request for order confirmation
      | orderId   | userId   | email   | name   | orderDate   | totalAmount   | currency   | productId   | productName   | quantity   | price   | line1   | line2   | city   | state   | postalCode   | country   | sendEmail   |
      | <orderId> | <userId> | <email> | <name> | <orderDate> | <totalAmount> | <currency> | <productId> | <productName> | <quantity> | <price> | <line1> | <line2> | <city> | <state> | <postalCode> | <country> | <sendEmail> |
    Then I receive status code <expectedStatusCode>

    Examples:
      | SceneID | Description                               | orderId     | userId      | email      | name      | orderDate            | totalAmount | currency | productId             | productName                          | quantity | price | line1      | line2      | city      | state      | postalCode | country      | sendEmail | expectedStatusCode |
      | 001     | User send POST request to add pet details | orderId1234 | test userId | test email | test name | 2025-06-26T08:00:00Z | 150         | USD      | productId1,productId2 | test productName1, test productName2 | 1,2      | 50,25 | test line1 | test line2 | test city | test state | 1234       | test country | False     | 201                |
