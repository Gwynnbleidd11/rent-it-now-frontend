package com.rent.rentitnowfrontend.apis.backend.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rent.rentitnowfrontend.apis.backend.domain.TransactionDto;
import com.rent.rentitnowfrontend.apis.backend.domain.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
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

    public void payTransaction(Long transactionId, Long cartId, TransactionType transactionType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        String transactionTypeJson;
        try {
            transactionTypeJson = objectMapper.writeValueAsString(transactionType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(transactionTypeJson, headers);
        String url = RENT_IT_NOW_URL + "/transactions/pay/" + transactionId + "/" + cartId;
        restTemplate.put(url, requestEntity);
    }
}
