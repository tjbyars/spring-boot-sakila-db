Feature: FilmController

  Scenario: A film is read by ID
    Given a film exists with ID 42
    When a GET request is made to films for ID 42
#    Then a FilmResponse is returned

  Scenario: An invalid film is read by ID
    Given no film exists with ID 24
    When a GET request is made to films for ID 24
#    Then a ResponseStatusException is thrown
#    And the status code is 404

  Scenario: A film is created
    Given a valid FilmInput request body
    When a POST request is made to the films collection
#    Then a FilmResponse is returned

#  Scenario: An invalid film is created
#    Given an invalid FilmInput request body
#    When a POST request is made to the films collection

  Scenario: A film is deleted
    Given a film exists with ID 14
    When a DELETE request is made to the films collection for ID 14
    Then no film exists with ID 14

  Scenario: Deleting a film that does not exist
    Given no film exists with ID 700
    When a DELETE request is made to the films collection for ID 700
    #  Deletion doesn't check if valid, need to fix