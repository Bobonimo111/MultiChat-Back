package com.william.chat.mult.dto;

import java.util.UUID;

public record UserSavedDto(
        UUID id,
        String username,
        String password,
        String email
){}
