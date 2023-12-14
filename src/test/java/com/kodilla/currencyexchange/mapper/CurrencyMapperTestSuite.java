package com.kodilla.currencyexchange.mapper;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.domain.CurrencyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyMapperTestSuite {

    @Autowired
    CurrencyMapper currencyMapper;

    @Test
    void mapToCurrecnyTest() {

        //Given
        CurrencyDto currencyDto = new CurrencyDto(100L, "TEST", "test", 1.0, 2.0, 3.0);

        //When
        Currency currency = currencyMapper.mapToCurrency(currencyDto);

        //Then
        assertEquals(100L, currency.getId());
        assertEquals("TEST", currency.getCode());
        assertEquals("test", currency.getCurrency());
        assertEquals(1.0, currency.getMid());
        assertEquals(2.0, currency.getBuying());
        assertEquals(3.0, currency.getSelling());
    }

    @Test
    void mapToCurrecnyDtoTest() {

        //Given
        Currency currency = new Currency(101L, "TEST2", "test2", 11.0, 12.0, 13.0);

        //When
        CurrencyDto currencyDto = currencyMapper.mapToCurrencyDto(currency);

        //Then
        assertEquals(101L, currencyDto.getId());
        assertEquals("TEST2", currencyDto.getCode());
        assertEquals("test2", currencyDto.getCurrency());
        assertEquals(11.0, currencyDto.getMid());
        assertEquals(12.0, currencyDto.getBuying());
        assertEquals(13.0, currencyDto.getSelling());
    }

    @Test
    void mapToCurrecnyListTest() {

        //Given
        Currency currency1 = new Currency(101L, "TEST1", "test1", 1.0, 2.0, 3.0);
        Currency currency2 = new Currency(102L, "TEST2", "test2", 11.0, 22.0, 33.0);
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(currency1);
        currencyList.add(currency2);

        //When
        List<CurrencyDto> currencyDtoList = currencyMapper.mapToCurrencyDtoList(currencyList);

        //Then
        assertEquals(2, currencyDtoList.size());
        assertEquals(101L, currencyDtoList.get(0).getId());
        assertEquals(102L, currencyDtoList.get(1).getId());
        assertEquals("test1", currencyDtoList.get(0).getCurrency());
        assertEquals(22.0, currencyDtoList.get(1).getBuying());
    }
}