package com.william.chat.mult.dto;

import java.util.UUID;

public record ReturnUserDto(
        UUID id,
        String username,
        String email
) {
}
