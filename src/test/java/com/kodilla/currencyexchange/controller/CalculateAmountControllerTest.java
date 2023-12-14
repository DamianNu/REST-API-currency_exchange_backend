package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.request.Amount;
import com.kodilla.currencyexchange.request.CalculateAmount;
import com.kodilla.currencyexchange.service.DbServiceCurrency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class CalculateAmountControllerTest {

    @Autowired
    private CalculateAmountController calculateAmountController;

    @MockBean
    private DbServiceCurrency serviceCurrency;

    @Test
    void shouldCalculateCurrencyBuyingTest() throws CurrencyNotFoundException {
        // Given
        Currency currency1 = new Currency(1L, "EUR", "euro", 4.30, 4.20, 4.35);
        Currency currency2 = new Currency(2L, "USD", "dolar amerykański", 4.10, 4.00, 4.20);
        Currency currency3 = new Currency(3L, "CHF", "frank szwajcarski", 4.55, 4.40, 4.70);

        CalculateAmount calculateAmount = new CalculateAmount(100.00, "USD", "buying");

        when(serviceCurrency.getCurrencyByCode("EUR")).thenReturn(currency1);
        when(serviceCurrency.getCurrencyByCode("USD")).thenReturn(currency2);
        when(serviceCurrency.getCurrencyByCode("CHF")).thenReturn(currency3);

        //When & Then
        ResponseEntity<Amount> responseEntity = calculateAmountController.calculateAmount(calculateAmount);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Amount calculatedAmount = responseEntity.getBody();
        assertNotNull(calculatedAmount);
        assertEquals(400.0, calculatedAmount.getResult());
    }

    @Test
    void shouldCalculateCurrencySellingTest() throws CurrencyNotFoundException {
        // Given
        Currency currency1 = new Currency(1L, "EUR", "euro", 4.30, 4.20, 4.35);
        Currency currency2 = new Currency(2L, "USD", "dolar amerykański", 4.10, 4.00, 4.20);
        Currency currency3 = new Currency(3L, "CHF", "frank szwajcarski", 4.55, 4.40, 4.70);

        CalculateAmount calculateAmount = new CalculateAmount(120.00, "CHF", "selling");

        when(serviceCurrency.getCurrencyByCode("EUR")).thenReturn(currency1);
        when(serviceCurrency.getCurrencyByCode("USD")).thenReturn(currency2);
        when(serviceCurrency.getCurrencyByCode("CHF")).thenReturn(currency3);

        //When & Then
        ResponseEntity<Amount> responseEntity = calculateAmountController.calculateAmount(calculateAmount);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Amount calculatedAmount = responseEntity.getBody();
        assertNotNull(calculatedAmount);
        assertEquals(120.0 * 4.7, calculatedAmount.getResult());
    }
}