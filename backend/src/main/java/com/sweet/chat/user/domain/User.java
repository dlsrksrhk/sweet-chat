package com.sweet.chat.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Column
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private UserProfile profile;

    public void updateProfileImage(String imageUrl) {
        if (getProfile() == null) {
            this.profile = new UserProfile();
        }

        this.profile.setProfileImageUrl(imageUrl);
    }

    public UserDetails toUserDetails() {
        return org.springframework.security.core.userdetails.User.builder()
                .username(this.loginId)
                .password(this.password)
                .build();
    }
}
