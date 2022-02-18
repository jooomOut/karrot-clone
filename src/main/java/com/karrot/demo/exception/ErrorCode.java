package com.karrot.demo.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    /*댓글 도메인*/
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 댓글을 찾을 수 없습니다."),

    /*관심 목록*/
    INTEREST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 관심목록입니다."),

    /*게시물*/
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "게시물을 찾을 수 없습니다."),
    INVALID_ITEM_STATUS(HttpStatus.BAD_REQUEST.value(), "잘못된 상태 값 입니다."),

    /*유저*/
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "사용자를 찾을 수 없습니다."),
    INVALID_NICKNAME(HttpStatus.BAD_REQUEST.value(), "잘못된 닉네임 형식 입니다."),
    DUPLICATE_USER(HttpStatus.CONFLICT.value(), "이미 사용중인 이메일 혹은 전화번호입니다."),

    ;

    private int status;
    private final String message;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
