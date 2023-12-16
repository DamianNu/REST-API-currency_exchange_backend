package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.User;
import com.kodilla.currencyexchange.repository.UserRepository;
import com.kodilla.currencyexchange.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByName(loginRequest.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Boolean isPasswordValid = BCrypt.checkpw(loginRequest.getPassword(), user.getPassword());
        if (!isPasswordValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
