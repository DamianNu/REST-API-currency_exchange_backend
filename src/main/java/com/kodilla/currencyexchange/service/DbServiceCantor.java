package com.kodilla.currencyexchange.service;

import com.kodilla.currencyexchange.controller.CantorNotFoundException;
import com.kodilla.currencyexchange.domain.Cantor;
import com.kodilla.currencyexchange.repository.CantorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbServiceCantor {
    private final CantorRepository repository;

    public List<Cantor> getAllCantors() {
        return repository.findAll();
    }

    public Cantor getCantor(final Long cantorId) throws CantorNotFoundException {
        return repository.findById(cantorId).orElseThrow(CantorNotFoundException::new);
    }

//    public Cantor getCantorByCode(final String currencyCode) throws CurrencyNotFoundException {
//        return repository.findByCode(currencyCode).orElseThrow(CurrencyNotFoundException::new);
//    }

    public Cantor saveCantor(final Cantor cantor) {
        return repository.save(cantor);
    }

    public void deleteCantor(final Long cantorId) {
        repository.deleteById(cantorId);
    }
}
