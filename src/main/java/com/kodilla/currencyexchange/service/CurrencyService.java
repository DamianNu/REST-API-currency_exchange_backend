package com.kodilla.currencyexchange.service;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.domain.CurrencyDto;
import com.kodilla.currencyexchange.webclient.currency.CurrencyClient;
import com.kodilla.currencyexchange.webclient.currency.dto.CurDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyClient currencyClient;
    private final DbService dbService;

    public void updateCurrency() {
        List<String> codes = new ArrayList<>();
        codes.add("EUR");
        codes.add("CHF");
        codes.add("USD");
        codes.add("GBP");
        codes.add("NOK");
        codes.add("SEK");
        codes.add("DKK");
        codes.add("CAD");
        codes.add("JPY");
        codes.add("CZK");
        for (int i = 0; i < codes.size(); i++) {
            dbService.deleteCurrency(i + 1l);
            CurDto cur = currencyClient.getCurrencyForCode(codes.get(i));
            Currency currency = new Currency(i + 1l, codes.get(i), cur.getCurrency(), cur.getRates().get(0).getMid()*0.98,cur.getRates().get(0).getMid()*1.02);
            dbService.saveCurrency(currency);
        }
    }
}
