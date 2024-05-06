package com.rent.rentitnowfrontend.apis.rapidapi.domain.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDataResponseDto {

    @JsonProperty("main")
    private WeatherMainResponseDto main;
    @JsonProperty("weather")
    private WeatherWeatherResponseDto[] weather;
}
