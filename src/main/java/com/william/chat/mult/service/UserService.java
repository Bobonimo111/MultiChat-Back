package com.william.chat.mult.service;

import com.william.chat.mult.dto.ReturnUserDto;
import com.william.chat.mult.dto.UserDto;
import com.william.chat.mult.model.UserModel;
import com.william.chat.mult.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

        return this.prepareUserDto(savedUser);
    }

    public List<UserDto> findByUsernameContaining(String username){

        List<UserModel> userModelList = userRepository.findByUsernameContaining(username);

        if(userModelList.isEmpty()){
            return null;
        }
        //Convertendo a lista do tipo UserModel para o tipo UserSavedDto.

        return userModelList
                .stream()
                .map(
                        model -> this.prepareUserDto(model)
                )
                .collect(Collectors.toList());
    }

    public UserDto updateUserById(UserDto userDto){
        Optional<UserModel> userOp = userRepository.findById(userDto.id());
        if(userOp.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ID NOT FOUND");
        }
        UserModel user = userOp.get();

        user.setUsername(((userDto.username() == null || "".equals(userDto.username()))? userDto.username() : user.getUsername()));
        user.setEmail((!(userDto.email() == null || "".equals(userDto.email()))? userDto.email() : user.getEmail()));
        user.setPassword((!(userDto.password() == null || "".equals(userDto.password()))? userDto.password() : user.getPassword()));

        UserModel savedUser = userRepository.save(user);

        return prepareUserDto(savedUser);
    }

    private UserDto prepareUserDto(UserModel savedUser){
        return new UserDto(
                savedUser.getId(),
                savedUser.getUsername(),
                null,
                savedUser.getEmail());
    }

    public List<ReturnUserDto> getUserByChatId(UUID chatId) {
        List<UserModel> users = userRepository.findByChatsId(chatId);
        return users.stream().map(
                (m) -> new ReturnUserDto(m.getId(),m.getUsername(), m.getEmail()))
                .collect(Collectors.toList());
    }
}
