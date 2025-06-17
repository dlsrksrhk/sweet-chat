package com.sweet.chat.chat.repository;

import com.sweet.chat.chat.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomIdOrderBySendedAtAsc(Long roomId);
    ChatMessage findFirstByRoomIdOrderBySendedAtDesc(Long roomId);
}