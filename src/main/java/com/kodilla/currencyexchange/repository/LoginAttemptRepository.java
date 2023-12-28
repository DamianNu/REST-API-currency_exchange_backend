package com.kodilla.currencyexchange.repository;

import com.kodilla.currencyexchange.domain.LoginAttempt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAttemptRepository extends CrudRepository<LoginAttempt, Long> {
    @Override
    LoginAttempt save(LoginAttempt loginAttempt);
    LoginAttempt findByLogin(String login);
}
