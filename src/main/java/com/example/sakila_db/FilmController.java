package com.example.sakila_db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    private FilmRepository filmRepo;

    @GetMapping("/films")
    public List<Film> readAll() {
        return filmRepo.findAll();
    }

    @GetMapping("/films/{id}")
    public Film readFilm(@PathVariable short id) {
        return filmRepo.findById(id).get();
    }

    @PostMapping("/films")
    public Film createFilm(@RequestBody Film film) {
        return filmRepo.save(film);
    }

    @PutMapping("/films/{id}")
    public Film updateFilm(@PathVariable short id, @RequestBody Film filmData) {
        BeanUtils.copyProperties(filmData, filmRepo.getReferenceById(id));
        return filmRepo.save(filmRepo.getReferenceById(id));
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable short id) {
        filmRepo.deleteById(id);
    }

//    public Film partUpdateFilm(@PathVariable short id, @RequestBody FilmInput filmInput) {
//        Film film = filmRepo.getReferenceById(id);
//        BeanUtils.copyProperties(filmInput, film);
//        return filmRepo.save(film);

    @PatchMapping("/films/{id}")
    public Film partUpdateFilm(@PathVariable short id, @RequestBody String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Film film = filmRepo.findById(id).orElseThrow();
        ObjectReader objectReader = objectMapper.readerForUpdating(film);
        film = objectReader.readValue(json);
        return filmRepo.save(film);
    }
}
