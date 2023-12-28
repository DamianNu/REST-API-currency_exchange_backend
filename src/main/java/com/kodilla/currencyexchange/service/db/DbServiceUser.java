package com.kodilla.currencyexchange.service.db;

import com.kodilla.currencyexchange.exception.UserNotFoundException;
import com.kodilla.currencyexchange.domain.user.User;
import com.kodilla.currencyexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbServiceUser {
    private final UserRepository userRepository;

    public User getUserByName(final String name) throws UserNotFoundException {
        return userRepository.findByName(name);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(final String email) throws UserNotFoundException {
        return userRepository.findByEmail(email);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }
}
