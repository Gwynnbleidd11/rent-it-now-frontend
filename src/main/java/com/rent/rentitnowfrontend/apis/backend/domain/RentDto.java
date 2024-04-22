package com.rent.rentitnowfrontend.apis.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {

    private Long rentId;
    private BigDecimal cost;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private MovieDto movie;
    private UserDto user;
}
