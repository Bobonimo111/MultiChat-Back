package com.william.chat.mult.repository;

import com.william.chat.mult.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageModel, UUID> {
    boolean existsById(UUID id);

    @Query("SELECT m FROM MessageModel m WHERE m.chat.id = :chatId")
    List<MessageModel> getMessagesByChatId(@Param("chatId") UUID chatId);
}
