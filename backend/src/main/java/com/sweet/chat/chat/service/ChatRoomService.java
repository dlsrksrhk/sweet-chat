package com.sweet.chat.chat.service;

import com.sweet.chat.chat.domain.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoom> getAllChatRooms();

    ChatRoom createChatRoom(String userName, String chatRoomName);
}
