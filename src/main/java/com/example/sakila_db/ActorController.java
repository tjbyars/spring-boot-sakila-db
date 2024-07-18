package com.example.sakila_db;

import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(
            @Validated(ValidationGroup.Create.class) @RequestBody ActorInput actorInput) {
        return actorService.createActor(actorInput);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateActor(
            @PathVariable short id,
            @RequestBody ActorInput actorInput) {
        actorService.updateActor(id, actorInput);
    }

    @DeleteMapping("actors/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteActor(
            @PathVariable short id) {
        actorService.deleteActor(id);
    }

    @GetMapping("/actors")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ActorResponse> readAllActors() {
        return actorService.readAllActors();
    }

    @GetMapping("/actors/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ActorResponse readActorById(
            @PathVariable Short id) {
        return actorService.readActorById(id);
    }

}