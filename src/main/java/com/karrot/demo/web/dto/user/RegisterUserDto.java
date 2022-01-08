package com.karrot.demo.web.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterUserDto {
    String email;
    String password;
    String username;
    String phone;
    String nickname;
}
