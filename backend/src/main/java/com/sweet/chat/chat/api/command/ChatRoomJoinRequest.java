package com.sweet.chat.chat.api.command;

import lombok.Data;

@Data
public class ChatRoomJoinRequest {
    private Long roomId;
    private String username;
}