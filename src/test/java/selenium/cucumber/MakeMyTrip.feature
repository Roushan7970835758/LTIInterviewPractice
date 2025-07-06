@tag2
Feature: Book a ticket at the lowest price
  I want to find and book a ticket with the lowest fare in the second week of the month after next
  @Test2
  Scenario: Search for the lowest fare in the second week of the target month
    Given I launch the "MakeMyTrip" website
    And I close the login popup if it appears
    Then wait for 2 seconds
    When I click on the departure date calendar
    And I navigate to the month after next
    Then wait for 2 seconds
    And I select the second week of that month
    Then I capture and print the lowest price and its corresponding date
    Then wait for 2 seconds
    And I click on the search button to proceed
    #And Close the Browser
    