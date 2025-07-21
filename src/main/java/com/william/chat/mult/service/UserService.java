package com.william.chat.mult.service;

import com.william.chat.mult.dto.UserDto;
import com.william.chat.mult.model.UserModel;
import com.william.chat.mult.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Trabalhar com os dados
    public UserDto createNewUser(String username, String password, String email){

        UserModel userModel = UserModel.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        UserModel savedUser =  userRepository.save(userModel);

        return new UserDto(
                savedUser.getId(),
                savedUser.getUsername(),
                null,
                savedUser.getEmail());
    }

    public List<UserDto> findByUsernameContaining(String username){

        List<UserModel> userModelList = userRepository.findByUsernameContaining(username);

        if(userModelList.isEmpty()){
            return null;
        }
        //Convertendo a lista do tipo UserModel para o tipo UserSavedDto.
        List<UserDto> userSavedDtos =   userModelList
                .stream()
                .map(
                        model -> new UserDto(model.getId(),model.getUsername(),null, model.getEmail())
                )
                .collect(Collectors.toList());

        return userSavedDtos;
    }

    public UserDto updateUserById(UserDto userDto){
        Optional<UserModel> userOp = userRepository.findById(userDto.id());
        if(userOp.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ID NOT FOUND");
        }
        UserModel user = userOp.get();

        user.setUsername((!userDto.username().isBlank()? userDto.username() : user.getUsername()));
        user.setEmail((!userDto.email().isBlank()? userDto.email() : user.getEmail()));
        user.setPassword((!userDto.password().isBlank()? userDto.password() : user.getPassword()));

        return null;
    }
}
