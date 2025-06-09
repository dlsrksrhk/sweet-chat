package com.sweet.chat.chat.service;

import com.sweet.chat.chat.domain.ChatRoom;
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
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Override
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public ChatRoom createChatRoom(String userName, String chatRoomName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        ChatRoom room = ChatRoom.builder()
                .name(chatRoomName)
                .createdBy(user)
                .createdAt(LocalDateTime.now())
                .build();

        return chatRoomRepository.save(room);
    }
}
