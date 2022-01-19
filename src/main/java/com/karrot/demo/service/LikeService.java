package com.karrot.demo.service;

import com.karrot.demo.domain.comment.Comment;
import com.karrot.demo.domain.comment.CommentRepository;
import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemRepository;
import com.karrot.demo.domain.like.Like;
import com.karrot.demo.domain.like.LikeRepository;
import com.karrot.demo.domain.user.Account;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.util.SecurityUtils;
import com.karrot.demo.web.dto.comment.AddCommentDto;
import com.karrot.demo.web.dto.like.AddLikeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class LikeService {

    private LikeRepository  likeRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    @Autowired
    public LikeService(LikeRepository likeRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public void addLike(AddLikeDto dto){
        Account user = userRepository.getById(dto.getUserId());
        Item item = itemRepository.getById(dto.getItemId());
        Like like = Like.builder()
                .user(user)
                .item(item)
                .build();
        try {
            likeRepository.save(like);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("no user or item id");
        }
    }
}
