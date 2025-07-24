package com.william.chat.mult.dto;


import java.util.UUID;

public record MessageDto(
        UUID id,
        String message,
        UUID chat_id,
        UUID user_id
) {
}
