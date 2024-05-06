package com.rent.rentitnowfrontend.apis.rapidapi.controller;

import com.rent.rentitnowfrontend.apis.rapidapi.client.WeatherClient;
import com.rent.rentitnowfrontend.apis.rapidapi.domain.weather.CoordsDto;
import com.rent.rentitnowfrontend.apis.rapidapi.domain.weather.WeatherDataResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/weather")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WeatherController {

    private final WeatherClient weatherClient;

    @GetMapping("/{lat}/{lon}")
    public WeatherDataResponseDto getWeather(@PathVariable final String lat, final String lon) {
        return weatherClient.getWeather(lat, lon);
    }

    @GetMapping("/coords/{city}")
    public List<CoordsDto> getCoords(@PathVariable final String city) {
        return weatherClient.getCoords(city);
    }
}
