package com.sweet.chat.common;

import com.sweet.chat.user.domain.User;

public class TestFactory {
    public static final String TEST_USER_NAME_1 = "testUser";
    public static final String TEST_USER_NAME_2 = "testUser2";
    public static final String TEST_USER_PASSWORD = "1234";

    public static User createUser(String username, String encodedPassword) {
        return User.builder()
                .username(username)
                .password(encodedPassword)
                .build();
    }
}
