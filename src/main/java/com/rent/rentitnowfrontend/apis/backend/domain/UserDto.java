package com.rent.rentitnowfrontend.apis.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDate creationDate;
}
