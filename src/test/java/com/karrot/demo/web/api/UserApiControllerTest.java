package com.karrot.demo.web.api;

import com.karrot.demo.web.dto.user.RegisterUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class UserApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    @WithMockUser
    @DisplayName("정상 회원 가입")
    void registerUser_200() throws Exception {
        RegisterUserDto user = RegisterUserDto.builder().
                email("tester@gmail.com")
                .password("TTTT")
                .nickname("Tester")
                .phone("0108888999")
                .username("김")
                .build();
        // when
        String requestUri = "/users" ;
        ResultActions actions = mockMvc
                .perform(post(requestUri)
                        .param("email", user.getEmail())
                        .param("password", user.getPassword())
                        .param("nickname", user.getNickname())
                        .param("phone", user.getPhone())
                        .param("username", user.getUsername())
                )
                ;
        // then
        actions
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

}