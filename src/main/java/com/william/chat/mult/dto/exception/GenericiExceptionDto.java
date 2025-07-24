package com.william.chat.mult.dto.exception;

import java.time.LocalDateTime;

public record GenericiExceptionDto(
        LocalDateTime TimeStamp,
        String Message,
        String Error,
        int Status
) {
}
