package com.sweet.chat.user.api;

import com.sweet.chat.user.api.command.UserSignUpRequest;
import com.sweet.chat.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/user/signup")
    public ResponseEntity<Void> signUpUser(@RequestBody UserSignUpRequest request) {
        userService.signUpUser(request);
        return ResponseEntity.ok().build();
    }
}
