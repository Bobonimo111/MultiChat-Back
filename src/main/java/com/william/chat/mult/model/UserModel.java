package com.william.chat.mult.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<ChatModel> chats;

}
