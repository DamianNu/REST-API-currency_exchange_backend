package com.kodilla.currencyexchange.mapper;

import com.kodilla.currencyexchange.domain.user.User;
import com.kodilla.currencyexchange.domain.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {
    public UserDto mapToUserValid(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getDate()
        );
    }

    public UserDto mapToUserValidByEmail(final User user) {
        return new UserDto(
                user.getName(),
                user.getEmail()
        );
    }

    public User mapToUser(final UserDto userValid) {
        return new User(
                userValid.getId(),
                userValid.getName(),
                userValid.getEmail(),
                userValid.getPassword(),
                userValid.getDate()
        );
    }

    public List<UserDto> mapToUserValidList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserValid)
                .toList();
    }
}