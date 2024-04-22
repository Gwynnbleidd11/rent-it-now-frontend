package com.rent.rentitnowfrontend.apis.backend.client;

import com.rent.rentitnowfrontend.apis.backend.domain.CartDto;
import com.rent.rentitnowfrontend.apis.backend.domain.MovieDto;
import com.rent.rentitnowfrontend.apis.backend.domain.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CartClient {

    private final RestTemplate restTemplate;
    private final String RENT_IT_NOW_URL = "http://localhost:8080";

    public CartDto getCart(Long cartId) {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/carts/" + cartId)
                .build().encode().toUri();
        return restTemplate.getForObject(url, CartDto.class);
    }

    public MovieDto[] getMoviesInCart(Long cartId) {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/carts/movies/" + cartId)
                .build().encode().toUri();
        return restTemplate.getForObject(url, MovieDto[].class);
    }

    public void addMovieToCart(String cartId, String movieId) {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/carts/add/" + cartId + "/" + movieId)
                .build().encode().toUri();
        restTemplate.put(url, null);
    }

    public void removeMovieFromCart(String cartId, String movieId) {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/carts/remove/" + cartId + "/" + movieId)
                .build().encode().toUri();
        restTemplate.put(url, null);
    }

    public TransactionDto createTransaction(String cartId) {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/carts/transaction/" + cartId)
                .build().encode().toUri();
        ResponseEntity<Void> response = restTemplate.postForEntity(url, null, Void.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            TransactionDto transactionDto = new TransactionDto();
            return transactionDto;
        } else {
            return null;
        }
    }
}
