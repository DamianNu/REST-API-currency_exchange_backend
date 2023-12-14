package com.kodilla.currencyexchange.service;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.request.Amount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AmountServiceTestSuite {

    @Autowired
    private AmountService amountService;

    @Test
    void calculateBuyingCurrencyTest() {
        // Given
        Currency currency = new Currency(1L, "EUR", "euro", 4.30, 4.20, 4.40);

        //When
        Amount amount = amountService.calculateBuyingCurrency(currency, 100.0);

        // Then
        assertEquals(420.0, amount.getResult());
    }

    @Test
    void calculateSellingCurrencyTest() {
        // Given
        Currency currency = new Currency(1L, "EUR", "euro", 4.30, 4.20, 4.40);

        //When
        Amount amount = amountService.calculateSellingCurrency(currency, 150.0);

        // Then
        assertEquals(660.0, amount.getResult());
    }
}