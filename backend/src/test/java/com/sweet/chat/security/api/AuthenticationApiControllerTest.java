package com.sweet.chat.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweet.chat.common.TestFactory;
import com.sweet.chat.security.api.command.SignupRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.sweet.chat.common.TestFactory.TEST_USER_NAME_1;
import static com.sweet.chat.common.TestFactory.TEST_USER_PASSWORD;
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
        SignupRequest request = new SignupRequest();
        request.setUsername(TEST_USER_NAME_1);
        request.setPassword(TEST_USER_PASSWORD);

        mockMvc.perform(post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

}