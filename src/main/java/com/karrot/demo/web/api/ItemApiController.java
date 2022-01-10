package com.karrot.demo.web.api;

import com.karrot.demo.exception.DuplicateUserException;
import com.karrot.demo.service.ItemService;
import com.karrot.demo.service.UserService;
import com.karrot.demo.util.SecurityUtils;
import com.karrot.demo.web.dto.item.ItemDto;
import com.karrot.demo.web.dto.user.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/items")
public class ItemApiController {

    @Autowired
    private ItemService itemService;
    /**
     * 중고거래 업로드
     * @Param : List<MultipartFile>, ItemDto
     * @Response : 200(성공) or 400
     * */
    @PostMapping
    public ResponseEntity uploadItem(@RequestPart List<MultipartFile> images,
                                     @ModelAttribute @Validated ItemDto itemDto,
                                     BindingResult errors){
        if (errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        itemDto.setUploaderId(SecurityUtils.getLoginUserId());
        itemService.uploadItem(images, itemDto);

        return ResponseEntity.ok().build();
    }
}
