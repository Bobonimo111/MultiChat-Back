package com.william.chat.mult.service;

import com.william.chat.mult.dto.MessageDto;
import com.william.chat.mult.model.ChatModel;
import com.william.chat.mult.model.MessageModel;
import com.william.chat.mult.model.UserModel;
import com.william.chat.mult.repository.ChatRepository;
import com.william.chat.mult.repository.MessageRepository;
import com.william.chat.mult.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class MessageService {

    public final MessageRepository messageRepository;
    public final ChatRepository chatRepository;
    public final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public MessageDto SaveNewMessage(String message, UUID chat_id, UUID user_id){
        ChatModel chatModel = chatRepository.findById(chat_id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Chat não exist")
        );

        UserModel userModel = userRepository.findById(user_id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario não existe")
        );

        MessageModel messageModel = MessageModel.builder()
                .message(message)
                .chat(chatModel)
                .user(userModel)
                .build();

        MessageModel saveMessage = this.messageRepository.save(messageModel);

        return new MessageDto(
                saveMessage.getId(),
                saveMessage.getMessage(),
                saveMessage.getChat().getId(),
                saveMessage.getUser().getId()
        );

    }

    private MessageDto preparerMessageDto(MessageModel savedMessage){
        return new MessageDto(
                savedMessage.getId(),
                savedMessage.getMessage(),
                savedMessage.getChat().getId(),
                savedMessage.getUser().getId());
    }

    public List<MessageDto> getMessagesByChatId(UUID id) {
        List messages = messageRepository.getMessagesByChatId(id);
        if(messages.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return messages;
    }
}
