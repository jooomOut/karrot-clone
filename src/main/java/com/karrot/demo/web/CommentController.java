package com.karrot.demo.web;

import com.karrot.demo.service.CommentService;
import com.karrot.demo.service.ItemService;
import com.karrot.demo.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/comments")
public class CommentController {

    private ItemService itemService;
    private CommentService commentService;
    @Autowired
    public CommentController(ItemService itemService, CommentService commentService) {
        this.itemService = itemService;
        this.commentService = commentService;
    }

    @GetMapping("/items/{itemId}")
    public String getCommentsPage(Model model,
                               @PathVariable Long itemId){

        model.addAttribute("item", itemService.getItemDtoBy(itemId));
        model.addAttribute("user", SecurityUtils.getLoginUser());

        return "/comments/view";
    }
    @GetMapping("/writing/{itemId}")
    public String getPageForWriting(Model model,
                                    @PathVariable Long itemId,
                                    @RequestParam(required = false) Long commentId){
        model.addAttribute("itemId", itemId);
        model.addAttribute("userId", SecurityUtils.getLoginUserId());
        if (commentId != null){
            model.addAttribute("commentText", commentService.getCommentText(commentId));
        }

        return "/comments/writing";
    }
}
