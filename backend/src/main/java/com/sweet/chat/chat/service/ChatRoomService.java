package com.sweet.chat.chat.service;

import com.sweet.chat.chat.domain.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoom> getAllChatRooms();
    List<ChatRoom> getChatRoomsByUserLoginId(String loginId);

    ChatRoom createChatRoom(String userLoginId, String chatRoomName);

    void joinChatRoom(Long roomId, String reqUserLoginId, String targetUserLoginId);
}
