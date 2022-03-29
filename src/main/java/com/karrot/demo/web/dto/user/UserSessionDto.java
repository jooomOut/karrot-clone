package com.karrot.demo.web.dto.user;

import com.karrot.demo.domain.image.UserProfileImage;
import com.karrot.demo.domain.user.Account;
import com.karrot.demo.web.dto.image.ImageDto;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class UserSessionDto implements Serializable {
    Long id;
    String email;
    String username;
    String phone;
    String nickname;
    ImageDto image;

    public static UserSessionDto from(Account account){
        return UserSessionDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .username(account.getUsername())
                .phone(account.getPhone())
                .nickname(account.getNickname())
                .image(account.getImage() == null ? null :
                        ImageDto.builder()
                                .id(account.getImage().getId())
                                .path(account.getImage().getPath())
                                .build())
                .build();
    }
}
