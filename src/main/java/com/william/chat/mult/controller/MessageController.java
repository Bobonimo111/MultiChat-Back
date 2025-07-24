package com.william.chat.mult.controller;

import com.william.chat.mult.dto.MessageDto;
import com.william.chat.mult.dto.NewMessageDto;
import com.william.chat.mult.repository.ChatRepository;
import com.william.chat.mult.repository.UserRepository;
import com.william.chat.mult.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("msg")
public class MessageController {

    public final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> createNew(@Valid NewMessageDto dto){
        return ResponseEntity.ok()
                .body(this.messageService.SaveNewMessage(
                                dto.message()
                                ,dto.chat_id()
                                ,dto.user_id()));
    }
}

