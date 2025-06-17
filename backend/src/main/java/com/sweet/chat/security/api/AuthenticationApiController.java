package com.sweet.chat.security.api;

import com.sweet.chat.security.api.command.SignupRequest;
import com.sweet.chat.security.dto.SessionUserInfo;
import com.sweet.chat.user.domain.User;
import com.sweet.chat.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO ResponseEntity 응답객체 생성
@RestController
@RequiredArgsConstructor
public class AuthenticationApiController {

    private final UserService userService;

    @PostMapping("/api/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        userService.createUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/api/session/userinfo")
    public ResponseEntity<SessionUserInfo> getSessionUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(SessionUserInfo
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .build());
    }

}
