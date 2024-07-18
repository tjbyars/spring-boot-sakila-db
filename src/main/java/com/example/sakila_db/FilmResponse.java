package com.example.sakila_db;

import lombok.Getter;

import java.util.List;

@Getter
public class FilmResponse {
    private final Short id;
    private final String title;
    private final String description;
    private final List<PartialActorResponse> cast;

    public FilmResponse(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.description = film.getDescription();
        this.cast = film.getCast()
                .stream()
                .map(PartialActorResponse::new)
                .toList();
    }
}
