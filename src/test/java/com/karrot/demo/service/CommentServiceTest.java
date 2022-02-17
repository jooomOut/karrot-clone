package com.karrot.demo.service;

import com.karrot.demo.web.dto.comment.AddCommentDto;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Test
    @DisplayName("코멘트 등록 : 실패")
    void addComment_fail(){
        AddCommentDto dto = AddCommentDto.builder()
                        .text("text")
                        .commenterId(1L)
                        .itemId(1L)
                        .build();
        // 유저가 없거나 게시물이 없거나.
        assertThrows(DataIntegrityViolationException.class, () -> commentService.addComment(dto));
    }


}