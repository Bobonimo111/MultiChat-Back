package com.william.chat.mult.service;

import com.william.chat.mult.dto.UserDto;
import com.william.chat.mult.dto.UserSavedDto;
import com.william.chat.mult.model.UserModel;
import com.william.chat.mult.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> novoUsuario(UserDto userDto){
        HashMap<String,Object> jsonResponse = new HashMap<>();
        jsonResponse.put("value",null);
        ArrayList<String> required = new ArrayList<>();
        if(userDto.username() == null || userDto.username().isEmpty()){
            required.add("username");
        }
        if(userDto.password() == null || userDto.password().isEmpty()){
            required.add("password");
        }
        if(userDto.email() == null || userDto.email().isEmpty()){
            required.add("email");
        }

        if(!required.isEmpty()){
            jsonResponse.put("required",required);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(jsonResponse);
        }

        UserModel userModel = UserModel.builder()
                .password(userDto.password())
                .username(userDto.username())
                .email(userDto.email())
                .build();

        UserModel savedUser =  userRepository.save(userModel);

        jsonResponse.put("value",new UserSavedDto(
                userModel.getId(),
                userModel.getUsername(),
                null,
                userModel.getEmail()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse);
    }

    public ResponseEntity<?> buscarUsuario(String username){
        HashMap<String,Object> jsonResponse = new HashMap<>();
        ArrayList<String> required = new ArrayList<>();
        jsonResponse.put("value",null);
        if(username == null || username.isEmpty()){
            required.add("username");
        }
        //Retorna o json com as dados de obrigatorios.
        if(!required.isEmpty()){
            jsonResponse.put("required",required);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
        }

        List<UserModel> userModelList = userRepository.findByUsernameContaining(username);

        if(userModelList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(jsonResponse);
        }

        //Convertendo a lista do tipo UserModel para o tipo UserSavedDto.
        jsonResponse.put("value",userModelList
                .stream()
                .map(
                        model -> new UserSavedDto(model.getId(),model.getUsername(),null, model.getEmail())
                )
                .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }
}
