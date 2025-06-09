package com.sweet.chat.security.api.command;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
}