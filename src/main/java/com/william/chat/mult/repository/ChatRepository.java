package com.william.chat.mult.repository;

import com.william.chat.mult.model.ChatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<ChatModel, UUID> {
}
