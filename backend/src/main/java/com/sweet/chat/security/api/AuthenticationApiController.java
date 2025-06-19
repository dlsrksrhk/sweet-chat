package com.sweet.chat.security.api;

import com.sweet.chat.security.dto.SessionUserInfo;
import com.sweet.chat.user.domain.User;
import com.sweet.chat.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO ResponseEntity 응답객체 생성
@RestController
@RequiredArgsConstructor
public class AuthenticationApiController {

    private final UserService userService;

    @GetMapping("/api/session/userinfo")
    public ResponseEntity<SessionUserInfo> getSessionUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByLoginId(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(SessionUserInfo
                .builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .userName(user.getUserName())
                .build());
    }

}
