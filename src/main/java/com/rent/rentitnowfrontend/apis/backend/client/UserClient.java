package com.rent.rentitnowfrontend.apis.backend.client;

import com.rent.rentitnowfrontend.apis.backend.domain.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;
    private final String RENT_IT_NOW_URL = "http://localhost:8080";

    public UserDto getUser(final Long userId) {
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/users/" + userId)
                .build().encode().toUri();
        return restTemplate.getForObject(url, UserDto.class);
    }
}
