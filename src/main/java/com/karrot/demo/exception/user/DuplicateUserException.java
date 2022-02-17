package com.karrot.demo.exception.user;

import org.springframework.dao.DuplicateKeyException;

public class DuplicateUserException extends DuplicateKeyException {
    private static final String DEFAULT_MSG = "이미 사용중인 이메일 혹은 전화번호입니다.";

    public DuplicateUserException() {
        super(DEFAULT_MSG);
    }

    public DuplicateUserException(String msg) {
        super(msg);
    }

    public DuplicateUserException(String email, String phone) {
        super(DEFAULT_MSG + " [" +email + ", " + phone + "]");
    }
}
