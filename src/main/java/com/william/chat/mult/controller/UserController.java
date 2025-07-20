package com.william.chat.mult.controller;

import com.william.chat.mult.dto.UserDto;
import com.william.chat.mult.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> novoUsuario(UserDto userDto){
        return userService.novoUsuario(userDto);
    }

    @GetMapping
    public ResponseEntity<?> buscarUsuario(String username){
        return userService.buscarUsuario(username);
    }
}
