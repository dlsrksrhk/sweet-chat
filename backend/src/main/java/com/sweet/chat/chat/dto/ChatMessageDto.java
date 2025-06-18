package com.sweet.chat.chat.dto;

import com.sweet.chat.chat.domain.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChatMessageDto {
    private Long id;
    private Long roomId;
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public static ChatMessageDto from(ChatMessage chatMessage) {
        return ChatMessageDto.builder()
                .id(chatMessage.getId())
                .roomId(chatMessage.getRoom().getId())
                .sender(chatMessage.getSender().getUsername())
                .content(chatMessage.getContent())
                .timestamp(chatMessage.getSendedAt())
                .build();
    }
}