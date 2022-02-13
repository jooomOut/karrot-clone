package com.karrot.demo.exception.comment;

import javax.persistence.EntityNotFoundException;

public class CommentNotFoundException extends EntityNotFoundException {
    private static final String MSG = "해당 댓글을 찾을 수 없습니다.";

    public CommentNotFoundException() {
        super(MSG);
    }

    public CommentNotFoundException(String message) {
        super(message);
    }

    public String getMsg(){
        return MSG;
    }
}
