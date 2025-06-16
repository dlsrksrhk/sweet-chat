package com.sweet.chat.chat.api;

import com.sweet.chat.chat.api.command.SendChatMessageRequest;
import com.sweet.chat.chat.dto.ChatMessageDto;
import com.sweet.chat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@DestinationVariable Long roomId,
                            SendChatMessageRequest request,
                            Principal principal) {

        String username = principal.getName();
        chatService.sendMessage(roomId, request, username);
    }
}
