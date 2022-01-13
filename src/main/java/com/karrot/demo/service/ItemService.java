package com.karrot.demo.service;

import com.karrot.demo.domain.item.Item;
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

    public List<Item> getItems(){
        return itemRepository.findAll();
    }
    public List<Item> getItems(String place){
        return itemRepository.findAllByPlace(place);
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
                .uploader(account)
                .status(ItemStatus.SALE)
                .whenUploaded(LocalDateTime.now())
                .build();
    }
}
