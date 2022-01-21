package com.karrot.demo.web.dto.user;

import com.karrot.demo.domain.image.UserProfileImage;
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
    UserProfileImage image;
}
