package com.karrot.demo.web.api;

import com.karrot.demo.service.CommentService;
import com.karrot.demo.service.ItemService;
import com.karrot.demo.util.SecurityUtils;
import com.karrot.demo.web.dto.AddCommentDto;
import com.karrot.demo.web.dto.item.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/comments")
public class CommentApiController {

    private CommentService commentService;
    @Autowired
    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public ResponseEntity addComment(@ModelAttribute @Validated AddCommentDto commentDto,
                                     BindingResult errors){
        if (errors.hasErrors()){
            log.debug(">>> 댓글 작성 에러 : " + errors.toString());
            return ResponseEntity.badRequest().build();
        }
        try {
            commentService.addComment(commentDto);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

}
