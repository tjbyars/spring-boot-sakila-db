package com.example.sakila_db;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Column(name = "title")
    private String title;

    @Setter
    @Column(name = "description")
    private String description;

    @Setter
    @Column(name = "release_year")
    private int release_year;

//    @Getter
//    @Column(name = "language_id")
//    private short language_id;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

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

/*
film_id     short
title       string
description     string
release_year    int
language_id     int
original_language_id    int
rental_duration     int
rental_rate     float
length      int
replacement_cost    float
rating      string - G  PG  PG-13   R   NC-17
special_features    string - Trailers   Commentaries    Deleted Scenes  Behind the Scenes
last_update     timestamp
 */