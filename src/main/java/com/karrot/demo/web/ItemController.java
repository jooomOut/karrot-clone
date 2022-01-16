package com.karrot.demo.web;

import com.karrot.demo.domain.item.ItemCategory;
import com.karrot.demo.service.ItemService;
import com.karrot.demo.util.SecurityUtils;
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
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String getItemsPage(Model model){

        model.addAttribute("items", itemService.getItems());

        return "/items/list";
    }

    @GetMapping("/{itemId}")
    public String getItemPage(Model model,
                              @PathVariable Long itemId){
        model.addAttribute("item", itemService.getItemDtoBy(itemId));

        return "/items/item-view";
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
        return "/items/my-items";
    }


}
