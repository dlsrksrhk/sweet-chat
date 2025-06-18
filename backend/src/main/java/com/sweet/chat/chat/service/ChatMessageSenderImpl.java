package com.sweet.chat.chat.service;

import com.sweet.chat.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatMessageSenderImpl implements ChatMessageSender {
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(Long roomId, ChatMessageDto message) {
        messagingTemplate.convertAndSend("/topic/chatroom." + roomId, message);
    }
}
