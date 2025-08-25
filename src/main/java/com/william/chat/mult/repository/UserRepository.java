package com.william.chat.mult.repository;

import com.william.chat.mult.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsById(UUID id);
    List<UserModel> findByUsernameContaining(String username);

    boolean existsByUsername(String username);

//    @Query("SELECT u FROM UserModel u WHERE u.chats.id = :chatId ")
    List<UserModel> findByChatsId(UUID chatId);
}
