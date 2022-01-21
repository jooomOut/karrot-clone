package com.karrot.demo.service;

import com.karrot.demo.domain.interest.InterestRepository;
import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemCategory;
import com.karrot.demo.domain.item.ItemRepository;
import com.karrot.demo.domain.item.ItemStatus;
import com.karrot.demo.domain.user.Account;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.util.SecurityUtils;
import com.karrot.demo.web.dto.item.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemService {

    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private InterestRepository interestRepository;
    private ImageService fileService;
    @Autowired
    public ItemService(ItemRepository itemRepository, UserRepository userRepository, InterestRepository interestRepository, ImageService fileService) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;
        this.fileService = fileService;
    }

    public ItemDto getItemDtoBy(Long itemId){
        int defaultUploaderItemSize = 4;
        int defaultCommentsSize = 20;

        return getItemDtoBy(itemId, defaultUploaderItemSize, defaultCommentsSize);
    }
    public ItemDto getItemDtoBy(Long itemId, int uploaderItemSize, int commentsSize){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        item.getUploader().setItems(
                item.getUploader().getItems().stream().limit(uploaderItemSize).collect(Collectors.toList())
        );
        item.setComments(item.getComments().stream().limit(commentsSize).collect(Collectors.toList()));
        return toItemDto(item);
    }

    public List<ItemDto> getItems(){
        return itemRepository.findTop20ByOrderByIdDesc().stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());
    }

    public List<ItemDto> getItemsBy(String keyword, String category){
        List<Item> ret;
        if (keyword == null) keyword = "";
        if (category == null) {
            ret = itemRepository.findAllByTitleContains(keyword);
        } else {
            ret = itemRepository.findAllByTitleContainsAndCategory(keyword, ItemCategory.valueOf(category));
        }
        return ret.stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());
    }

    public List<ItemDto> getItemsByUserId(Long userId){
        return itemRepository.findAllByUploaderId(userId).stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());
    }

    public List<ItemDto> getItemsByUserInterest(Long userId) {
        return interestRepository.findAllByUserId(userId).stream()
                .map(interest -> toItemDto(interest.getItem()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void uploadItem(List<MultipartFile> files, ItemDto itemDto){
        Account uploader = userRepository.findById(itemDto.getUploaderId())
                .orElseThrow(EntityNotFoundException::new);

        Item item = itemRepository.save(toEntityForAdding(itemDto, uploader));
        fileService.upload(item, files);
    }

    public void updateItem(Long itemId, List<MultipartFile> uploadImages, ItemDto itemDto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        SecurityUtils.checkUser(item.getUploader().getId());
        // TODO: 이미지 처리 구현하기
        item = toEntityForEditing(item, itemDto, uploadImages);
        itemRepository.save(item);
    }

    public void updateItemStatus(Long itemId, String status) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        Long userId = SecurityUtils.getLoginUserId();
        if (!item.getUploader().getId().equals(userId)) {
            throw new AuthorizationServiceException(userId + " 는 해당 영역에 접근할 수 없습니다.");
        }
        try {
            item.setStatus(ItemStatus.valueOf(status));
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException(status + "로 변경할 수 없습니다.");
        }
        itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        Long userId = SecurityUtils.getLoginUserId();
        if (!item.getUploader().getId().equals(userId)) {
            throw new AuthorizationServiceException(userId + " 는 해당 영역에 접근할 수 없습니다.");
        }
        itemRepository.delete(item);
    }

    private Item toEntityForEditing(Item item, ItemDto itemDto, List<MultipartFile> uploadImages) {
        item.setTitle(itemDto.getTitle());
        item.setPrice(itemDto.getPrice());
        item.setMainText(itemDto.getMainText());
        item.setCategory(ItemCategory.valueOf(itemDto.getCategory()));

        return item;
    }

    private Item toEntityForAdding(ItemDto itemDto, Account account){
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
                .category(item.getCategory().getKrName())
                .uploader(item.getUploader())
                .place(item.getPlace())
                .status(item.getStatus().name())
                .images(item.getImages())
                .comments(item.getComments())
                .interests(item.getInterests())
                .build();
        dto.setWhenUploaded(item.getWhenUploaded());
        return dto;
    }
}
