package com.sweet.chat.user.service;

import com.sweet.chat.user.api.command.UserSignUpRequest;
import com.sweet.chat.user.domain.User;
import com.sweet.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean existsByLoginId(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    @Override
    public User createUser(String loginId, String userName, String password, String email) {
        User user = User.builder()
                .loginId(loginId)
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User signUpUser(UserSignUpRequest request) {
        if (existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 가입 중인 로그인 ID입니다.");
        }

        User user = User.builder()
                .loginId(request.getLoginId())
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다 : " + loginId));
    }
}
