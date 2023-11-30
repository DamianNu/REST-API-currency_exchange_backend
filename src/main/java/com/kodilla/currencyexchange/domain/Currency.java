package com.kodilla.currencyexchange.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "currency")
public class Currency {
    @Id
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "currency")
    private String currency;

    @Column(name = "buying")
    private double buying;

    @Column(name = "selling")
    private double selling;

}
