package com.kodilla.currencyexchange.repository;

import com.kodilla.currencyexchange.domain.Cantor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CantorRepository extends CrudRepository<Cantor, Long> {
    @Override
    List<Cantor> findAll();

    @Override
    Cantor save(Cantor cantor);

    @Override
    Optional<Cantor> findById(Long id);
}
