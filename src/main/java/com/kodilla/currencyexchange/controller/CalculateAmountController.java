package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.exception.CurrencyNotFoundException;
import com.kodilla.currencyexchange.domain.currency.Currency;
import com.kodilla.currencyexchange.request.Amount;
import com.kodilla.currencyexchange.request.CalculateAmount;
import com.kodilla.currencyexchange.service.AmountService;
import com.kodilla.currencyexchange.service.db.DbServiceCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calculate-amount")
@RequiredArgsConstructor
public class CalculateAmountController {
    private final DbServiceCurrency service;
    private final AmountService amountService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Amount> calculateAmount(@RequestBody CalculateAmount calculateAmount) throws CurrencyNotFoundException {
        double amount = calculateAmount.getAmount();
        Amount result;
        if (calculateAmount.getAmount() < 0) {
            return ResponseEntity.notFound().build();
        } else {
            Currency currency = service.getCurrencyByCode(calculateAmount.getCode());
            if (calculateAmount.getType().equals("buying")) {
                result = amountService.calculateBuyingCurrency(currency, amount);
            } else if (calculateAmount.getType().equals("selling")) {
                result = amountService.calculateSellingCurrency(currency, amount);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(result);
    }
}
