package com.sweet.chat.chat.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageDto {
    private Long roomId;
    private String sender;
    private String content;
    private LocalDateTime timestamp;
}