package com.karrot.demo.domain.item;

public enum ItemStatus {
    SALE("판매중"),
    RESERVED("예약중"),
    SOLD("거래완료");

    private String krName;
    ItemStatus(String krName) {
        this.krName = krName;
    }
    public String getKrName() {
        return krName;
    }

}
