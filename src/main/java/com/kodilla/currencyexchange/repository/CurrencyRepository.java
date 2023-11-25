package com.kodilla.currencyexchange.repository;

import com.kodilla.currencyexchange.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    List<Currency> findAll();
    Currency save(Currency currency);
}
