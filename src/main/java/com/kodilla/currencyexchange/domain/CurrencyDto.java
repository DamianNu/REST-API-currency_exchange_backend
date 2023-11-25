package com.kodilla.currencyexchange.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrencyDto {
    private Long id;
    private String code;
    private String currency;
}
