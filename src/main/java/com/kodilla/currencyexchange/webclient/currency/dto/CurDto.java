package com.kodilla.currencyexchange.webclient.currency.dto;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CurDto {
    public String table;
    public String currency;
    public String code;
    public ArrayList<Rate> rates;

}
