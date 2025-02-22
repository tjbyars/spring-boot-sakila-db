Feature: FilmController

  Scenario: A film is read by ID
    Given a film exists with ID 42
    When a GET request is made to films for ID 42

  Scenario: An invalid film is read by ID
    Given no film exists with ID 24
    When a GET request is made to films for ID 24

  Scenario: A film is created
    Given a valid FilmInput request body
    When a POST request is made to the films collection
    Then a FilmResponse output is returned

  Scenario: A film is deleted
    Given a film exists with ID 14
    When a DELETE request is made to the films collection for ID 14
    Then no film exists with ID 14

  Scenario: Deleting a film that does not exist
    Given no film exists with ID 700
    When a DELETE request is made to the films collection for ID 700

  Scenario: Updating a film
    Given a film exists with ID 38 and has title "Title"
    When the film with ID 38 is updated to have title "NewTitle"
    Then the film with ID 38 has title "NewTitle"

  Scenario: Read all films
    Given a film exists with ID 1
    And a film exists with ID 2
    When a GET request is made to films for all films

  Scenario: Use filmController to create, update, and delete
    Given a film exists with ID 10 Controller
    When the film with ID 10 is updated to have title "NewTitle" Controller
    And A DELETE request is made to the films collections for ID 10
