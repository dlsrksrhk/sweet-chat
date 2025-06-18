package com.sweet.chat.chat.service;

import com.sweet.chat.chat.dto.ChatMessageDto;

public interface ChatMessageSender {
    void sendMessage(Long roomId, ChatMessageDto message);
}
