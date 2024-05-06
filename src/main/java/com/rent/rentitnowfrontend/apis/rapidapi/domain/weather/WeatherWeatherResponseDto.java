package com.rent.rentitnowfrontend.apis.rapidapi.domain.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherWeatherResponseDto {

    @JsonProperty("main")
    private String main;
    @JsonProperty("description")
    private String description;
}
