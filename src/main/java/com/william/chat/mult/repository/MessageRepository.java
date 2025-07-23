package com.william.chat.mult.repository;

import com.william.chat.mult.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageModel, UUID> {
}
