//package com.example.sakila_db;
//
//import jakarta.persistence.*;
//import lombok.Setter;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "category_id")
//    private short id;
//
//    @Setter
//    @Column(name = "name")
//    private String name;
//
//    @Setter
//    @Column(name = "last_update")
//    private Timestamp last_update;
//
//    @ManyToMany(mappedBy = "categories")
//    private List<Film> films = new ArrayList<>();
//}
