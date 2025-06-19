package com.sweet.chat;

import com.sweet.chat.chat.domain.ChatRoom;
import com.sweet.chat.chat.service.ChatRoomService;
import com.sweet.chat.chat.service.ChatService;
import com.sweet.chat.user.domain.User;
import com.sweet.chat.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class ChatApplication implements CommandLineRunner {
    private final UserService userService;
    private final ChatRoomService chatRoomService;
    private final ChatService chatService;
    private final Environment environment;


    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        String ddlAuto = environment.getProperty("spring.jpa.hibernate.ddl-auto");
        if(!"create".equalsIgnoreCase(ddlAuto)){
            return;
        }

        User user1 = userService.createUser("test1", "김동현", "1234", "test1@naver.com");
        User user2 = userService.createUser("test2", "한경선", "1234", "test2@naver.com");
        ChatRoom chatRoom1 = chatRoomService.createChatRoom(user1.getLoginId(), "한경선");
        chatRoomService.joinChatRoom(chatRoom1.getId(), user1.getLoginId(), user2.getLoginId());

        ChatRoom chatRoom2 = chatRoomService.createChatRoom(user2.getLoginId(), "스윗코드 단체방");
        chatRoomService.joinChatRoom(chatRoom2.getId(), user2.getLoginId(), user1.getLoginId());

        chatService.saveMessage(chatRoom1, "하이루!", user1);
        chatService.saveMessage(chatRoom1, "하위~~!", user2);
        chatService.saveMessage(chatRoom1, "머하누?", user1);
        chatService.saveMessage(chatRoom1, "한우 먹는중 ㅋㅋㅋ", user2);
    }
}
