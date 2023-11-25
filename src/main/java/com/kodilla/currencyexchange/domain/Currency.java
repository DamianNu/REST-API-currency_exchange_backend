package com.kodilla.currencyexchange.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @GeneratedValue
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "currency")
    private String currency;

}
