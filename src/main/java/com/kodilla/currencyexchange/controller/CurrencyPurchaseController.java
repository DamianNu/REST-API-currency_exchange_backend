package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.exception.CurrencyPurchaseNotFoundException;
import com.kodilla.currencyexchange.domain.currencyPurchase.CurrencyPurchase;
import com.kodilla.currencyexchange.domain.currencyPurchase.CurrencyPurchaseDto;
import com.kodilla.currencyexchange.mapper.CurrencyPurchaseMapper;
import com.kodilla.currencyexchange.repository.CurrencyPurchaseRepository;
import com.kodilla.currencyexchange.service.db.DbServiceCurrencyPurchase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class CurrencyPurchaseController {
    private final DbServiceCurrencyPurchase service;
    private final CurrencyPurchaseMapper mapper;
    private final CurrencyPurchaseRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CurrencyPurchaseDto>> getCurrencyPurchase() {
        List<CurrencyPurchase> currencyPurchaseList = service.getAllCurrencyPurchase();
        return ResponseEntity.ok(mapper.mapToCurrencyPurchaseDtoList(currencyPurchaseList));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{currencyPurchaseId}")
    public ResponseEntity<CurrencyPurchaseDto> getCurrencyPurchaseById(@PathVariable Long currencyPurchaseId) throws CurrencyPurchaseNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyPurchaseDto(service.getCurrencyPurchase(currencyPurchaseId)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CurrencyPurchaseDto> updateCurrencyPurchase(@RequestBody CurrencyPurchaseDto currencyPurchaseDto) {
        CurrencyPurchase currencyPurchase = mapper.mapToCurrencyPurchase(currencyPurchaseDto);
        CurrencyPurchase saveCurrencyPurchase = service.saveCurrencyPurchase(currencyPurchase);
        return ResponseEntity.ok(mapper.mapToCurrencyPurchaseDto(saveCurrencyPurchase));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createCurrencyPurchase(@RequestBody CurrencyPurchaseDto currencyPurchaseDto) {
        CurrencyPurchase currencyPurchase = mapper.mapToCurrencyPurchase(currencyPurchaseDto);
        repository.save(currencyPurchase);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/code/{currencyPurchaseCode}")
    public ResponseEntity<CurrencyPurchaseDto> getCurrencyPurchaseByCode(@PathVariable String currencyPurchaseCode) throws CurrencyPurchaseNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyPurchaseDto(service.getCurrencyPurchaseByCode(currencyPurchaseCode)));
    }
}
