package com.kodilla.currencyexchange.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CantorDto {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String openingHours;
}
