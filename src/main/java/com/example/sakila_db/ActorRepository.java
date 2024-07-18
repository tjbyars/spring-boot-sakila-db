package com.example.sakila_db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Short> {

    List<Actor> findAll();

    List<Actor> findAllByFirstName(String firstName);
    List<Actor> findAllByFirstNameContainsIgnoreCase(String firstName);




}
