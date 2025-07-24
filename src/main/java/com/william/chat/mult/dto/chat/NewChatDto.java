package com.william.chat.mult.dto.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record NewChatDto (
        @NotBlank
        String chatName,
        @Size(min = 2,message = "O canal deve conter pelo menos dois UUID")
        List<UUID> usersId
        ){}
