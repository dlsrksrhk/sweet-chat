package com.sweet.chat.security.api.command;

import lombok.Data;

@Data
public class LoginRequest {
    private String userLoginId;
    private String password;
}