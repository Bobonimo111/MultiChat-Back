package com.william.chat.mult.service;

import com.william.chat.mult.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public ResponseEntity<?> novoUsuario(UserDto userDto){
        return null;
    }

    public ResponseEntity<?> buscarUsuario(String username){
        return null;
    }
}
