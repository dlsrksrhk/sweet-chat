package com.sweet.chat.chat.api.command;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SendChatMessageRequest {
    private String content;
}