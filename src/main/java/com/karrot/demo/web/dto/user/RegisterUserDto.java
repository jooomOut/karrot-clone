package com.karrot.demo.web.dto.user;

import lombok.Builder;

@Builder
public class RegisterUserDto {
    String email;
    String password;
    String username;
    String phone;
    String nickname;
}
