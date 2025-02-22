package com.example.sakila_db;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
@CrossOrigin
@RequiredArgsConstructor
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public Film createFilm(@Validated @RequestBody Film film) {
        return filmService.createFilm(film);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateFilm(@PathVariable short id,
                           @RequestBody Film filmData) {
        filmService.updateFilm(id, filmData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteFilm(
            @PathVariable short id) {
        filmService.deleteFilm(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Film partUpdateFilm(
            @PathVariable short id,
            @RequestBody String json) throws JsonProcessingException {
        return filmService.partUpdateFilm(id, json);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<FilmResponse> readAllFilms() {
        return filmService.readAllFilms();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public FilmResponse readFilmById(
            @PathVariable Short id) {
        return filmService.readFilmById(id);
    }

}
