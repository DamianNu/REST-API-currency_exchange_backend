package com.kodilla.currencyexchange.repository;

import com.kodilla.currencyexchange.domain.currencyPurchase.CurrencyPurchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyPurchaseRepository extends CrudRepository<CurrencyPurchase, Long> {
    @Override
    List<CurrencyPurchase> findAll();

    @Override
    CurrencyPurchase save(CurrencyPurchase currencyPurchase);

    @Override
    Optional<CurrencyPurchase> findById(Long id);

    Optional<CurrencyPurchase> findByCurrencyCode(String code);
}
