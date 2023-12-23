package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.User;
import com.kodilla.currencyexchange.domain.UserValid;
import com.kodilla.currencyexchange.mapper.UserMapper;
import com.kodilla.currencyexchange.repository.UserRepository;
import com.kodilla.currencyexchange.service.DbServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper;

    private final DbServiceUser serviceUser;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
        User user1 = userRepository.findByName(user.getName());
        if(user1 != null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } else {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public ResponseEntity<UserValid> getUserByName(@PathVariable String userName) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserValid(serviceUser.getUserByName(userName)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserValid>> getAllUsers() {
        List<User> userValidList = serviceUser.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserValidList(userValidList));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/email/{userEmail}")
    public ResponseEntity<UserValid> getUserByEmail(@PathVariable String userEmail) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserValid(serviceUser.getUserByEmail(userEmail)));
    }
}