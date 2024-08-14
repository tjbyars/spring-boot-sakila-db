Feature: ActorController

  Scenario: An actor is read by ID
    Given an actor exists with ID 42
    When a GET request is made to actors for ID 42
    Then an ActorResponse is returned

  Scenario: An invalid actor is read by ID
    Given no actor exists with ID 24
    When a GET request is made to actors for ID 24
    Then a ResponseStatusException is thrown
    And the status code is 404

  Scenario: An actor is created
    Given a valid ActorInput request body
    When a POST request is made to the actors collection
    Then an ActorResponse output is returned

  Scenario: An invalid actor is created
    Given an invalid ActorInput request body
    When a POST request is made to the actors collection

  Scenario: An actor is deleted
    Given an actor exists with ID 14
    When a DELETE request is made to the actors collection for ID 14
    Then no actor exists with ID 14

  Scenario: Deleting an actor that does not exist
    Given no actor exists with ID 700
    When a DELETE request is made to the actors collection for ID 700

  Scenario: Read all actors
    Given an actor exists with ID 1
    And an actor exists with ID 2
    When a GET request is made to actors for all actors