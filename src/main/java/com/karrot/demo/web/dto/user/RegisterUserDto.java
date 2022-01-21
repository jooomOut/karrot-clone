package com.karrot.demo.web.dto.user;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Builder
public class RegisterUserDto {
    @NotNull @Email(message = "이메일 형식을 따르지 못했습니다.")
    String email;
    @NotNull
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
    String phone;
    @NotNull String password;
    @NotNull String username;
    @NotNull String nickname;
}
