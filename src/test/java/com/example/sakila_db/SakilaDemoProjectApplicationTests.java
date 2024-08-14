package com.example.sakila_db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import static org.mockito.Mockito.*;

@SpringBootTest
class SakilaDemoProjectApplicationTests {

	private ActorController actorController;

    @BeforeEach
    public void setup() {
        final var mockService = mock(ActorService.class);

        final var actor = new Actor();
        actor.setFirstName("fName");
        actor.setLastName("lName");

        final var actorResponse = new ActorResponse(actor);
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(mockService).readActorById(any());
        doReturn(actorResponse).when(mockService).readActorById((short)1);

        actorController = new ActorController(mockService);
    }

    @Test
    protected void actorControllerReadActorByIdReturnsExistingActor() {
        final var expectedFirstName = "fName";
        final var expectedLastName = "lName";

        final var actual = actorController.readActorById((short)1);

        Assertions.assertEquals(expectedFirstName, actual.getFirstName());
        Assertions.assertEquals(expectedLastName, actual.getLastName());
    }

    @Test
    protected void actorControllerReadActorByIdThrows404WhenInvalidId() {
        Exception exception = null;
        try {
            actorController.readActorById((short)2);
        } catch (Exception e) {
            exception = e;
        }
        Assertions.assertNotNull(exception);
        Assertions.assertInstanceOf(ResponseStatusException.class, exception);
        Assertions.assertEquals(HttpStatus.NOT_FOUND,
                ((ResponseStatusException)exception).getStatusCode());
     }
}
