package com.kodilla.currencyexchange.service.db;

import com.kodilla.currencyexchange.domain.LoginAttempt;
import com.kodilla.currencyexchange.repository.LoginAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbServiceLoginAttempt {
    private final LoginAttemptRepository repository;

    public LoginAttempt saveLoginAttempt(final LoginAttempt loginAttempt) {
        return repository.save(loginAttempt);
    }
}
