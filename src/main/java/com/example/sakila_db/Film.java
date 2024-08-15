package com.example.sakila_db;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private short id;

    @Setter
    @Column(name = "title")
    private String title;

    @Setter
    @Column(name = "description")
    private String description;

    @Setter
    @Column(name = "release_year")
    private int release_year;

    @Setter
    @Column(name = "language_id")
    private Short language_id;

    @Nullable
    @Column(name = "original_language_id", insertable = false, updatable = false)
    private Short original_language_id;

    @Setter
    @Column(name = "rental_duration")
    private int rental_duration;

    @Setter
    @Column(name = "rental_rate")
    private float rental_rate;

    @Setter
    @Column(name = "length")
    private int length;

    @Setter
    @Column(name = "replacement_cost")
    private float replacement_cost;

    @Setter
    @Column(name = "rating")
    private String rating;

    @Setter
    @Column(name = "special_features")
    private String special_features;

    @Setter
    @Column(name = "last_update")
    private Timestamp last_update;

    @ManyToMany(mappedBy = "films")
    private List<Actor> cast = new ArrayList<>();
}