package com.william.chat.mult.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewMessageDto(
        @NotBlank(message = "message não pode estar em branco")
        String message,
    @NotNull(message = "chat_id não pode estar em branco")
    UUID chat_id,
    @NotNull(message = "user_id não pode estar em branco")
    UUID user_id
) {
}
