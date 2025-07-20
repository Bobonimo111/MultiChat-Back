package com.william.chat.mult.repository;

import com.william.chat.mult.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    List<UserModel> findByUsernameContaining(String username);
}
