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

public class ActorControllerStepDefs {

    ActorService mockService;
    ActorController controller;
    ActorResponse actualOutput;
    Exception caughtException;
    ActorInput actorInput;
    ActorInput invalidActorInput;
    Actor actor;


    @Before
    public void setup() {
        mockService = mock(ActorService.class);
        controller = new ActorController(mockService);
    }


    @Given("an actor exists with ID {short}")
    public void anActorExistsWithID(short actorId) {
        final var actor = new Actor(actorId, "Joe", "Bloggs", List.of());
        final var actorResponse = new ActorResponse(actor);
        doReturn(actorResponse)
                .when(mockService)
                .readActorById(actorId);
    }
    // doReturn(Optional.of(actor).when(mockService).readActorById(actorId);

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
//        Assertions.assertNotNull(actor);
        Assertions.assertNull(caughtException);
    }
}
