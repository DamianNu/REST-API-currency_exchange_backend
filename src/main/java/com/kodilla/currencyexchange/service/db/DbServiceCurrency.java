package com.kodilla.currencyexchange.service.db;

import com.kodilla.currencyexchange.exception.CurrencyNotFoundException;
import com.kodilla.currencyexchange.domain.currency.Currency;
import com.kodilla.currencyexchange.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbServiceCurrency {
    private final CurrencyRepository repository;

    public List<Currency> getAllCurrency() {
        return repository.findAll();
    }

    public Currency getCurrency(final Long currencyId) throws CurrencyNotFoundException {
        return repository.findById(currencyId).orElseThrow(CurrencyNotFoundException::new);
    }

    public Currency getCurrencyByCode(final String currencyCode) throws CurrencyNotFoundException {
        return repository.findByCode(currencyCode).orElseThrow(CurrencyNotFoundException::new);
    }

    public Currency saveCurrency(final Currency currency) {
        return repository.save(currency);
    }

    public void deleteCurrency(final Long currencyId) {
        repository.deleteById(currencyId);
    }
}
