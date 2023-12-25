package com.kodilla.currencyexchange.mapper;

import com.kodilla.currencyexchange.domain.User;
import com.kodilla.currencyexchange.domain.UserValid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {
    public UserValid mapToUserValid(final User user) {
        return new UserValid(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getDate()
        );
    }

    public UserValid mapToUserValidByEmail(final User user) {
        return new UserValid(
                user.getName(),
                user.getEmail()
        );
    }

    public User mapToUser(final UserValid userValid) {
        return new User(
                userValid.getId(),
                userValid.getName(),
                userValid.getEmail(),
                userValid.getPassword(),
                userValid.getDate()
        );
    }

    public List<UserValid> mapToUserValidList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserValid)
                .toList();
    }
}