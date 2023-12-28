package com.kodilla.currencyexchange.mapper;

import com.kodilla.currencyexchange.domain.currencyPurchase.CurrencyPurchase;
import com.kodilla.currencyexchange.domain.currencyPurchase.CurrencyPurchaseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyPurchaseMapper {
    public CurrencyPurchase mapToCurrencyPurchase(final CurrencyPurchaseDto currencyPurchaseDto) {
        return new CurrencyPurchase(
                currencyPurchaseDto.getId(),
                currencyPurchaseDto.getCurrencyCode(),
                currencyPurchaseDto.getOperationType(),
                currencyPurchaseDto.getAmount(),
                currencyPurchaseDto.getPurchaseRate(),
                currencyPurchaseDto.getPurchaseAmount(),
                currencyPurchaseDto.getPurchaseDate()
        );
    }

    public CurrencyPurchaseDto mapToCurrencyPurchaseDto(final CurrencyPurchase currencyPurchase) {
        return new CurrencyPurchaseDto(
                currencyPurchase.getId(),
                currencyPurchase.getCurrencyCode(),
                currencyPurchase.getOperationType(),
                currencyPurchase.getAmount(),
                currencyPurchase.getPurchaseRate(),
                currencyPurchase.getPurchaseAmount(),
                currencyPurchase.getPurchaseDate()
        );
    }

    public List<CurrencyPurchaseDto> mapToCurrencyPurchaseDtoList(final List<CurrencyPurchase> currencyPurchaseList) {
        return currencyPurchaseList.stream()
                .map(this::mapToCurrencyPurchaseDto)
                .toList();
    }
}
