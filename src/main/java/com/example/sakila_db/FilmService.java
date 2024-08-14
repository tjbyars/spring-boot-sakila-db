package com.example.sakila_db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Transactional
public class FilmService {

    @Autowired
    private FilmRepository filmRepo;

    @GetMapping("/films")
    public List<FilmResponse> readAllFilms() {
        return filmRepo.findAll()
                .stream()
                .map(FilmResponse::new)
                .toList();
    }

    @GetMapping("/films/{id}")
    public FilmResponse readFilmById(@PathVariable Short id) {
        return filmRepo.findById(id)
                .map(FilmResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/films")
    public Film createFilm(@Validated @RequestBody Film film) {
        return filmRepo.save(film);
    }

    @PutMapping("/films/{id}")
    public Film updateFilm(@PathVariable short id, @RequestBody Film filmData) {
        BeanUtils.copyProperties(filmData, filmRepo.getReferenceById(id));
        return filmRepo.save(filmRepo.getReferenceById(id));
    }

    @PatchMapping("/films/{id}")
    public Film partUpdateFilm(@PathVariable short id, @RequestBody String json)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Film film = filmRepo.findById(id).orElseThrow();
        ObjectReader objectReader = objectMapper.readerForUpdating(film);
        film = objectReader.readValue(json);
        return filmRepo.save(film);
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable short id) {
        filmRepo.deleteById(id);
    }
}

