package com.rent.rentitnowfrontend.apis.backend.client;

import com.rent.rentitnowfrontend.apis.backend.domain.RentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RentClient {

    private final RestTemplate restTemplate;

    private final String RENT_IT_NOW_URL = "http://localhost:8080";

    public List<RentDto> getUserRents(@PathVariable Long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/rents/user/" + userId)
                .build().encode().toUri();
        RentDto[] rentsResponse = restTemplate.getForObject(url, RentDto[].class);
        return Optional.ofNullable(rentsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<RentDto> getCurrentRents(Long userId) {
        List<RentDto> currentRents = getUserRents(userId).stream()
                .filter(r -> r.getReturnDate().isAfter(LocalDate.now()))
                .toList();
        return currentRents;
    }
}
