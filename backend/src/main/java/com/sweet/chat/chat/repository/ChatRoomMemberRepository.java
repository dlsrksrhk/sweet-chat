package com.sweet.chat.chat.repository;

import com.sweet.chat.chat.domain.ChatRoom;
import com.sweet.chat.chat.domain.ChatRoomMember;
import com.sweet.chat.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
    List<ChatRoomMember> findByRoom(ChatRoom room);

    Optional<ChatRoomMember> findByRoomAndJoiner(ChatRoom room, User userName);

}