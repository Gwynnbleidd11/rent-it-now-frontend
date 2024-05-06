package com.rent.rentitnowfrontend.apis.rapidapi.client;

import com.rent.rentitnowfrontend.apis.rapidapi.domain.weather.CoordsDto;
import com.rent.rentitnowfrontend.apis.rapidapi.domain.weather.WeatherDataResponseDto;
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
public class WeatherClient {

    private final RestTemplate restTemplate;
    private static final String API_KEY = "44d76f64849828e7115ceb837ae53510";
    private static final String WEATHER_URL = "https://api.openweathermap.org/";

    public WeatherDataResponseDto getWeather(String lat, String lon) {
        URI url = UriComponentsBuilder.fromHttpUrl(WEATHER_URL + "data/2.5/weather?")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY)
                .build().encode().toUri();
        WeatherDataResponseDto dataResponse = restTemplate.getForObject(url, WeatherDataResponseDto.class);
        return dataResponse;
    }

    public List<CoordsDto> getCoords(String city) {
        URI url = UriComponentsBuilder.fromHttpUrl(WEATHER_URL + "geo/1.0/direct?")
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .build().encode().toUri();
        CoordsDto[] dataResponse = restTemplate.getForObject(url, CoordsDto[].class);
        return Optional.ofNullable(dataResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
