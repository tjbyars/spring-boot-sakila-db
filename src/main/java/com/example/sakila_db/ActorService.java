package com.example.sakila_db;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ActorService {

    @Autowired
    private ActorRepository actorRepo;

    @PutMapping("/actors/{id}")
    public void updateActor(@PathVariable short id, @RequestBody ActorInput actorInput) {
        Actor actor = actorRepo.getReferenceById(id);
        actor.setFirstName(actorInput.getFirstName());
        actor.setLastName(actorInput.getLastName());
        actorRepo.save(actor);
    }

    @DeleteMapping("actors/{id}")
    public void deleteActor(@PathVariable short id) {
        actorRepo.deleteById(id);
    }

    @GetMapping("/actors")
    public List<ActorResponse> readAllActors() {
        return actorRepo.findAll()
                .stream()
                .map(ActorResponse::new)
                .toList();
    }

    @GetMapping("/actors/{id}")
    public ActorResponse readActorById(@PathVariable Short id) {
        return actorRepo.findById(id)
                .map(ActorResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Actor createActor(ActorInput actorData) {
        final var actor = new Actor();
        actor.setFirstName(actorData.getFirstName());
        actor.setLastName(actorData.getLastName());
        return actorRepo.save(actor);
    }
}

