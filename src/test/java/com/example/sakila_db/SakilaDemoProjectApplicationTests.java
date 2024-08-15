package com.example.sakila_db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.sql.Timestamp;

import static org.mockito.Mockito.*;

@SpringBootTest
class SakilaDemoProjectApplicationTests {

	private ActorController actorController;
    private FilmController filmController;

    @BeforeEach
    public void setup() {
        final var mockService = mock(ActorService.class);
        final var actor = new Actor();
        actor.setFirstName("fName");
        actor.setLastName("lName");
        final var actorResponse = new ActorResponse(actor);
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(mockService)
                .readActorById(any());
        doReturn(actorResponse).
                when(mockService).
                readActorById((short) 1);
        actorController = new ActorController(mockService);

        final var mockFilmService = mock(FilmService.class);
        final var film = new Film();
        film.setTitle("TEST TITLE");
        film.setDescription("This Is A Test Description");
        film.setRelease_year(2005);
        film.setLanguage_id((short) 1);
        film.setRental_duration(3);
        film.setRental_rate(4f);
        film.setLength(120);
        film.setReplacement_cost(6f);
        film.setRating("G");
        film.setSpecial_features("Trailers");
        Timestamp timestamp = new Timestamp(1124103810000L);
        film.setLast_update(timestamp);
        final var filmResponse = new FilmResponse(film);
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(mockFilmService)
                .readFilmById(any());
        doReturn(filmResponse)
                .when(mockFilmService)
                .readFilmById((short) 1);
        filmController = new FilmController(mockFilmService);
    }

    @Test
    protected void actorControllerReadActorByIdReturnsExistingActor() {
        final var expectedFirstName = "fName";
        final var expectedLastName = "lName";

        final var actual = actorController.readActorById((short) 1);

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

    @Test
    protected void filmControllerReadFilmByIdReturnsExistingFilm() {
        final var expectedTitle = "TEST TITLE";
        final var expectedLength = 120;

        final var actual = filmController.readFilmById((short) 1);

        Assertions.assertEquals(expectedTitle, actual.getTitle());
        Assertions.assertEquals(expectedLength, actual.getLength());
        Assertions.assertNotNull(actual.getId());
        Assertions.assertNotNull(actual.getDescription());
        Assertions.assertNotNull(actual.getCast());
        Assertions.assertNotNull(actual.getLanguage_id());
        Assertions.assertNotNull(actual.getRating());
        Assertions.assertNotNull(actual.getSpecial_features());
        Assertions.assertNotNull(actual.getLast_update());
     }
}
