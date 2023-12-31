package com.kodilla.currencyexchange.domain.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrencyDto {
    private Long id;
    private String code;
    private String currency;
    private double mid;
    private double buying;
    private double selling;
}

