package com.karrot.demo.service;

import com.karrot.demo.domain.comment.Comment;
import com.karrot.demo.domain.comment.CommentRepository;
import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemRepository;
import com.karrot.demo.domain.user.Account;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.exception.comment.CommentNotFoundException;
import com.karrot.demo.web.dto.comment.AddCommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }


    public String getCommentText(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElse(null);
        return comment == null ? "" : comment.getText();
    }

    public void addComment(AddCommentDto commentDto) {
        Item item = itemRepository.getById(commentDto.getItemId());
        Account user = userRepository.getById(commentDto.getCommenterId());

        Comment comment = toEntityFromAdding(commentDto);
        comment.setCommenter(user);
        comment.setItem(item);
        commentRepository.save(comment);
    }
    public void updateComment(Long commentId, String text) {
        Comment comment = findById(commentId);

        comment.setText(text);
        commentRepository.save(comment);
    }
    private Comment findById(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> {
                    CommentNotFoundException e = new CommentNotFoundException();
                    log.info(e.getMsg() + " : " + id);
                    return e;
                });
    }
    private Comment toEntityFromAdding(AddCommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .createdAt(LocalDateTime.now())
                .build();
    }


}
