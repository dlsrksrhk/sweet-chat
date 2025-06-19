package com.sweet.chat.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweet.chat.common.TestFactory;
import com.sweet.chat.security.api.command.LoginRequest;
import com.sweet.chat.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static com.sweet.chat.common.TestFactory.TEST_USER_NAME_2;
import static com.sweet.chat.common.TestFactory.TEST_USER_PASSWORD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() throws Exception {
        userRepository.save(TestFactory.createUser(
                TestFactory.TEST_USER_NAME_1,
                TestFactory.TEST_USER_NAME_1,
                passwordEncoder.encode("1234"),
                TestFactory.TEST_USER_EMAIL_1));

        LoginRequest request = new LoginRequest();
        request.setUserLoginId(TestFactory.TEST_USER_NAME_1);
        request.setPassword(TEST_USER_PASSWORD);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}