package com.karrot.demo.exception.item;

public class InvalidItemStatusException extends IllegalArgumentException{
    private static final String MSG = "잘못된 값입니다.";
    public InvalidItemStatusException() {
        super(MSG);
    }
}
