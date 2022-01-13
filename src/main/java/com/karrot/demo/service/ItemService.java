package com.karrot.demo.service;

import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemRepository;
import com.karrot.demo.domain.item.ItemStatus;
import com.karrot.demo.web.dto.item.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ItemService {

    private ItemRepository itemRepository;
    private ImageService fileService;

    @Autowired
    public ItemService(ItemRepository itemRepository, ImageService fileService) {
        this.itemRepository = itemRepository;
        this.fileService = fileService;
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }
    public List<Item> getItems(String place){
        return itemRepository.findAllByPlace(place);
    }

    @Transactional
    public void uploadItem(List<MultipartFile> files, ItemDto itemDto){

        Item item = itemRepository.save(toEntity(itemDto));

        //TODO 이미지 업로드
        fileService.upload(item, files);

    }

    private Item toEntity(ItemDto itemDto){
        return Item.builder()
                .title(itemDto.getTitle())
                .mainText(itemDto.getMainText())
                .price(itemDto.getPrice())
                .uploaderId(itemDto.getUploaderId())
                .status(ItemStatus.SALE)
                .whenUploaded(LocalDateTime.now())
                .build();
    }
}
