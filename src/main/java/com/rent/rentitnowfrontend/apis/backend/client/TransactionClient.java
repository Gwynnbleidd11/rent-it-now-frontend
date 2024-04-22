package com.rent.rentitnowfrontend.apis.backend.client;

import com.rent.rentitnowfrontend.apis.backend.domain.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TransactionClient {

    private final RestTemplate restTemplate;

    private final String RENT_IT_NOW_URL = "http://localhost:8080";

    public List<TransactionDto> getUserTransactions(@PathVariable Long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/transactions/user/" + userId)
                .build().encode().toUri();
        TransactionDto[] transactionResponse = restTemplate.getForObject(url, TransactionDto[].class);
        return Optional.ofNullable(transactionResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
