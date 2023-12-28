package com.kodilla.currencyexchange.domain.currencyPurchase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name = "CURRENCIES_PURCHASE")
public class CurrencyPurchase {
    private Long id;
    private String currencyCode;
    private String operationType;
    private double amount;
    private double purchaseRate;
    private double purchaseAmount;
    private LocalDateTime purchaseDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "CODE")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @Column(name = "TYPE_OPERATION")
    public String getOperationType() {
        return operationType;
    }

    @Column(name = "AMOUNT")
    public double getAmount() {
        return amount;
    }

    @Column(name = "PURCHASE_RATE")
    public double getPurchaseRate() {
        return purchaseRate;
    }

    @Column(name = "AMOUNT_PURCHASE")
    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    @Column(name = "DATE")
    public LocalDateTime getPurchaseDate() {
        LocalDateTime time = LocalDateTime.now();
        return time;
    }
}
