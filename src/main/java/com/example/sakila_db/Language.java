package com.example.sakila_db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Language {

    @Id
    @Column(name = "language_id")
    private byte id;

    @Column(name = "name")
    private String name;

}
