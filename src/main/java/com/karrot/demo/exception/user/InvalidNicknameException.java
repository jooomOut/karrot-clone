package com.karrot.demo.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.BAD_REQUEST)
public class InvalidNicknameException extends RuntimeException{
    private static final String MSG = "잘못된 닉네임 형식 입니다.";

    public InvalidNicknameException() {
        super(MSG);
    }
}
