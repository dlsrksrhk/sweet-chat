package com.sweet.chat.user.service;

import com.sweet.chat.user.api.command.UserSignUpRequest;
import com.sweet.chat.user.domain.User;

public interface UserService {

    boolean existsByLoginId(String loginId);

    User createUser(String loginId, String userName, String password, String email);
    User signUpUser(UserSignUpRequest request);


    User findByLoginId(String loginId);
}
