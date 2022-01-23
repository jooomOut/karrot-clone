package com.karrot.demo.web.api;

import com.karrot.demo.service.ItemService;
import com.karrot.demo.util.SecurityUtils;
import com.karrot.demo.web.dto.item.ItemDto;
import com.karrot.demo.web.dto.item.ItemUploadDto;
import com.karrot.demo.web.dto.item.ItemPreviewDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<List<ItemPreviewDto>> getItemList(@RequestParam Long lastId,
                                                            @RequestParam(required = false, defaultValue = "5") int size){
        List<ItemPreviewDto> itemDtos = itemService.getItemsPreview(lastId, size);
        return ResponseEntity.ok().body(itemDtos);
    }
    /**
     * 중고거래 업로드
     * @Param : List<MultipartFile>, ItemDto
     * @Response : 200(성공) or 400
     * */
    @PostMapping
    public ResponseEntity uploadItem(@RequestPart(required = false) List<MultipartFile> uploadImages,
                                     @ModelAttribute @Valid ItemUploadDto itemDto,
                                     BindingResult errors){
        if (errors.hasErrors()){
            String errorStr = errors.getErrorCount() > 0 ? errors.getAllErrors().get(0).getDefaultMessage() : "알 수 없는 오류";
            return ResponseEntity.badRequest().body(errorStr);
        }
        try {
            itemService.uploadItem(uploadImages, itemDto);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{itemId}")
    public ResponseEntity updateItem(@PathVariable Long itemId,
                                    @RequestPart(required = false) List<MultipartFile> uploadImages,
                                     @ModelAttribute @Valid ItemUploadDto itemDto,
                                     BindingResult errors){
        if (errors.hasErrors()){
            String errorStr = errors.getErrorCount() > 0 ? errors.getAllErrors().get(0).getDefaultMessage() : "알 수 없는 오류";
            return ResponseEntity.badRequest().body(errorStr);
        }
        try {
            itemService.updateItem(itemId, uploadImages, itemDto);
        } catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
        log.info("item is uploaded");
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
        log.info("item is deleted - "+ itemId);
        return ResponseEntity.ok().build();
    }
}
