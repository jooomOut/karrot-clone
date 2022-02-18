package com.karrot.demo.exception.comment;

import com.karrot.demo.exception.ErrorCode;
import com.karrot.demo.exception.common.CustomEntityNotFoundException;

public class CommentNotFoundException extends CustomEntityNotFoundException {

    public CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }

    public CommentNotFoundException(String message) {
        super(ErrorCode.COMMENT_NOT_FOUND, message);
    }
}
