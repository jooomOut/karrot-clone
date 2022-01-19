package com.karrot.demo.web.api;

import com.karrot.demo.service.LikeService;
import com.karrot.demo.web.dto.like.AddLikeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/likes")
public class LikeApiController {
    private LikeService likeService;
    @Autowired
    public LikeApiController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping()
    public ResponseEntity addLike(@ModelAttribute @Validated AddLikeDto likeDto,
                                     BindingResult errors){
        if (errors.hasErrors()){
            log.debug(">>> 관심 목록 추가 에러 : " + errors.toString());
            return ResponseEntity.badRequest().build();
        }
        likeService.addLike(likeDto);
        return ResponseEntity.ok().build();
    }

}
