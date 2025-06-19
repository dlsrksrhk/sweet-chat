package com.sweet.chat.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweet.chat.user.api.command.UserSignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.sweet.chat.common.TestFactory.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void signupSuccess() throws Exception {
        UserSignUpRequest request = new UserSignUpRequest();
        request.setLoginId(TEST_USER_NAME_1);
        request.setUserName(TEST_USER_NAME_1);
        request.setPassword(TEST_USER_PASSWORD);
        request.setEmail(TEST_USER_EMAIL_1);

        mockMvc.perform(post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

}