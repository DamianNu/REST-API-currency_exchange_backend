package com.kodilla.currencyexchange.controller;

import com.kodilla.currencyexchange.domain.User;
import com.kodilla.currencyexchange.domain.UserValid;
import com.kodilla.currencyexchange.mapper.UserMapper;
import com.kodilla.currencyexchange.repository.UserRepository;
import com.kodilla.currencyexchange.service.DbServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper;

    private final DbServiceUser serviceUser;

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@Valid @RequestBody User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
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

//    @RequestMapping(method = RequestMethod.PUT)
//    public ResponseEntity<UserValid> updateUserEmail(@Valid @RequestBody UserValid userValid) {
//        User user = userRepository.findByName(userValid.getName());
//        User saveUser = new User(user.getId(),user.getName(),userValid.getEmail(),passwordEncoder.encode(user.getPassword());
//        serviceUser.saveUser(saveUser);
//        return ResponseEntity.ok(userMapper.mapToUserValid(saveUser));
//    }

//    @RequestMapping(method = RequestMethod.PUT)
//    public ResponseEntity<User> updateUser(@RequestBody User user) {
//
//        // Sprawdź, czy użytkownik jest zalogowany
//        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        // Sprawdź, czy użytkownik jest właścicielem rekordu
////        User currentUser = getCurrentUser();
////        if (id != currentUser.getId()) {
////            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
////        }
//
//        // Uaktualnij hasło
//        if (user.getPassword() != null) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//
//        // Uaktualnij adres e-mail
//        if (user.getEmail() != null) {
//            user.setEmail(userRepository.findByEmail(user.getEmail()).orElse(null));
//        }
//
//        // Zapisz użytkownika w bazie danych
//        userRepository.save(user);
//
//        // Zwróc odpowiedź HTTP
//        return ResponseEntity.status(HttpStatus.OK).body(user);
//    }
}