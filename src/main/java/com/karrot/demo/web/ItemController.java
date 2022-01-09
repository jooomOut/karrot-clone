package com.karrot.demo.web;

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

import java.security.Security;

@Slf4j
@RestController
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

        return "items/list";
    }

    @GetMapping("/upload")
    public String uploadItemPage(){
        return "items/upload";
    }

    @PostMapping
    public ResponseEntity uploadItem(@ModelAttribute @Validated ItemDto itemDto,
                                     BindingResult errors){
        if (errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        itemDto.setUploaderId(SecurityUtils.getLoginUserId());
        itemService.uploadItem(itemDto);

        return ResponseEntity.ok().build();
    }

}
