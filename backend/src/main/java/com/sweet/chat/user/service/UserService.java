package com.sweet.chat.user.service;

import com.sweet.chat.user.domain.User;

public interface UserService {

    boolean existsByUsername(String username);

    User createUser(String username, String password);

    User findByUsername(String username);
}
