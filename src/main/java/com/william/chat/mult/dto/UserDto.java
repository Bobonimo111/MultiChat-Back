package com.william.chat.mult.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UserDto(
        @NotNull
        UUID id,
        String username,
        String password,
        @Email
        String email
){}
