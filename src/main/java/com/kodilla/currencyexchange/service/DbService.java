package com.kodilla.currencyexchange.service;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private final CurrencyRepository repository;

    public List<Currency> getAllCurrency() {
        return repository.findAll();
    }

    public Optional<Currency> getCurrency(final Long currencyId) {
        return repository.findById(currencyId);
    }

    public Currency saveCurrency(final Currency currency) {
        return repository.save(currency);
    }

}
