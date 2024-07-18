package com.example.sakila_db;

import lombok.Getter;

import java.time.Year;

@Getter
public class PartialFilmResponse {

    private final Short id;
    private final String title;
    private final Year releaseYear;

    public PartialFilmResponse(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.releaseYear = Year.of(film.getRelease_year());
    }
}
