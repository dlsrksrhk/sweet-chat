package com.sweet.chat.user.api.command;

import lombok.Data;

@Data
public class UserSignUpRequest {
    private String loginId;
    private String password;
    private String userName;
    private String email;
}