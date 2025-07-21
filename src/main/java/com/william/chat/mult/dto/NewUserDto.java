package com.william.chat.mult.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NewUserDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @Email
        String email
) {
}
