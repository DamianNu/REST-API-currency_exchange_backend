package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.domain.CurrencyDto;
import com.kodilla.currencyexchange.mapper.CurrencyMapper;
import com.kodilla.currencyexchange.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final DbService service;
    private final CurrencyMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CurrencyDto>> getCurrency() {
        List<Currency> currencyList = service.getAllCurrency();
        return ResponseEntity.ok(mapper.mapToCurrencyDtoList(currencyList));
    }

    @RequestMapping(method = RequestMethod.GET,value = "{currencyId}")
    public CurrencyDto getCurrency(@PathVariable Long currencyId) {
        return new CurrencyDto(1L, "USD", "dolar amerykański");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{currencyId}")
    public void deleteCurrency(@PathVariable Long currencyId) {

    }

    @RequestMapping(method = RequestMethod.POST)
    public CurrencyDto updateCurrency(@RequestBody CurrencyDto currencyDto) {
        return new CurrencyDto(2L, "USD2", "dolar amerykański2");
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createCurrency(@RequestBody CurrencyDto currencyDto){
        Currency currency = mapper.mapToCurrency(currencyDto);
        service.saveCurrency(currency);
    }

}
