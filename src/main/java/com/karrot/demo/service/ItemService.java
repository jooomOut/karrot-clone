package com.karrot.demo.service;

import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemCategory;
import com.karrot.demo.domain.item.ItemRepository;
import com.karrot.demo.domain.item.ItemStatus;
import com.karrot.demo.domain.user.Account;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.web.dto.item.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemService {

    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private ImageService fileService;
    @Autowired
    public ItemService(ItemRepository itemRepository, UserRepository userRepository, ImageService fileService) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.fileService = fileService;
    }
    public ItemDto getItemDtoBy(Long itemId){
        int defaultUploaderItemSize = 4;
        return getItemDtoBy(itemId, defaultUploaderItemSize);
    }
    public ItemDto getItemDtoBy(Long itemId, int uploaderItemSize){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(IllegalArgumentException::new);
        item.getUploader().setItems(
                item.getUploader().getItems().stream().limit(uploaderItemSize).collect(Collectors.toList())
        );
        return toItemDto(item);
    }

    public List<ItemDto> getItems(){
        return itemRepository.findAll().stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());
    }
    public List<ItemDto> getItemsByUserId(Long userId){
        return itemRepository.findAllByUploaderId(userId).stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public void uploadItem(List<MultipartFile> files, ItemDto itemDto){
        Account uploader = userRepository.findById(itemDto.getUploaderId())
                .orElseThrow(IllegalArgumentException::new);

        Item item = itemRepository.save(toEntity(itemDto, uploader));
        fileService.upload(item, files);

    }

    private Item toEntity(ItemDto itemDto, Account account){
        return Item.builder()
                .title(itemDto.getTitle())
                .mainText(itemDto.getMainText())
                .price(itemDto.getPrice())
                .category(ItemCategory.valueOf(itemDto.getCategory()))
                .uploader(account)
                .status(ItemStatus.SALE)
                .whenUploaded(LocalDateTime.now())
                .build();
    }
    private ItemDto toItemDto(Item item){
        ItemDto dto = ItemDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .mainText(item.getMainText())
                .price(item.getPrice())
                .category(item.getCategory().name())
                .uploader(item.getUploader())
                .place(item.getPlace())
                .images(item.getImages())
                .build();
        dto.setWhenUploaded(item.getWhenUploaded());
        return dto;
    }
}
