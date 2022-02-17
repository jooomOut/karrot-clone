package com.karrot.demo.exception.interest;

import javax.persistence.EntityNotFoundException;

public class InterestNotFoundException extends EntityNotFoundException {
    private static final String MSG = "존재하지 않는 관심목록입니다.";

    public InterestNotFoundException() {
        super(MSG);
    }
}
