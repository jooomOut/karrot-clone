package com.karrot.demo.web.api;

import com.karrot.demo.service.ItemService;
import com.karrot.demo.web.dto.item.ItemPreviewDto;
import com.karrot.demo.web.dto.item.ItemUploadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ItemPreviewDto>> getItemList(@RequestParam(required = false) Long lastId,
                                                            @RequestParam(required = false, defaultValue = "5") int size){
        lastId = lastId == null ? Long.MAX_VALUE : lastId;
        List<ItemPreviewDto> itemDtos = itemService.getItemsPreview(lastId, size);
        return ResponseEntity.ok().body(itemDtos);
    }
    /**
     * 중고거래 업로드
     * @Param : List<MultipartFile>, ItemDto
     * @Response : 200(성공) or 400 (DataIntegrityViilationException
     * */
    @PostMapping
    public ResponseEntity uploadItem(@RequestPart(required = false) List<MultipartFile> uploadImages,
                                     @ModelAttribute @Valid ItemUploadDto itemDto){

        itemService.uploadItem(uploadImages, itemDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{itemId}")
    public ResponseEntity updateItem(@PathVariable Long itemId,
                                    @RequestPart(required = false) List<MultipartFile> uploadImages,
                                     @ModelAttribute @Valid ItemUploadDto itemDto){

        itemService.updateItem(itemId, uploadImages, itemDto);

        log.info("item is uploaded");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity updateItemStatus(@PathVariable Long itemId,
                                     @RequestParam String status){

        itemService.updateItemStatus(itemId, status);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
        log.info("item is deleted - "+ itemId);
        return ResponseEntity.ok().build();
    }
}
