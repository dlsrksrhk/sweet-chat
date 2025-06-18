package com.sweet.chat.chat.repository;

import com.sweet.chat.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findAllByOrderByCreatedAtAsc();

    @Query(
            value = "SELECT cr.* FROM chat_rooms cr " +
                    "JOIN chat_room_members crm ON cr.id = crm.room_id " +
                    "JOIN users u ON crm.joiner_id = u.id " +
                    "WHERE u.username = :username",
            nativeQuery = true)
    List<ChatRoom> findByJoinedUserName(@Param("username") String username);
}