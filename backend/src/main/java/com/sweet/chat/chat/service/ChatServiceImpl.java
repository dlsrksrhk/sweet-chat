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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatServiceImpl implements ChatService {
    private final ChatMessageSender chatMessageSender;
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
        ChatMessage chatMessage = saveMessage(room, request.getContent(), sender);

        // 브로드캐스트할 메시지
        ChatMessageDto message = new ChatMessageDto();
        message.setRoomId(roomId);
        message.setContent(request.getContent());
        message.setSender(sender.getUsername());
        message.setTimestamp(chatMessage.getSendedAt());

        chatMessageSender.sendMessage(roomId, message);
    }

    @Override
    public ChatMessage saveMessage(ChatRoom chatRoom, String message, User sender) {
        ChatMessage entity = new ChatMessage();
        entity.setRoom(chatRoom);
        entity.setSender(sender);
        entity.setContent(message);
        entity.setSendedAt(LocalDateTime.now());
        chatMessageRepository.save(entity);
        return entity;
    }

    @Override
    public List<ChatMessageDto> getChatHistoryByRoomId(Long roomId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByRoomIdOrderBySendedAtAsc(roomId);

        return chatMessages.stream()
                .map(ChatMessageDto::from)
                .toList();
    }

    @Override
    public ChatMessageDto findLastMessageByRoomId(Long roomId) {
        ChatMessage lastChatMessage = chatMessageRepository.findFirstByRoomIdOrderBySendedAtDesc(roomId);
        if (lastChatMessage == null) return null;

        return ChatMessageDto.from(lastChatMessage);
    }
}
