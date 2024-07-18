package com.example.sakila_db;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
public class FilmInput {
    private short id;
    private String title;
    private String description;
    private int release_year;
    private Language language_id;
    private Short original_language_id;
    private int rental_duration;
    private float rental_rate;
    private int length;
    private float replacement_cost;
    private String rating;
    private String special_features;
    private Timestamp last_update;
}
