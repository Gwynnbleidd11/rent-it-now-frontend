package com.rent.rentitnowfrontend.apis.backend.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long movieId;
    private String imdbMovieId;
    private String title;
    private String director;
    private String cast;
    private LocalDate publicationDate;
    private BigDecimal price;
}