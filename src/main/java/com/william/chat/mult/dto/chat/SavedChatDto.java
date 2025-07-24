package com.william.chat.mult.dto.chat;

import java.util.UUID;

public record SavedChatDto(UUID id,
                           String chat_name
) {
}
