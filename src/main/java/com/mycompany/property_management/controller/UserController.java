package com.mycompany.property_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDto) {
        userDto = userService.register(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDto) {
        userDto = userService.login(userDto.getOwnerEmail(), userDto.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // Aspect OrientedProgramming is a way of programmingwhich helps
    // us take out the commonfunctionalities, and put it in oneplace.
}
