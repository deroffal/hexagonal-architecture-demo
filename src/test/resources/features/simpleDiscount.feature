Feature: Price calculation with simple discount (x% from n same items)

  Background:
    Given a 'book' costs 10.00
    And a 'blue-ray' costs 25.00
    Given a discount of 10% from 3 'book'
    And a discount of 50% from 3 'blue-ray'

  Scenario: Price calculation with a simple discount
    Given I add 5 'book' in my basket
    When I validate my basket
    Then I should pay 45.00

  Scenario: Price calculation with a simple discount for 2 items
    Given I add 10 'book' in my basket
    And I add 10 'blue-ray' in my basket
    When I validate my basket
    Then I should pay 215.00
