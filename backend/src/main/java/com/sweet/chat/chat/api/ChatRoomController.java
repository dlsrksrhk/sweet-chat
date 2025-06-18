package com.sweet.chat.chat.api;

import com.sweet.chat.chat.api.command.ChatRoomCreateRequest;
import com.sweet.chat.chat.api.command.ChatRoomJoinRequest;
import com.sweet.chat.chat.domain.ChatRoom;
import com.sweet.chat.chat.dto.ChatRoomResponse;
import com.sweet.chat.chat.service.ChatRoomService;
import com.sweet.chat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatService chatService;

    @PostMapping("/api/chatrooms")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomCreateRequest request,
                                            @AuthenticationPrincipal UserDetails userDetails) {

        ChatRoom chatRoom = chatRoomService.createChatRoom(userDetails.getUsername(), request.getName());

        return ResponseEntity.ok(ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .name(chatRoom.getName())
                .createdBy(userDetails.getUsername())
                .createdAt(chatRoom.getCreatedAt())
                .build());
    }

    @GetMapping("/api/chatrooms")
    public ResponseEntity<List<ChatRoomResponse>> getAllJoinedChatRooms(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<ChatRoom> rooms = chatRoomService.getChatRoomsByUserName(userDetails.getUsername());
        List<ChatRoomResponse> result = rooms.stream()
                .map(room -> ChatRoomResponse.builder()
                        .id(room.getId())
                        .name(room.getName())
                        .createdBy(room.getCreatedBy().getUsername())
                        .createdAt(room.getCreatedAt())
                        .lastMessageInfo(chatService.findLastMessageByRoomId(room.getId()))
                        .build())
                .toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/chatrooms/join")
    public ResponseEntity<Void> joinChatRoom(
            @RequestBody ChatRoomJoinRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {

        return ResponseEntity.ok().build();
    }
}
