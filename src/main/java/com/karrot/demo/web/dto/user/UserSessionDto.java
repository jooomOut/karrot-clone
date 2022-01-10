package com.karrot.demo.web.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSessionDto {
    Long id;
    String email;
    String username;
    String phone;
    String nickname;
}
