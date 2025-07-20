package com.william.chat.mult.controller;

import com.william.chat.mult.dto.UserDto;
import com.william.chat.mult.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> novoUsuario(@RequestBody UserDto userDto){
        return userService.novoUsuario(userDto);
    }

    @GetMapping
    public ResponseEntity<?> buscarUsuario(@RequestParam(name = "username") String username){
        return userService.buscarUsuario(username);
    }
}
