package com.kodilla.currencyexchange.service;

import com.kodilla.currencyexchange.controller.CurrencyNotFoundException;
import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {
    private final CurrencyRepository repository;

    public List<Currency> getAllCurrency() {
        return repository.findAll();
    }

    public Currency getCurrency(final Long currencyId) throws CurrencyNotFoundException {
        return repository.findById(currencyId).orElseThrow(CurrencyNotFoundException::new);
    }

    public Currency saveCurrency(final Currency currency) {
        return repository.save(currency);
    }

    public void deleteCurrency(final Long currencyId) {
        repository.deleteById(currencyId);
    }
}
