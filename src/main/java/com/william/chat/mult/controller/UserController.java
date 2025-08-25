package com.william.chat.mult.controller;

import com.william.chat.mult.dto.NewUserDto;
import com.william.chat.mult.dto.ReturnUserDto;
import com.william.chat.mult.dto.UserDto;
import com.william.chat.mult.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //validar os dados
    @PostMapping
    public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid NewUserDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.createNewUser(userDto.username(),userDto.password(),userDto.email()));
    }

    @GetMapping("/containing")
    public ResponseEntity<List<UserDto>> findByUsernameContaining(@RequestParam(name = "username") String username){
        List<UserDto> list = userService.findByUsernameContaining(username);

        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUserById(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(userDto));
    }

    @GetMapping("usersByChat/{id}")
    public List<ReturnUserDto> getUserByChatId(@PathVariable(name = "id",required = true) UUID chatId){
        return userService.getUserByChatId(chatId);
    }
}
