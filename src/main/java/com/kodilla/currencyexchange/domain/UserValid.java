package com.kodilla.currencyexchange.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserValid {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime date;

    public UserValid(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
