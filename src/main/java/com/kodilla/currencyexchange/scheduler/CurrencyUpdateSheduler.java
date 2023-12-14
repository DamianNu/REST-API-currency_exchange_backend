package com.kodilla.currencyexchange.scheduler;

import com.kodilla.currencyexchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyUpdateSheduler {

    private final CurrencyService service;

    @Scheduled(fixedDelay = 3000000)
    public void updateCurrency() {
        service.updateCurrency();
    }
}