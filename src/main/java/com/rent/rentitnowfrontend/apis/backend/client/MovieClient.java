package com.rent.rentitnowfrontend.apis.backend.client;

import com.rent.rentitnowfrontend.apis.backend.domain.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieClient {

    private final RestTemplate restTemplate;
    private final String RENT_IT_NOW_URL = "http://localhost:8080";

    public MovieDto getMovie(String movieId) {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/movies/" + movieId)
                .build().encode().toUri();
        return restTemplate.getForObject(url, MovieDto.class);
    }

    public List<MovieDto> getAllMovies() {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/movies")
                .build().encode().toUri();
        MovieDto[] moviesResponse = restTemplate.getForObject(url, MovieDto[].class);
        return Optional.ofNullable(moviesResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
