@beymen
Feature: Default

  Scenario: The user should be able to shop by entering the name of product they want in the search bar.

    Given The user should be able to login the beymen page
    When  The user should be able to write the product name
    Then  The user should be able to delete the product name from search bar
    Then  The user should be able to write the other product name
    Then  The user should be able to search for the product name entered
    Then  The user should be able to choice a product
    Then  User saves selected product and price information
    Then  The user should be able to add the selected product to the basket
    Then  The user should be able to verify the price information of the product in the basket
    Then  The user should be able to increase the number of products
    Then  The user should be able to delete items in basket




