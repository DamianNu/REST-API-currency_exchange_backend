package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.currencyPurchase.CurrencyPurchase;
import com.kodilla.currencyexchange.domain.currencyPurchase.CurrencyPurchaseDto;
import com.kodilla.currencyexchange.exception.CurrencyPurchaseNotFoundException;
import com.kodilla.currencyexchange.mapper.CurrencyPurchaseMapper;
import com.kodilla.currencyexchange.service.db.DbServiceCurrencyPurchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyPurchaseControllerTestSuite {
    @Autowired
    private CurrencyPurchaseController controller;

    @MockBean
    private DbServiceCurrencyPurchase dbService;

    @MockBean
    private CurrencyPurchaseMapper mapper;

    @Test
    void shouldFetchCurrencyPurchaseTest() {
        // Given
        List<CurrencyPurchase> currencyPurchaseList = new ArrayList<>();
        CurrencyPurchase currencyPurchase1 = new CurrencyPurchase(1L, "PLN", "buying",
                100.0, 1.1, 110.0, null);
        CurrencyPurchase currencyPurchase2 = new CurrencyPurchase(2L, "USD", "selling", 100.0,
                4.1, 410.0, null);
        CurrencyPurchase currencyPurchase3 = new CurrencyPurchase(3L, "EUR", "buying", 100.0,
                4.5, 450.0, null);
        currencyPurchaseList.add(currencyPurchase1);
        currencyPurchaseList.add(currencyPurchase2);
        currencyPurchaseList.add(currencyPurchase3);

        List<CurrencyPurchaseDto> currencyPurchaseDtoList = new ArrayList<>();
        CurrencyPurchaseDto currencyPurchaseDto1 = new CurrencyPurchaseDto(1L, "PLN", "buying",
                100.0, 1.1, 110.0, null);
        CurrencyPurchaseDto currencyPurchaseDto2 = new CurrencyPurchaseDto(2L, "USD", "selling", 100.0,
                4.1, 410.0, null);
        CurrencyPurchaseDto currencyPurchaseDto3 = new CurrencyPurchaseDto(3L, "EUR", "buying", 100.0,
                4.5, 450.0, null);
        currencyPurchaseDtoList.add(currencyPurchaseDto1);
        currencyPurchaseDtoList.add(currencyPurchaseDto2);
        currencyPurchaseDtoList.add(currencyPurchaseDto3);

        when(dbService.getAllCurrencyPurchase()).thenReturn(currencyPurchaseList);
        when(mapper.mapToCurrencyPurchaseDtoList(currencyPurchaseList)).thenReturn(currencyPurchaseDtoList);
        //When & Then
        ResponseEntity<List<CurrencyPurchaseDto>> listResponseEntity = controller.getCurrencyPurchase();

        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
        List<CurrencyPurchaseDto> currencyPurchaseDtoListTest = listResponseEntity.getBody();
        assertNotNull(currencyPurchaseDtoListTest);
        assertEquals("PLN", currencyPurchaseDtoListTest.get(0).getCurrencyCode());
        assertEquals("selling", currencyPurchaseDtoListTest.get(1).getOperationType());
        assertEquals(100.0, currencyPurchaseDtoListTest.get(2).getAmount());
        assertEquals(1.1, currencyPurchaseDtoListTest.get(0).getPurchaseRate());
        assertEquals(410.0, currencyPurchaseDtoListTest.get(1).getPurchaseAmount());
    }

    @Test
    void shouldFetchCurrencyPurchaseByIdTest() throws CurrencyPurchaseNotFoundException {
        // Given
        CurrencyPurchase currencyPurchase = new CurrencyPurchase(1L, "PLN", "buying",
                100.0, 1.1, 110.0, null);
        CurrencyPurchaseDto currencyPurchaseDto = new CurrencyPurchaseDto(1L, "PLN", "buying",
                100.0, 1.1, 110.0, null);

        when(dbService.getCurrencyPurchase(1L)).thenReturn(currencyPurchase);
        when(mapper.mapToCurrencyPurchaseDto(currencyPurchase)).thenReturn(currencyPurchaseDto);

        // When & Then
        ResponseEntity<CurrencyPurchaseDto> responseEntity = controller.getCurrencyPurchaseById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CurrencyPurchaseDto currencyPurchaseDtoTest = responseEntity.getBody();
        assertEquals(100.0, currencyPurchaseDtoTest.getAmount());
        assertEquals(1.1, currencyPurchaseDtoTest.getPurchaseRate());
        assertEquals("PLN", currencyPurchaseDtoTest.getCurrencyCode());
    }

    @Test
    void shouldFetchCurrencyPurchaseByCodeTest() throws CurrencyPurchaseNotFoundException {
        // Given
        CurrencyPurchase currencyPurchase = new CurrencyPurchase(1L, "PLN", "buying",
                100.0, 1.1, 110.0, null);
        CurrencyPurchaseDto currencyPurchaseDto = new CurrencyPurchaseDto(1L, "PLN", "buying",
                100.0, 1.1, 110.0, null);

        when(dbService.getCurrencyPurchaseByCode("PLN")).thenReturn(currencyPurchase);
        when(mapper.mapToCurrencyPurchaseDto(currencyPurchase)).thenReturn(currencyPurchaseDto);

        // When & Then
        ResponseEntity<CurrencyPurchaseDto> responseEntity = controller.getCurrencyPurchaseByCode("PLN");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        CurrencyPurchaseDto currencyPurchaseDtoTest = responseEntity.getBody();
        assertEquals("PLN", currencyPurchaseDtoTest.getCurrencyCode());
        assertEquals(1.1, currencyPurchaseDtoTest.getPurchaseRate());
        assertEquals("buying", currencyPurchaseDtoTest.getOperationType());
        assertEquals(110.0, currencyPurchaseDtoTest.getPurchaseAmount());
    }

    @Test
    void createCurrencyPurchaseTest() {
        // Given
        CurrencyPurchase currencyPurchase = new CurrencyPurchase(1L, "PLN", "buying",
                100.0, 1.1, 110.0, null);
        CurrencyPurchaseDto currencyPurchaseDto = new CurrencyPurchaseDto(1L, "PLN", "buying",
                100.0, 1.1, 110.0, null);

        when(mapper.mapToCurrencyPurchase(currencyPurchaseDto)).thenReturn(currencyPurchase);
        when(dbService.saveCurrencyPurchase(currencyPurchase)).thenReturn(currencyPurchase);

        // When & Then
        ResponseEntity<Void> responseEntity = controller.createCurrencyPurchase(currencyPurchaseDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}