package com.sweet.chat.chat.api;

import com.sweet.chat.chat.dto.ChatMessageDto;
import com.sweet.chat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
@RequiredArgsConstructor
public class ChatHistoryController {
    private final ChatService chatService;

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<ChatMessageDto>> getChatHistory(@PathVariable Long roomId) {
        List<ChatMessageDto> messages = chatService.getChatHistoryByRoomId(roomId);
        return ResponseEntity.ok(messages);
    }
}
