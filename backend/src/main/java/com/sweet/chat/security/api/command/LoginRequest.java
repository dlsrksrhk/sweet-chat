package com.sweet.chat.security.api.command;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}