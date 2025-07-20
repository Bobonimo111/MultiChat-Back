package com.william.chat.mult.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    private String message;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private ChatModel chat;
}
