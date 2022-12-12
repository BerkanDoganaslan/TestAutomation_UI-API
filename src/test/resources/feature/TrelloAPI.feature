@TrelloAPI
Feature: TrelloAPI

  Scenario: The user should be able to create, add, edit and delete cards on Trello

    Given   User creates a board on trello
    Then    User creates two cards on the created board
    Then    User updates a random number of created cards
    Then    User deletes created cards
    Then    User deletes created board