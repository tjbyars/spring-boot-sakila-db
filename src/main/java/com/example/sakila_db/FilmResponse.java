package com.example.sakila_db;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class FilmResponse {
    private final Short id;
    private final String title;
    private final String description;
    private final List<PartialActorResponse> cast;
    private final int release_year;
    private final Language language_id;
    private final Short original_language_id;
    private final int rental_duration;
    private final float rental_rate;
    private final int length;
    private final float replacement_cost;
    private final String rating;
    private final String special_features;
    private final Timestamp last_update;

    public FilmResponse(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.description = film.getDescription();
        this.cast = film.getCast()
                .stream()
                .map(PartialActorResponse::new)
                .toList();
        this.release_year = film.getRelease_year();
        this.language_id = film.getLanguage();
        this.original_language_id = film.getOriginal_language_id();
        this.rental_duration = film.getRental_duration();
        this.rental_rate = film.getRental_rate();
        this.length = film.getLength();
        this.replacement_cost = film.getReplacement_cost();
        this.rating = film.getRating();
        this.special_features = film.getSpecial_features();
        this.last_update = film.getLast_update();
    }
}
