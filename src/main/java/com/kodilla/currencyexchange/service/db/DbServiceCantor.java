package com.kodilla.currencyexchange.service.db;

import com.kodilla.currencyexchange.exception.CantorNotFoundException;
import com.kodilla.currencyexchange.domain.cantor.Cantor;
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

    public Cantor getCantorByName(final String name) throws CantorNotFoundException {
        return repository.findByName(name).orElseThrow(CantorNotFoundException::new);
    }

    public List<Cantor> getCantorByCity(final String city) {
        return repository.findAllByCity(city);
    }

    public Cantor saveCantor(final Cantor cantor) {
        return repository.save(cantor);
    }

    public void deleteCantor(final Long cantorId) {
        repository.deleteById(cantorId);
    }
}
