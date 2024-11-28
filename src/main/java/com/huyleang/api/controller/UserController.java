package com.huyleang.api.controller;

import com.huyleang.api.entities.UserEntity;
import com.huyleang.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/huyleang")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        var lists = userService.getAllUsers();
        return lists.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
        try {
            var response = userService.saveUser(user);
            if (response == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        } catch (Exception e) {
            System.err.println("Exception save user : " + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

}