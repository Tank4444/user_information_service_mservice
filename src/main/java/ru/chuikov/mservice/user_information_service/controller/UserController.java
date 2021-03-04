package ru.chuikov.mservice.user_information_service.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chuikov.mservice.user_information_service.entity.User;
import ru.chuikov.mservice.user_information_service.service.UserService;

@RestController
@RequestMapping( value = "/user", produces ="application/json")
@Log
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping(path = "/add")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody User user){
        log.info("Add user request with User info ( "+ user.toString() +" )");
        return userService.getUserByUsername(user.getUsername())
                .map(checkUser -> new ResponseEntity<>(HttpStatus.BAD_REQUEST))
                .orElse(userService.getUserByEmail(user.getEmail())
                        .map(checkUser -> new ResponseEntity<>(HttpStatus.BAD_REQUEST))
                            .orElseGet(()->{
                                userService.add(user);
                                return new ResponseEntity<>(HttpStatus.OK);
                            })
                );
    }

    @PostMapping(path = "/get/id/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id){
        log.info("Request User info by Id ( "+ id +" )");
        return userService.getById(id).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/get/username/{username}")
    public ResponseEntity getUserByUsername(@PathVariable("username") String username){
        log.info("Request User info by Username ( "+ username +" )");
        return userService.getUserByUsername(username).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping(path = "/get/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable("email") String email){
        log.info("Request User info by Email ( "+ email +" )");
        return userService.getUserByEmail(email).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
