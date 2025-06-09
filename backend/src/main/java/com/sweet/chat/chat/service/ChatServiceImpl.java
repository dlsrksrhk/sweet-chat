package com.sweet.chat.chat.service;

import com.sweet.chat.chat.api.command.SendChatMessageRequest;
import com.sweet.chat.chat.domain.ChatMessage;
import com.sweet.chat.chat.domain.ChatRoom;
import com.sweet.chat.chat.dto.ChatMessageDto;
import com.sweet.chat.chat.repository.ChatMessageRepository;
import com.sweet.chat.chat.repository.ChatRoomRepository;
import com.sweet.chat.user.domain.User;
import com.sweet.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatServiceImpl implements ChatService {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @Override
    public void sendMessage(Long roomId, SendChatMessageRequest request, String username) {
        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        User sender = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        // DB에 메시지 저장
        ChatMessage entity = new ChatMessage();
        entity.setRoom(room);
        entity.setSender(sender);
        entity.setContent(request.getContent());
        entity.setSendedAt(LocalDateTime.now());
        chatMessageRepository.save(entity);

        // 브로드캐스트할 메시지
        ChatMessageDto message = new ChatMessageDto();
        message.setRoomId(roomId);
        message.setContent(request.getContent());
        message.setSender(sender.getUsername());
        message.setTimestamp(entity.getSendedAt());

        messagingTemplate.convertAndSend("/topic/chatroom." + roomId, message);
    }

    @Override
    public List<ChatMessageDto> getChatHistoryByRoomId(Long roomId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId);

        return chatMessages.stream()
                .map(chatMessage -> {
                    ChatMessageDto msg = new ChatMessageDto();
                    msg.setRoomId(roomId);
                    msg.setSender(chatMessage.getSender().getUsername());
                    msg.setContent(chatMessage.getContent());
                    msg.setTimestamp(chatMessage.getSendedAt());
                    return msg;
                })
                .toList();
    }
}
