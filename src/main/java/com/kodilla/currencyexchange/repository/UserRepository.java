package com.kodilla.currencyexchange.repository;

import com.kodilla.currencyexchange.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
    @Override
    List<User> findAll();
    @Override
    User save(User user);
}