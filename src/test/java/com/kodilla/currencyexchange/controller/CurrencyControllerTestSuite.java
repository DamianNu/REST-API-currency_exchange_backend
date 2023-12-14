package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.domain.CurrencyDto;
import com.kodilla.currencyexchange.mapper.CurrencyMapper;
import com.kodilla.currencyexchange.service.DbServiceCurrency;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

//@SpringJUnitWebConfig
//@WebMvcTest(CurrencyController.class)
@SpringBootTest

class CurrencyControllerTestSuite {

//    @Autowired
//    private MockMvc mockMvc;

    @MockBean
    private DbServiceCurrency dbServiceCurrency;

    @MockBean
    private CurrencyMapper currencyMapper;

    @Test
    void shouldFetchCurrencyTest() throws Exception {
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

        when(dbServiceCurrency.getAllCurrency()).thenReturn(currencyList);
        when(currencyMapper.mapToCurrencyDtoList(currencyList)).thenReturn(currencyListDto);
        //When & Then
//        mockMvc
//                .perform(MockMvcRequestBuilders
//                        .get("/currency")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));

    }
}