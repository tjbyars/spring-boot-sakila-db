package com.example.sakila_db;

import lombok.Getter;

import java.time.Year;

@Getter
public class PartialFilmResponse {

    private final Short id;
    private final String title;
    private final Year release_year;

    public PartialFilmResponse(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.release_year = Year.of(film.getRelease_year());
    }
}
