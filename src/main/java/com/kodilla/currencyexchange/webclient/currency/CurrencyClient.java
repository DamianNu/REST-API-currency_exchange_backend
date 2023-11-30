package com.kodilla.currencyexchange.webclient.currency;

import com.kodilla.currencyexchange.domain.CurrencyDto;
import com.kodilla.currencyexchange.webclient.currency.dto.CurDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyClient {
    public static final String CURRENCY_URL = "https://api.nbp.pl/api/exchangerates/rates/A/";
    public static final String FORMAT_JSON = "/?format=json";
    private RestTemplate restTemplate = new RestTemplate();

    public CurDto getCurrencyForCode(String code) {
        return restTemplate.getForObject(CURRENCY_URL + code + FORMAT_JSON,
                CurDto.class);


    }
}
