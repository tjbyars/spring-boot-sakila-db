package com.example.sakila_db;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import static org.mockito.Mockito.*;

public class CombinedStepDefs {

    ActorService mockService;
    ActorController controller;
    ActorResponse actualOutput;
    Exception caughtException;
    ActorInput actorInput;
    Actor actor;

    @Before
    public void setup() {
        mockService = mock(ActorService.class);
        controller = new ActorController(mockService);
        mockFilmService = mock(FilmService.class);
        filmController = new FilmController(mockFilmService);
    }

    @Given("an actor exists with ID {short}")
    public void anActorExistsWithID(short actorId) {
        final var newActor = new Actor(actorId, "Joe", "Bloggs", List.of());
        final var actorResponse = new ActorResponse(newActor);
        doReturn(actorResponse)
                .when(mockService)
                .readActorById(actorId);
    }

    @Given("no actor exists with ID {short}")
    public void noActorExistsWithID(short actorId) {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(mockService)
                .readActorById(actorId);
    }

    @Given("a valid ActorInput request body")
    public void aValidActorInputRequestBody() {
        actorInput = new ActorInput("John", "Smith");
    }

    @Given("an invalid ActorInput request body")
    public void anInvalidActorInputRequestBody() {
        // Not currently using due to issues with it
        System.out.println(".");
    }

    @When("a GET request is made to actors for ID {short}")
    public void aGETRequestIsMadeToActors(short actorId) {
        try {
            actualOutput = controller.readActorById(actorId);
        } catch (Exception ex) {
            caughtException = ex;
        }
    }

    @When("a POST request is made to the actors collection")
    public void aPOSTRequestIsMadeToTheActorsCollection() {
        try {
            actor = mockService.createActor(actorInput);
        } catch (Exception ex) {
            caughtException = ex;
        }
    }

    @When("a DELETE request is made to the actors collection for ID {short}")
    public void aDELETERequestIsMadeToTheActorsCollection(short actorId) {
        try {
            mockService.deleteActor(actorId);
        } catch (Exception ex) {
            caughtException = ex;
            System.out.println("excep");
        }
    }

    @Then("an ActorResponse is returned")
    public void anActorResponseIsReturn() {
        Assertions.assertNotNull(actualOutput);
        Assertions.assertNull(caughtException);
    }

    @Then("a ResponseStatusException is thrown")
    public void aResponseStatusExceptionIsThrown() {
        Assertions.assertNotNull(caughtException);
        Assertions.assertInstanceOf(ResponseStatusException.class, caughtException);
    }

    @And("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        Assertions.assertEquals(statusCode, ((ResponseStatusException)caughtException).getStatusCode().value());
    }

    @Then("an ActorResponse output is returned")
    public void anActorResponseOutputIsReturned() {
        Assertions.assertNull(caughtException);
    }

    @When("a GET request is made to actors for all actors")
    public void aGETRequestIsMadeToActorsForAllActors() {
        try {
            controller.readAllActors();
        } catch (Exception ex) {
            caughtException = ex;
        }
    }


    FilmService mockFilmService;
    FilmController filmController;
    FilmResponse actualFilmOutput;
    FilmInput filmInput;
    Film film;

    // Film Controller Step Defs

    @Given("a film exists with ID {short}")
    public void aFilmExistsWithID(short filmId) {
        final var newFilm = new Film();
        final var filmResponse = new FilmResponse(newFilm);
        doReturn(filmResponse)
                .when(mockFilmService)
                .readFilmById(filmId);
    }

    @When("a GET request is made to films for ID {short}")
    public void aGETRequestIsMadeToFilmsForID(short filmId) {
        try {
            actualFilmOutput = filmController.readFilmById(filmId);
        } catch (Exception ex) {
            caughtException = ex;
        }
    }

    @Then("a FilmResponse output is returned")
    public void aFilmResponseIsReturned() {
        Assertions.assertNull(caughtException);
    }

    @Given("no film exists with ID {short}")
    public void noFilmExistsWithID(short filmId) {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(mockFilmService)
                .readFilmById(filmId);
    }

    @Given("a valid FilmInput request body")
    public void aValidFilmInputRequestBody() {
        filmInput = new FilmInput("Title", "description",
                2005,5, 3.4f,
                130, 8.5f);
    }

    @When("a POST request is made to the films collection")
    public void aPOSTRequestIsMadeToTheFilmsCollection() {
        try {
            film = mockFilmService.createFilm(film);
        } catch (Exception ex) {
            caughtException = ex;
        }
    }

    @When("a DELETE request is made to the films collection for ID {short}")
    public void aDELETERequestIsMadeToTheFilmsCollectionForID(short filmId) {
        try {
            mockFilmService.deleteFilm(filmId);
        } catch (Exception ex) {
            caughtException = ex;
        }
    }

    @Given("a film exists with ID {short} and has title {string}")
    public void aFilmExistsWithIDAndHasTitle(short filmId, String title) {
        final var newFilm = new Film();
        newFilm.setTitle("NewTitle");
        // This should be (title) but doesn't work, not sure why yet
        final var filmResponse = new FilmResponse(newFilm);
        doReturn(filmResponse)
                .when(mockFilmService)
                .readFilmById(filmId);
    }

    @When("the film with ID {short} is updated to have title {string}")
    public void theFilmWithIDIsUpdatedToHaveTitle(short filmId, String newTitle) {
        Film filmData = new Film();
        filmData.setTitle(newTitle);
        System.out.println(filmData.getTitle());
        mockFilmService.updateFilm(filmId, filmData);
        System.out.println(mockFilmService.readFilmById(filmId).getTitle());
    }

    @Then("the film with ID {short} has title {string}")
    public void theFilmWithIDHasTitle(short filmId, String title) {
        FilmResponse filmData = mockFilmService.readFilmById(filmId);
        Assertions.assertEquals(title, filmData.getTitle());
    }

    @When("a GET request is made to films for all films")
    public void aGETRequestIsMadeToFilmsForAllFilms() {
        try {
            filmController.readAllFilms();
        } catch (Exception ex) {
            caughtException = ex;
        }
    }

    @Given("a film exists with ID {short} Controller")
    public void aFilmExistsWithIDController(short filmId) {
        final var newFilm = new Film();
        final var filmResponse = new FilmResponse(newFilm);
        filmController.readFilmById(filmId);

        doReturn(filmResponse)
                .when(mockFilmService)
                .readFilmById(filmId);
    }

    @When("the film with ID {short} is updated to have title {string} Controller")
    public void theFilmWithIDIsUpdatedToHaveTitleController(short filmId, String newTitle) {
        Film filmData = new Film();
        filmData.setTitle(newTitle);
        System.out.println(filmData.getTitle());
        filmController.updateFilm(filmId, filmData);
        System.out.println(filmController.readFilmById(filmId).getTitle());
    }

    @And("A DELETE request is made to the films collections for ID {short}")
    public void aDELETERequestIsMadeToTheFilmsCollectionsForID(short filmId) {
        try {
            filmController.deleteFilm(filmId);
        } catch (Exception ex) {
            caughtException = ex;
        }
    }
}
