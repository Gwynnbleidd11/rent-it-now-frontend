package com.rent.rentitnowfrontend.apis.rapidapi.client;
import com.rent.rentitnowfrontend.apis.rapidapi.domain.imdb.rating.IMDbRatingResponseDto;
import com.rent.rentitnowfrontend.apis.rapidapi.domain.metacritic.score.IMDbMetacriticScoreResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
@RequiredArgsConstructor
public class IMDbClient {

    private final RestTemplate restTemplate;

    @Value("${RapidAPI-Key}")
    private String rapidApiKey;
    @Value("${RapidAPI-IMDb-Host}")
    private String imdbRapidApiEndpoint;

    //I am not entirely sure, why I was not able to pass variables above to the code below as I was getting an error,
    //so I hardcoded it for now
    //duplicated code, fix later
    public Integer getMatacriticScore(String imdbMovieId) {
        URI url = UriComponentsBuilder.fromHttpUrl("https://imdb8.p.rapidapi.com" + "/title/v2/get-metacritic")
                .queryParam("rapidapi-key", "8d5b15f1f4msh84715a49411b5d0p1e65aejsnaa91de4cf3fb")
                .queryParam("tconst", imdbMovieId)
                .build().encode().toUri();
        IMDbMetacriticScoreResponseDto dataResponse = restTemplate.getForObject(url, IMDbMetacriticScoreResponseDto.class);
        assert dataResponse != null;
        log.info(String.valueOf(dataResponse.getData().getTitle().getMetacritic().getMetascore().getScore()));
        return dataResponse.getData().getTitle().getMetacritic().getMetascore().getScore();
    }

    public Double getIMDbRating(String imdbMovieId) {
        URI url = UriComponentsBuilder.fromHttpUrl("https://imdb8.p.rapidapi.com" + "/title/v2/get-ratings")
                .queryParam("rapidapi-key", "8d5b15f1f4msh84715a49411b5d0p1e65aejsnaa91de4cf3fb")
                .queryParam("tconst", imdbMovieId)
                .build().encode().toUri();
        IMDbRatingResponseDto dataResponse = restTemplate.getForObject(url, IMDbRatingResponseDto.class);
        assert dataResponse != null;
        log.info(String.valueOf(dataResponse.getData().getTitle().getRatingsSummary().getAggregateRating()));
        return dataResponse.getData().getTitle().getRatingsSummary().getAggregateRating();
    }

    public String getIMDbMovieImage(String imdbMovieId) {
        URI url = UriComponentsBuilder.fromHttpUrl("https://imdb8.p.rapidapi.com" + "/title/v2/get-ratings")
                .queryParam("rapidapi-key", "8d5b15f1f4msh84715a49411b5d0p1e65aejsnaa91de4cf3fb")
                .queryParam("tconst", imdbMovieId)
                .build().encode().toUri();
        IMDbRatingResponseDto dataResponse = restTemplate.getForObject(url, IMDbRatingResponseDto.class);
        assert dataResponse != null;
        log.info(String.valueOf(dataResponse.getData().getTitle().getRatingsSummary().getAggregateRating()));
        return dataResponse.getData().getTitle().getPrimaryImage().getUrl();
    }
}
