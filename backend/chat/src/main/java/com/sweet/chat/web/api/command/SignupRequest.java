package com.sweet.chat.web.api.command;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
}