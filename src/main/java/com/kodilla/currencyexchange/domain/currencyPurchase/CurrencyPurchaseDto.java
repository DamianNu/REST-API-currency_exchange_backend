package com.kodilla.currencyexchange.domain.currencyPurchase;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CurrencyPurchaseDto {
    private Long id;
    private String currencyCode;
    private String operationType;
    private double amount;
    private double purchaseRate;
    private double purchaseAmount;
    private LocalDateTime purchaseDate;
}
