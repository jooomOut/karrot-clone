package com.karrot.demo.web;

import com.karrot.demo.domain.item.ItemCategory;
import com.karrot.demo.domain.item.ItemStatus;
import com.karrot.demo.service.ItemService;
import com.karrot.demo.service.InterestService;
import com.karrot.demo.util.SecurityUtils;
import com.karrot.demo.web.dto.item.ItemDto;
import com.karrot.demo.web.dto.user.UserSessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;

@Slf4j
@Controller
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;
    private InterestService interestService;
    @Autowired
    public ItemController(ItemService itemService, InterestService interestService) {
        this.itemService = itemService;
        this.interestService = interestService;
    }

    @GetMapping
    public String getItemsPage(Model model){

        model.addAttribute("items", itemService.getItems());

        return "/items/list";
    }

    @GetMapping("/{itemId}")
    public String getItemPage(Model model,
                              @PathVariable Long itemId){
        UserSessionDto user = SecurityUtils.getLoginUser();
        model.addAttribute("user", user);
        model.addAttribute("item", itemService.getItemDtoBy(itemId));
        model.addAttribute("isLiked", interestService.checkInterestedBy(itemId, user.getId()));
        model.addAttribute("statusList", ItemStatus.values());

        return "/items/item-view";
    }

    @GetMapping("/{itemId}/edit")
    public String getItemEditPage(Model model,
                              @PathVariable Long itemId){
        ItemDto item = itemService.getItemDtoBy(itemId);
        SecurityUtils.checkUser(item.getUploader().getId());

        model.addAttribute("item", item);
        model.addAttribute("user", SecurityUtils.getLoginUser());
        model.addAttribute("categories", ItemCategory.values());

        return "/items/item-edit";
    }

    @GetMapping("/user/{userId}")
    public String getItemsOfUser(Model model,
                                 @PathVariable Long userId){
        model.addAttribute("items", itemService.getItemsByUserId(userId));
        return "/items/user-items";
    }

    @GetMapping("/upload")
    public String uploadItemPage(Model model){
        model.addAttribute("categories", ItemCategory.values());
        return "items/upload";
    }

    @GetMapping("/my-items")
    public String getItemsOfMe(Model model) throws AuthenticationException {
        Long userId = SecurityUtils.getLoginUserId();
        if (userId == null){
            throw new AuthenticationException("로그인하지 않은 사용자는 접근할 수 없습니다.");
        }
        model.addAttribute("items", itemService.getItemsByUserId(userId));
        return "items/my-items";
    }


}
