package com.kodilla.currencyexchange.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name = "currency")
public class Currency {
    private Long id;

    private String code;

    private String currency;

    private double mid;

    private double buying;

    private double selling;

    @Id
    @Column(name = "ID_CURRENCY")
    public Long getId() {
        return id;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    @Column(name = "CURRENCY")
    public String getCurrency() {
        return currency;
    }

    @Column(name = "MID", scale = 4)
    public double getMid() {
        return mid;
    }

    @Column(name = "BUYING", scale = 4)
    public double getBuying() {
        return buying;
    }

    @Column(name = "SELLING", scale = 4)
    public double getSelling() {
        return selling;
    }
}
