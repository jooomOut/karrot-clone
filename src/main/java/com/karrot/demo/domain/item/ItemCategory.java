package com.karrot.demo.domain.item;

import java.util.Arrays;

public enum ItemCategory {
    DIGITAL_DEVICE("디지털기기"),
    HOME_APPLIANCES("생활가전"),
    FURNITURE("가구/인테리어"),
    CHILD("유아동"),
    HOUSEHOLD_PROCESSED_FOOD("생활/가공식품"),
    CHILD_BOOK("유아도서"),
    SPORTS("스포츠/레저"),
    WOMEN_GOODS("여성잡화"),
    WOMEN_CLOTHES("여성의류"),
    MEN_GODDS("남성패션/잡화"),
    GAME_HOBBIES("게임/취미"),
    BEAUTY("뷰티/미용"),
    PETS("반려동물용품"),
    BOOKS_ALBUM("도서/티켓/음반"),
    PLANTS("식물"),
    ETC("기타 중고물품"),
    CARS("중고차");


    private String krName;
    ItemCategory(String krName) {
        this.krName = krName;
    }
    public String getKrName() {
        return krName;
    }

}
