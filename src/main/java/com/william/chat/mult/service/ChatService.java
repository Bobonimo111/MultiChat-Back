package com.william.chat.mult.service;

import com.william.chat.mult.dto.chat.NewChatDto;
import com.william.chat.mult.dto.chat.SavedChatDto;
import com.william.chat.mult.model.ChatModel;
import com.william.chat.mult.model.UserModel;
import com.william.chat.mult.repository.ChatRepository;
import com.william.chat.mult.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    public final ChatRepository chatRepository;
    public final UserRepository userRepository;
    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public SavedChatDto createNewChat(NewChatDto newchatDto){
        List<UserModel> userList = userRepository.findAllById(newchatDto.usersId());

        if (userList.size() != newchatDto.usersId().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um ou mais usuários não encontrados");
        }

        ChatModel chatModel = ChatModel.builder()
                .name(newchatDto.chatName())
                .users(userList)
                .build();

        return prepareChatDto(chatModel);
    }

    public List<SavedChatDto> listAll(){
        List<ChatModel> chatlist = chatRepository.findAll();

        if(chatlist.isEmpty()){
            return null;
        }

        return chatlist.stream().map(
                this::prepareChatDto
                ).collect(Collectors.toList());
    }

    private SavedChatDto prepareChatDto(ChatModel chatModel){
        return new SavedChatDto(chatModel.getId(), chatModel.getName());
    }
}
