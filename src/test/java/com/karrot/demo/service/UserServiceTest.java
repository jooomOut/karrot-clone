package com.karrot.demo.service;

import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.exception.user.DuplicateUserException;
import com.karrot.demo.web.dto.user.RegisterUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    RegisterUserDto userDto = RegisterUserDto.builder().
            email("tester@gmail.com")
            .password("TTTT")
            .nickname("Tester")
            .phone("0108888999")
            .username("김")
            .build();

    @Test
    @DisplayName("중복 사용자 추가")
    void registerUser_dup(){
        when(userRepository.save(any()))
                .thenThrow(new DuplicateUserException());
        when(passwordEncoder.encode(userDto.getPassword()))
                .thenReturn(userDto.getPassword());

        assertThrows(DuplicateUserException.class, () -> userService.registerUser(userDto));
    }


}