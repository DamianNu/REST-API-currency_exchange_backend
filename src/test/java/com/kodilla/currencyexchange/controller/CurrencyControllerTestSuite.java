package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.domain.CurrencyDto;
import com.kodilla.currencyexchange.mapper.CurrencyMapper;
import com.kodilla.currencyexchange.service.DbServiceCurrency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyControllerTestSuite {

    @Autowired
    private CurrencyController controller;

    @MockBean
    private DbServiceCurrency dbService;

    @MockBean
    private CurrencyMapper mapper;

    @Test
    void shouldFetchCurrencyTest() {
        // Given

        List<Currency> currencyList = new ArrayList<>();
        Currency currency1 = new Currency(1L, "EUR", "euro", 4.30, 4.20, 4.35);
        Currency currency2 = new Currency(2L, "USD", "dolar amerykański", 4.10, 4.00, 4.20);
        Currency currency3 = new Currency(3L, "CHF", "frank szwajcarski", 4.55, 4.40, 4.70);
        currencyList.add(currency1);
        currencyList.add(currency2);
        currencyList.add(currency3);
        List<CurrencyDto> currencyListDto = new ArrayList<>();
        CurrencyDto currencyDto1 = new CurrencyDto(1L, "EUR", "euro", 4.30, 4.20, 4.35);
        CurrencyDto currencyDto2 = new CurrencyDto(2L, "USD", "dolar amerykański", 4.10, 4.00, 4.20);
        CurrencyDto currencyDto3 = new CurrencyDto(3L, "CHF", "frank szwajcarski", 4.55, 4.40, 4.70);
        currencyListDto.add(currencyDto1);
        currencyListDto.add(currencyDto2);
        currencyListDto.add(currencyDto3);

        when(dbService.getAllCurrency()).thenReturn(currencyList);
        when(mapper.mapToCurrencyDtoList(currencyList)).thenReturn(currencyListDto);
        //When & Then
        ResponseEntity<List<CurrencyDto>> listResponseEntity = controller.getCurrency();

        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
        List<CurrencyDto> listCurrencyDto = listResponseEntity.getBody();
        assertNotNull(listCurrencyDto);
        assertEquals("EUR", listCurrencyDto.get(0).getCode());
        assertEquals("USD", listCurrencyDto.get(1).getCode());
        assertEquals(4.55, listCurrencyDto.get(2).getMid());
    }

    @Test
    void shouldFetchCurrencyByIdTest() throws CurrencyNotFoundException {
        // Given
        Currency currency = new Currency(1L, "EUR", "euro", 4.30, 4.20, 4.35);
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR", "euro", 4.30, 4.20, 4.35);

        when(dbService.getCurrency(1L)).thenReturn(currency);
        when(mapper.mapToCurrencyDto(currency)).thenReturn(currencyDto);

        // When & Then
        ResponseEntity<CurrencyDto> responseEntity = controller.getCurrency(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CurrencyDto curDto = responseEntity.getBody();
        assertEquals("EUR", curDto.getCode());
        assertEquals("euro", curDto.getCurrency());
        assertEquals(4.3, curDto.getMid());
        assertEquals(4.2, curDto.getBuying());
        assertEquals(4.35, curDto.getSelling());
    }

    @Test
    void deleteCurrencyByIdTest() {
        // When & Then
        ResponseEntity<Void> responseEntity = controller.deleteCurrency(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateCurrencyTest() {
        // Given
        Currency currency = new Currency(1L, "EUR", "euro", 4.30, 4.20, 4.35);
        Currency saveCurrency = new Currency(1L, "EUR2", "euro2", 14.30, 14.20, 14.35);
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR", "euro", 4.30, 4.20, 4.35);
        CurrencyDto saveCurrencyDto = new CurrencyDto(1L, "EUR2", "euro2", 14.30, 14.20, 14.35);

        when(mapper.mapToCurrency(currencyDto)).thenReturn(currency);
        when(dbService.saveCurrency(currency)).thenReturn(saveCurrency);
        when(mapper.mapToCurrencyDto(saveCurrency)).thenReturn(saveCurrencyDto);

        // When & Then
        ResponseEntity<CurrencyDto> responseEntity = controller.updateCurrency(currencyDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CurrencyDto curDto = responseEntity.getBody();
        assertEquals("EUR2", curDto.getCode());
        assertEquals("euro2", curDto.getCurrency());
        assertEquals(14.3, curDto.getMid());
        assertEquals(14.2, curDto.getBuying());
        assertEquals(14.35, curDto.getSelling());
    }

    @Test
    void createCurrencyTest() {
        // Given
        Currency currency = new Currency(1L, "EUR", "euro", 4.30, 4.20, 4.35);
        CurrencyDto currencyDto = new CurrencyDto(1L, "EUR", "euro", 4.30, 4.20, 4.35);

        when(mapper.mapToCurrency(currencyDto)).thenReturn(currency);
        when(dbService.saveCurrency(currency)).thenReturn(currency);

        // When & Then
        ResponseEntity<Void> responseEntity = controller.createCurrency(currencyDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldFetchCurrencyByCodeTest() throws CurrencyNotFoundException {
        // Given
        Currency currency = new Currency(1L, "USD", "dolar amerykański", 4.50, 4.40, 4.60);
        CurrencyDto currencyDto = new CurrencyDto(1L, "USD", "dolar amerykański", 4.50, 4.40, 4.60);

        when(dbService.getCurrencyByCode("USD")).thenReturn(currency);
        when(mapper.mapToCurrencyDto(currency)).thenReturn(currencyDto);

        // When & Then
        ResponseEntity<CurrencyDto> responseEntity = controller.getCurrencyByCode("USD");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CurrencyDto curDto = responseEntity.getBody();
        assertEquals("USD", curDto.getCode());
        assertEquals("dolar amerykański", curDto.getCurrency());
        assertEquals(4.5, curDto.getMid());
        assertEquals(4.4, curDto.getBuying());
        assertEquals(4.6, curDto.getSelling());
    }
}
