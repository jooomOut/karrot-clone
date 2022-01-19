package com.karrot.demo.service;

import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemRepository;
import com.karrot.demo.domain.interest.Interest;
import com.karrot.demo.domain.interest.InterestRepository;
import com.karrot.demo.domain.user.Account;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.web.dto.like.AddInterestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class InterestService {

    private InterestRepository interestRepository;
    private UserRepository userRepository;
    private ItemRepository itemRepository;
    @Autowired
    public InterestService(InterestRepository interestRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.interestRepository = interestRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }
    public boolean checkInterestedBy(Long itemId, Long userId){
        if (itemId != null && userId != null){
            return interestRepository.existsByItemIdAndUserId(itemId,userId);
        }
        return false;
    }

    public void addInterest(AddInterestDto dto){
        Account user = userRepository.getById(dto.getUserId());
        Item item = itemRepository.getById(dto.getItemId());
        Interest interest = Interest.builder()
                .user(user)
                .item(item)
                .build();
        try {
            interestRepository.save(interest);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("no user or item id");
        }
    }
}
