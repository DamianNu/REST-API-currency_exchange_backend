package com.kodilla.currencyexchange.service.db;

import com.kodilla.currencyexchange.exception.CurrencyPurchaseNotFoundException;
import com.kodilla.currencyexchange.domain.currencyPurchase.CurrencyPurchase;
import com.kodilla.currencyexchange.repository.CurrencyPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbServiceCurrencyPurchase {
    private final CurrencyPurchaseRepository repository;

    public List<CurrencyPurchase> getAllCurrencyPurchase() {
        return repository.findAll();
    }

    public CurrencyPurchase getCurrencyPurchase(final Long currencyPurchaseId) throws CurrencyPurchaseNotFoundException {
        return repository.findById(currencyPurchaseId).orElseThrow(CurrencyPurchaseNotFoundException::new);
    }

    public CurrencyPurchase getCurrencyPurchaseByCode(final String currencyPurchaseCode) throws CurrencyPurchaseNotFoundException {
        return repository.findByCurrencyCode(currencyPurchaseCode).orElseThrow(CurrencyPurchaseNotFoundException::new);
    }

    public CurrencyPurchase saveCurrencyPurchase(final CurrencyPurchase currencyPurchase) {
        return repository.save(currencyPurchase);
    }
}
