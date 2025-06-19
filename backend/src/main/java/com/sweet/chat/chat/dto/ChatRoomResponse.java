package com.sweet.chat.chat.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatRoomResponse {
    private Long id;
    private String name;
    private String createdByUserName;
    private String createdByUserLoginId;
    private LocalDateTime createdAt;
    private ChatMessageDto lastMessageInfo;
}