package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.domain.CurrencyDto;
import com.kodilla.currencyexchange.mapper.CurrencyMapper;
import com.kodilla.currencyexchange.service.CurrencyService;
import com.kodilla.currencyexchange.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final DbService service;
    private final CurrencyMapper mapper;
    private final CurrencyService currencyService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CurrencyDto>> getCurrency() {
        List<Currency> currencyList = service.getAllCurrency();
        return ResponseEntity.ok(mapper.mapToCurrencyDtoList(currencyList));
    }

    @RequestMapping(method = RequestMethod.GET, value = "{currencyId}")
    public CurrencyDto getCurrency(@PathVariable Long currencyId) throws CurrencyNotFoundException {
        return mapper.mapToCurrencyDto(service.getCurrency(currencyId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{currencyId}")
    public void deleteCurrency(@PathVariable Long currencyId) {
        service.deleteCurrency(currencyId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CurrencyDto updateCurrency(@RequestBody CurrencyDto currencyDto) {
        Currency currency = mapper.mapToCurrency(currencyDto);
        Currency saveCurrency = service.saveCurrency(currency);
        return mapper.mapToCurrencyDto(saveCurrency);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createCurrency(@RequestBody CurrencyDto currencyDto) {
        Currency currency = mapper.mapToCurrency(currencyDto);
        service.saveCurrency(currency);
    }

    @GetMapping("/up")
    public void upCurrency() {
        currencyService.updateCurrency();
    }
}
