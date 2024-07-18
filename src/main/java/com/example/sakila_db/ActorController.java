package com.example.sakila_db;

import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ActorController {

    // Repository
    @Autowired
    private ActorRepository actorRepository;

    @PostMapping("/actors")
    public Actor createCustom(@RequestBody ActorInput actorData) {
        final var actor = new Actor();
        actor.setFirstName(actorData.getFirstName());
        actor.setLastName(actorData.getLastName());
        return actorRepository.save(actor);
    }

//    @GetMapping("/actors")
//    public List<Actor> readAll() {
//        return actorRepository.findAll();
//    }

//    @GetMapping("/actors/{id}")
//    public Actor findActor(@PathVariable short id) {
//        return actorRepository.findById(id).get();
//    }

    @PutMapping("/actors/{id}")
    public void updateActor(@PathVariable short id, @RequestBody ActorInput actorInput) {
        Actor actor = actorRepository.getReferenceById(id);
        actor.setFirstName(actorInput.getFirstName());
        actor.setLastName(actorInput.getLastName());
        actorRepository.save(actor);
    }

    @DeleteMapping("actors/{id}")
    public void deleteActor(@PathVariable short id) {
        actorRepository.deleteById(id);
    }

    @GetMapping("/actors")
    public List<ActorResponse> readAllActors() {
        return actorRepository.findAll()
                .stream()
                .map(ActorResponse::new)
                .toList();
    }

    @GetMapping("/actors/{id}")
    public ActorResponse readActorById(@PathVariable Short id) {
        return actorRepository.findById(id)
                .map(ActorResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }




}
