    package com.william.chat.mult.model;

    import jakarta.persistence.*;
    import lombok.*;

    import java.util.List;
    import java.util.UUID;

    @Entity
    @Table(name = "chats")
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    @Setter
    public class ChatModel {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(name = "chat_name")
        private String name;

        @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER)
        private List<MessageModel> messages;

        @ManyToMany()
        @JoinTable(name = "chat_user",
        joinColumns = {@JoinColumn(name = "chat_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")})
        private List<UserModel> users;

    }
