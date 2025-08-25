package com.william.chat.mult.config;

import com.william.chat.mult.model.ChatModel;
import com.william.chat.mult.model.MessageModel;
import com.william.chat.mult.model.UserModel;
import com.william.chat.mult.repository.ChatRepository;
import com.william.chat.mult.repository.MessageRepository;
import com.william.chat.mult.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AfterStartApplication implements CommandLineRunner {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(!userRepository.existsByUsername("carlos") || !userRepository.existsByUsername("william")){
            UserModel user1 = UserModel.builder()
                    .email("carlosmaycon@gmail.com")
                    .username("carlos")
                    .password("carlosmaycon@gmail.com")
                    .build();
            UserModel user2 = UserModel.builder()
                    .email("williamr@gmail.com")
                    .username("william")
                    .password("williammr@gmail.com")
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);

            ChatModel chat = ChatModel
                    .builder()
                    .users(List.of(user1, user2))
                    .build();

            chatRepository.save(chat);
        }
        System.out.println("ChatId : " + chatRepository.findAll().getFirst().getId());
    }
}
