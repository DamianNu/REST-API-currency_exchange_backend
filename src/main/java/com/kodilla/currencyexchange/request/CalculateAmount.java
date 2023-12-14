package com.kodilla.currencyexchange.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CalculateAmount {
    private double amount;
    private String code;
    private String type;
}
