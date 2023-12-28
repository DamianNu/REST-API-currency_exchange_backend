package com.kodilla.currencyexchange.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime date;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
