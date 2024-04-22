package com.rent.rentitnowfrontend.apis.backend.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long cartId;
    private Long userId;
    private List<Long> movieIds;
}
