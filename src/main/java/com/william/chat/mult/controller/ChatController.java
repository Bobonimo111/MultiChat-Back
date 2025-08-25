package com.william.chat.mult.controller;

import com.william.chat.mult.dto.chat.NewChatDto;
import com.william.chat.mult.dto.chat.SavedChatDto;
import com.william.chat.mult.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("chat")
public class ChatController {

    public final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<SavedChatDto> CrateNewChat(@Valid NewChatDto dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatService.createNewChat(dto));
    }

    @GetMapping
    public ResponseEntity<List<SavedChatDto>> listAll(){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatService.listAll());

    }


}
