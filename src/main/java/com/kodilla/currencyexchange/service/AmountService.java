package com.kodilla.currencyexchange.service;

import com.kodilla.currencyexchange.domain.currency.Currency;
import com.kodilla.currencyexchange.request.Amount;
import org.springframework.stereotype.Service;

@Service
public class AmountService {

    public Amount calculateBuyingCurrency(Currency currency, double amount) {
        double result = currency.getBuying() * amount;
        return new Amount( Math.round(result * 100.0) / 100.0);
    }

    public Amount calculateSellingCurrency(Currency currency, double amount) {
        double result = currency.getSelling() * amount;
        return new Amount(Math.round(result * 100.0) / 100.0);
    }
}
