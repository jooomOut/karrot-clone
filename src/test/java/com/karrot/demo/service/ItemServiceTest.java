package com.karrot.demo.service;

import com.karrot.demo.domain.item.ItemStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;


class ItemServiceTest {

    @Test
    @DisplayName("Item 상태 업데이트 - enum 에러")
    void itemUpdate_ENUM_ERROR(){
        assertThrows(IllegalArgumentException.class, () -> ItemStatus.valueOf("AAA"));
    }

}