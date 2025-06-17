package com.sweet.chat.chat.domain;

import com.sweet.chat.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ChatRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDateTime sendedAt;
}