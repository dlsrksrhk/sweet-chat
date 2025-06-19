package com.sweet.chat.common;

import com.sweet.chat.user.domain.User;

public class TestFactory {
    public static final String TEST_USER_NAME_1 = "testUser";
    public static final String TEST_USER_EMAIL_1 = TEST_USER_NAME_1 + "@test.com";
    public static final String TEST_USER_NAME_2 = "testUser2";
    public static final String TEST_USER_EMAIL_2 = TEST_USER_NAME_2 + "@test.com";
    public static final String TEST_USER_PASSWORD = "1234";

    public static User createUser(String loginId, String userName, String encodedPassword, String email) {
        return User.builder()
                .loginId(loginId)
                .userName(userName)
                .password(encodedPassword)
                .email(email)
                .build();
    }
}
