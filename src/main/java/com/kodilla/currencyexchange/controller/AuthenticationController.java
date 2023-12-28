package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.LoginAttempt;
import com.kodilla.currencyexchange.domain.user.User;
import com.kodilla.currencyexchange.repository.UserRepository;
import com.kodilla.currencyexchange.request.LoginRequest;
import com.kodilla.currencyexchange.service.db.DbServiceLoginAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DbServiceLoginAttempt service;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByName(loginRequest.getName());
        if (user == null) {
            service.saveLoginAttempt(new LoginAttempt(loginRequest.getName(),false));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Boolean isPasswordValid = BCrypt.checkpw(loginRequest.getPassword(), user.getPassword());
        if (!isPasswordValid) {
            service.saveLoginAttempt(new LoginAttempt(loginRequest.getName(),false));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        service.saveLoginAttempt(new LoginAttempt(loginRequest.getName(), true));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
