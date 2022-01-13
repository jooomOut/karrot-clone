package com.karrot.demo.web;

import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemCategory;
import com.karrot.demo.service.ItemService;
import com.karrot.demo.util.SecurityUtils;
import com.karrot.demo.web.dto.item.ItemDto;
import com.karrot.demo.web.dto.user.UserSessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Security;
import java.util.List;

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
        log.info("get single item page - %d", itemId);
        Item item = itemService.getItemBy(itemId);
        model.addAttribute("item", item);

        return "/items/item-view";
    }
    @GetMapping("/upload")
    public String uploadItemPage(Model model){
        model.addAttribute("categories", ItemCategory.values());

        return "items/upload";
    }


}
