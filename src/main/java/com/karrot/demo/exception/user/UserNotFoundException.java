package com.karrot.demo.exception.user;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    private static final String MSG = "사용자를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(MSG);
    }
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(Long id) {
        super(MSG + " : " + id);
    }
    public String getMsg() {
        return MSG;
    }
}