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
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/items")
public class ItemApiController {

    @Autowired
    private ItemService itemService;

    /*
    * lastId 보다 작은 id 값을 가진 게시물들을 가져온다.
    * */
    @GetMapping
    public ResponseEntity<List<ItemDto>> getItemList(@RequestParam Long lastId,
                                      @RequestParam(required = false, defaultValue = "5") int size){
        List<ItemDto> itemDtos = itemService.getItems(lastId, size);
        return ResponseEntity.ok().body(itemDtos);
    }
    /**
     * 중고거래 업로드
     * @Param : List<MultipartFile>, ItemDto
     * @Response : 200(성공) or 400
     * */
    @PostMapping
    public ResponseEntity uploadItem(@RequestPart(required = false) List<MultipartFile> uploadImages,
                                     @ModelAttribute @Valid ItemDto itemDto,
                                     BindingResult errors){
        if (errors.hasErrors()){
            log.debug(">>> 중고거래 업로드 에러 : " + errors.toString());
            return ResponseEntity.badRequest().build();
        }

        try {
            itemDto.setUploaderId(SecurityUtils.getLoginUserId());
            itemService.uploadItem(uploadImages, itemDto);
        } catch (IllegalArgumentException e){
            log.debug("USER ID를 찾을 수 없음 : " + itemDto.getUploaderId());
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{itemId}")
    public ResponseEntity updateItem(@PathVariable Long itemId,
                                    @RequestPart(required = false) List<MultipartFile> uploadImages,
                                     @ModelAttribute @Validated ItemDto itemDto,
                                     BindingResult errors){
        if (errors.hasErrors()){
            log.debug(">>> 중고거래 게시글 수정 에러 : " + errors.toString());
            return ResponseEntity.badRequest().build();
        }
        try {
            itemService.updateItem(itemId, uploadImages, itemDto);
        } catch (IllegalArgumentException e){
            log.debug("USER ID를 찾을 수 없음 : " + itemDto.getUploaderId());
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity updateItemStatus(@PathVariable Long itemId,
                                     @RequestParam String status){
        try {
            itemService.updateItemStatus(itemId, status);
        } catch (AuthorizationServiceException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable Long itemId){
        try {
            itemService.deleteItem(itemId);
        } catch (AuthorizationServiceException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
