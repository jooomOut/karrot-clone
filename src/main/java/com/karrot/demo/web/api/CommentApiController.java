package com.karrot.demo.web.api;

import com.karrot.demo.service.CommentService;
import com.karrot.demo.util.SecurityUtils;
import com.karrot.demo.web.dto.comment.AddCommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity addComment(@ModelAttribute @Valid AddCommentDto commentDto){

        SecurityUtils.checkUser(commentDto.getCommenterId());
        commentService.addComment(commentDto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long commentId,
                                        @ModelAttribute @Valid AddCommentDto commentDto,
                                        BindingResult errors){

        commentService.updateComment(commentId, commentDto.getText());
        return ResponseEntity.ok().build();
    }

}
