package com.sweet.chat.chat.service;

import com.sweet.chat.chat.domain.ChatRoom;
import com.sweet.chat.chat.domain.ChatRoomMember;
import com.sweet.chat.chat.repository.ChatRoomMemberRepository;
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
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    @Override
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAllByOrderByCreatedAtAsc();
    }

    @Override
    public List<ChatRoom> getChatRoomsByUserName(String userName) {
        return chatRoomRepository.findByJoinedUserName(userName);
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

        chatRoomRepository.save(room);
        joinChatRoom(room.getId(), userName, userName);

        return room;
    }

    @Override
    public void joinChatRoom(Long roomId, String reqUserName, String targetUserName) {
        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        User reqUser = userRepository.findByUsername(reqUserName).orElse(null);
        if(!room.isRoomOwner(reqUser)) {
            throw new IllegalArgumentException("채팅방 초대 권한이 없습니다.");
        }

        User targetUser = userRepository.findByUsername(targetUserName)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + targetUserName));

        chatRoomMemberRepository.findByRoomAndJoiner(room, targetUser)
                .ifPresent(member -> {
                    throw new IllegalArgumentException(targetUser.getUsername() + "사용자는 이미 채팅방에 참여 중입니다.");
                });

        chatRoomMemberRepository.save(
                ChatRoomMember.builder()
                        .room(room)
                        .joiner(targetUser)
                        .joinedAt(LocalDateTime.now())
                        .build()
        );
    }
}
