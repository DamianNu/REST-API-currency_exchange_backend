package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.user.User;
import com.kodilla.currencyexchange.domain.user.UserDto;
import com.kodilla.currencyexchange.exception.UserNotFoundException;
import com.kodilla.currencyexchange.mapper.UserMapper;
import com.kodilla.currencyexchange.service.db.DbServiceUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTestSuite {

    @Autowired
    private UserController controller;

    @MockBean
    private DbServiceUser dbService;

    @MockBean
    private UserMapper mapper;

    @Test
    void shouldFetchUsersTest() {
        // Given
        List<User> userList = new ArrayList<>();
        User user1 = new User(1L,"user1","user1@gmail.com","password1");
        User user2 = new User(2L,"user2","user2@gmail.com","password2");
        User user3 = new User(3L,"user3","user3@gmail.com","password3");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        List<UserDto> userValidList = new ArrayList<>();
        UserDto userValid1 = new UserDto(1L,"user1","user1@gmail.com","password1",null);
        UserDto userValid2 = new UserDto(2L,"user2","user2@gmail.com","password2",null);
        UserDto userValid3 = new UserDto(3L,"user3","user3@gmail.com","password3",null);
        userValidList.add(userValid1);
        userValidList.add(userValid2);
        userValidList.add(userValid3);

        when(dbService.getAllUsers()).thenReturn(userList);
        when(mapper.mapToUserValidList(userList)).thenReturn(userValidList);
        //When & Then
        ResponseEntity<List<UserDto>> listResponseEntity = controller.getAllUsers();

        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
        List<UserDto> userValidListTest = listResponseEntity.getBody();
        assertNotNull(userValidListTest);
        assertEquals("user1", userValidListTest.get(0).getName());
        assertEquals("user2@gmail.com", userValidListTest.get(1).getEmail());
        assertEquals("user3", userValidListTest.get(2).getName());
    }

    @Test
    void shouldFetchUserByNameTest() throws UserNotFoundException {
        // Given
        User user1 = new User(1L,"user1","user1@gmail.com","password1");
        UserDto userValid1 = new UserDto(1L,"user1","user1@gmail.com","password1",null);

        when(dbService.getUserByName("user1")).thenReturn(user1);
        when(mapper.mapToUserValid(user1)).thenReturn(userValid1);

        // When & Then
        ResponseEntity<UserDto> responseEntity = controller.getUserByName("user1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        UserDto userValidTest = responseEntity.getBody();
        assertEquals("user1", userValidTest.getName());
        assertEquals("user1@gmail.com", userValidTest.getEmail());
    }

    @Test
    void shouldFetchUserByEmailTest() throws UserNotFoundException {
        // Given
        User user1 = new User(1L,"user1","user1@gmail.com","password1");
        UserDto userValid1 = new UserDto(1L,"user1","user1@gmail.com","password1",null);

        when(dbService.getUserByEmail("user1@gmail.com")).thenReturn(user1);
        when(mapper.mapToUserValid(user1)).thenReturn(userValid1);

        // When & Then
        ResponseEntity<UserDto> responseEntity = controller.getUserByEmail("user1@gmail.com");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        UserDto userValidTest = responseEntity.getBody();
        assertEquals("user1", userValidTest.getName());
        assertEquals("user1@gmail.com", userValidTest.getEmail());
    }

    @Test
    void deleteUserByIdTest() {
        // When & Then
        ResponseEntity<Void> responseEntity = controller.deleteUserById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}