package com.sweet.chat.chat.domain;

import com.sweet.chat.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "chat_rooms")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @OneToMany(mappedBy = "room")
    private List<ChatRoomMember> roomMembers = new ArrayList<>();

    private LocalDateTime createdAt;

    public void addRoomMember(ChatRoomMember member) {
        if (member != null && !this.roomMembers.contains(member)) {
            this.roomMembers.add(member);
            member.setRoom(this);
        }
    }

    public boolean isRoomOwner(User user) {
        if (user == null) return false;
        return this.createdBy.getId().equals(user.getId());
    }

    public boolean isRoomMember(User user) {
        if (user == null) return false;
        return this.roomMembers
                .stream()
                .anyMatch(
                        member -> member
                                .getJoiner()
                                .getId()
                                .equals(user.getId())
                );
    }
}