package com.sweet.chat.chat.service;

import com.sweet.chat.chat.api.command.SendChatMessageRequest;
import com.sweet.chat.chat.domain.ChatMessage;
import com.sweet.chat.chat.domain.ChatRoom;

import java.util.List;

public interface ChatService {
    void sendMessage(Long roomId, SendChatMessageRequest request, String username);
}
