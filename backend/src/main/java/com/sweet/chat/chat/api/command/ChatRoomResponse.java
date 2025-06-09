package com.sweet.chat.chat.api.command;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChatRoomResponse {
    private Long id;
    private String name;
    private String createdBy;
    private LocalDateTime createdAt;
}