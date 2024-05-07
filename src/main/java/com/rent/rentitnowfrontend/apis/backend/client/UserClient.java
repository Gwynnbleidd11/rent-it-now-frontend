package com.rent.rentitnowfrontend.apis.backend.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import com.rent.rentitnowfrontend.apis.backend.domain.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

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

    public void changeUserName(final Long userId, final String name) {
        UserDto userDto = getUser(userId);
        if (!userDto.getFirstname().equals(name)) {
            userDto.setFirstname(name);
        }
        Gson gson = new Gson();
        String userJson = gson.toJson(userDto);
        URI url = UriComponentsBuilder.fromHttpUrl(RENT_IT_NOW_URL + "/users")
                .build().encode().toUri();
        restTemplate.put(url, userJson);
    }
}
