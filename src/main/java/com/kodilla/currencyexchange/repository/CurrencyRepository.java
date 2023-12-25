package com.kodilla.currencyexchange.repository;

import com.kodilla.currencyexchange.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    @Override
    List<Currency> findAll();

    @Override
    Currency save(Currency currency);

    @Override
    Optional<Currency> findById(Long id);

    Optional<Currency> findByCode(String code);

}
