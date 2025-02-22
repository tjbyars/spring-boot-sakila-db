package com.example.sakila_db;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class FilmInput {

    @NotNull(groups = ValidationGroup.Create.class)
    @Min(0)
    @Max(65535)
    private short id;

    @NotNull
    @Size(min = 1, max = 128)
    private String title;

    @Size(min = 1)
    private String description;

    @Min(1888)
    private int release_year;

    @NotNull
    @Min(0)
    @Max(255)
    private Language language_id;

    @Min(0)
    @Max(255)
    private Short original_language_id;

    @NotNull
    @Min(0)
    @Max(255)
    private int rental_duration;

    @NotNull
    @DecimalMax("99.99")
    @DecimalMin("00.00")
    private float rental_rate;

    @Min(0)
    @Max(65535)
    private int length;

    @NotNull
    @DecimalMax("999.99")
    @DecimalMin("00.00")
    private float replacement_cost;


    private String rating;


    private String special_features;

    @NotNull
    private Timestamp last_update;

    public FilmInput(String title, String description, int release_year,
                     int rental_duration, float rental_rate, int length,
                     float replacement_cost) {
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.length = length;
        this.replacement_cost = replacement_cost;
    }
}
