package com.rent.rentitnowfrontend.apis.rapidapi.domain.imdb.rating;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IMDbRatingResponseDto {

    @JsonProperty("data")
    private IMDbDataResponseDto data;
}
