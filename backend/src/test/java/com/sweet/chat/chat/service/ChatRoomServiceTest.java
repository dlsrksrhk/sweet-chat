package com.sweet.chat.chat.service;

import com.sweet.chat.chat.domain.ChatRoom;
import com.sweet.chat.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.sweet.chat.common.TestFactory.TEST_USER_NAME_1;
import static com.sweet.chat.common.TestFactory.TEST_USER_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ChatRoomServiceTest {
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.createUser(TEST_USER_NAME_1, TEST_USER_PASSWORD);
    }

    @Test
    void createChatRoom() {
        // Given
        String roomName = "Test Room";

        // When
        ChatRoom chatRoom = chatRoomService.createChatRoom(TEST_USER_NAME_1, roomName);

        // Then
        assertNotNull(chatRoom, "Chat room ID should not be null");
        assertEquals(chatRoom.getName(), roomName);
    }

}