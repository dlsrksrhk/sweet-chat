package com.sweet.chat.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionUserInfo {
    private Long id;
    private String username;
}
