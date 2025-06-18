package com.sweet.chat.chat.service;

import com.sweet.chat.chat.domain.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoom> getAllChatRooms();
    List<ChatRoom> getChatRoomsByUserName(String userName);

    ChatRoom createChatRoom(String userName, String chatRoomName);

    void joinChatRoom(Long roomId, String reqUserName, String targetUserName);
}
