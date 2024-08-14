package com.example.sakila_db;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:5173"})
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor createActor(
            @Validated(ValidationGroup.Create.class) @RequestBody ActorInput actorInput) {
        return actorService.createActor(actorInput);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateActor(
            @PathVariable short id,
            @RequestBody ActorInput actorInput) {
        actorService.updateActor(id, actorInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteActor(
            @PathVariable short id) {
        actorService.deleteActor(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ActorResponse> readAllActors() {
        return actorService.readAllActors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ActorResponse readActorById(
            @PathVariable Short id) {
        return actorService.readActorById(id);
    }

}