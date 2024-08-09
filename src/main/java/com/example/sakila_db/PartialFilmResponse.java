package com.example.sakila_db;

import lombok.Getter;

import java.time.Year;

@Getter
public class PartialFilmResponse {

    private final Short id;
    private final String title;
    private final Year release_year;
//    private final String description;
//    private final String category;

    public PartialFilmResponse(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.release_year = Year.of(film.getRelease_year());
//        this.description = film.getDescription();
//        this.category = film.getCategory();
    }
}
