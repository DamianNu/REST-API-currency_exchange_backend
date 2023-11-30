package com.kodilla.currencyexchange.mapper;

import com.kodilla.currencyexchange.domain.Currency;
import com.kodilla.currencyexchange.domain.CurrencyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyMapper {
    public Currency mapToCurrency(final CurrencyDto currencyDto) {
        return new Currency(
                currencyDto.getId(),
                currencyDto.getCode(),
                currencyDto.getCurrency(),
                currencyDto.getBuying(),
                currencyDto.getSelling()
        );
    }

    public CurrencyDto mapToCurrencyDto(final Currency currency) {
        return new CurrencyDto(
                currency.getId(),
                currency.getCode(),
                currency.getCurrency(),
                currency.getBuying(),
                currency.getSelling()
        );
    }

    public List<CurrencyDto> mapToCurrencyDtoList(final List<Currency> currencyList) {
        return currencyList.stream()
                .map(this::mapToCurrencyDto)
                .toList();
    }
}
