package com.sweet.chat.chat.service;

import com.sweet.chat.chat.api.command.SendChatMessageRequest;
import com.sweet.chat.chat.domain.ChatMessage;
import com.sweet.chat.chat.domain.ChatRoom;
import com.sweet.chat.chat.dto.ChatMessageDto;
import com.sweet.chat.user.domain.User;

import java.util.List;

public interface ChatService {
    void sendMessage(Long roomId, SendChatMessageRequest request, String username);
    ChatMessage saveMessage(ChatRoom chatRoom, String message, User sender);

    List<ChatMessageDto> getChatHistoryByRoomId(Long roomId);

    ChatMessageDto findLastMessageByRoomId(Long roomId);
}
