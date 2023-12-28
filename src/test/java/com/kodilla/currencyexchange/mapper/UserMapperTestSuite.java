package com.kodilla.currencyexchange.mapper;

import com.kodilla.currencyexchange.domain.user.User;
import com.kodilla.currencyexchange.domain.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTestSuite {

    @Autowired
    UserMapper userMapper;

    @Test
    void mapToUserTest() {
        //Given
        UserDto userValid = new UserDto(100L, "user1", "user1@o2.pl", "password", null);

        //When
        User user = userMapper.mapToUser(userValid);

        //Then
        assertEquals(100L, user.getId());
        assertEquals("user1", user.getName());
        assertEquals("user1@o2.pl", user.getEmail());
    }

    @Test
    void mapToUserValidTest() {
        //Given
        User user = new User(100L, "user1", "user1@o2.pl", "password", null);

        //When
        UserDto userValid = userMapper.mapToUserValid(user);

        //Then
        assertEquals(100L, userValid.getId());
        assertEquals("user1", userValid.getName());
        assertEquals("user1@o2.pl", userValid.getEmail());
    }

    @Test
    void mapToUserValidListTest() {
        //Given
        User user1 = new User(100L, "user1", "user1@o2.pl", "password", null);
        User user2 = new User(200L, "user2", "user2@o2.pl", "password", null);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        //When
        List<UserDto> userValidList = userMapper.mapToUserValidList(userList);

        //Then
        assertEquals(2, userValidList.size());
        assertEquals(100L, userValidList.get(0).getId());
        assertEquals(200L, userValidList.get(1).getId());
        assertEquals("user1", userValidList.get(0).getName());
        assertEquals("user2@o2.pl", userValidList.get(1).getEmail());
    }
}
