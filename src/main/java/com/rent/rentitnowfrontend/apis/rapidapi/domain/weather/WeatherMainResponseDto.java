package com.rent.rentitnowfrontend.apis.rapidapi.domain.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMainResponseDto {

    @JsonProperty("temp")
    private double temp;
    @JsonProperty("humidity")
    private int humidity;
}
