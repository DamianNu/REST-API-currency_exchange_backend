package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.domain.CurrencyDto;
import com.kodilla.currencyexchange.mapper.CurrencyMapper;
import com.kodilla.currencyexchange.service.CurrencyService;
import com.kodilla.currencyexchange.service.DbServiceCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final DbServiceCurrency service;
    private final CurrencyMapper mapper;
    private final CurrencyService currencyService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CurrencyDto>> getCurrency() {
        List<Currency> currencyList = service.getAllCurrency();
        return ResponseEntity.ok(mapper.mapToCurrencyDtoList(currencyList));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{currencyId}")
    public ResponseEntity<CurrencyDto> getCurrency(@PathVariable Long currencyId) throws CurrencyNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyDto(service.getCurrency(currencyId)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{currencyId}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long currencyId) {
        service.deleteCurrency(currencyId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CurrencyDto> updateCurrency(@RequestBody CurrencyDto currencyDto) {
        Currency currency = mapper.mapToCurrency(currencyDto);
        Currency saveCurrency = service.saveCurrency(currency);
        return ResponseEntity.ok(mapper.mapToCurrencyDto(saveCurrency));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createCurrency(@RequestBody CurrencyDto currencyDto) {
        Currency currency = mapper.mapToCurrency(currencyDto);
        service.saveCurrency(currency);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/code/{currencyCode}")
    public ResponseEntity<CurrencyDto> getCurrencyByCode(@PathVariable String currencyCode) throws CurrencyNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyDto(service.getCurrencyByCode(currencyCode)));
    }

    @GetMapping("/up")
    public ResponseEntity<Void> upCurrency() {
        currencyService.updateCurrency();
        return ResponseEntity.ok().build();
    }
}
