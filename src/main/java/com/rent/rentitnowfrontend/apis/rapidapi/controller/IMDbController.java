package com.rent.rentitnowfrontend.apis.rapidapi.controller;

import com.rent.rentitnowfrontend.apis.rapidapi.client.IMDbClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/imdb")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IMDbController {

    private final IMDbClient imdbClient;

    @GetMapping(value = "/metacritic/{imdbMovieId}")
    public ResponseEntity<Integer> getMetacriticScore(@PathVariable final String imdbMovieId) {
        Integer response = imdbClient.getMatacriticScore(imdbMovieId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/imdbrating/{imdbMovieId}")
    public ResponseEntity<Double> getIMDbRating(@PathVariable final String imdbMovieId) {
        Double response = imdbClient.getIMDbRating(imdbMovieId);
        return ResponseEntity.ok(response);
    }
}
