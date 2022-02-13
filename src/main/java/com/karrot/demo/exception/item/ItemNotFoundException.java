package com.karrot.demo.exception.item;

import javax.persistence.EntityNotFoundException;

public class ItemNotFoundException extends EntityNotFoundException {
    private static final String MSG = "게시물을 찾을 수 없습니다.";

    public ItemNotFoundException() {
        super(MSG);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public String getMsg(){
        return MSG;
    }
}
