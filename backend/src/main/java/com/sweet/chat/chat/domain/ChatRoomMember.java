package com.sweet.chat.chat.domain;

import com.sweet.chat.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "chat_room_members")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User joiner;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom room;

    private LocalDateTime joinedAt;
}