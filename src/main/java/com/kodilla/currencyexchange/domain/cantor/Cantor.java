package com.kodilla.currencyexchange.domain.cantor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name = "CANTORS")
public class Cantor {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String openingHours;

    @Id
    @Column(name = "ID_CANTOR")
    public Long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    @Column(name = "OPENING_HOURS")
    public String getOpeningHours() {
        return openingHours;
    }
}
