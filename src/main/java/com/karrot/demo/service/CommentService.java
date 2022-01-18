package com.karrot.demo.service;

import com.karrot.demo.domain.comment.Comment;
import com.karrot.demo.domain.comment.CommentRepository;
import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemRepository;
import com.karrot.demo.domain.user.Account;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.util.SecurityUtils;
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
        SecurityUtils.checkUser(commentDto.getCommenterId());
        Item item = itemRepository.findById(commentDto.getItemId())
                .orElseThrow(() -> new EntityNotFoundException("item is not found : with id - "+ commentDto.getItemId()));
        Account user = userRepository.findById(commentDto.getCommenterId())
                .orElseThrow(() -> new EntityNotFoundException("user is not found : with id - "+ commentDto.getCommenterId()));

        Comment comment = toEntityFromAdding(commentDto);
        comment.setCommenter(user);
        comment.setItem(item);
        commentRepository.save(comment);
    }

    private Comment toEntityFromAdding(AddCommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
