package com.karrot.demo.service;

import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemRepository;
import com.karrot.demo.domain.interest.Interest;
import com.karrot.demo.domain.interest.InterestRepository;
import com.karrot.demo.domain.user.Account;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.web.dto.like.AddInterestDto;
import com.karrot.demo.web.dto.like.CheckInterestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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
    /*
    * 해당 user가 item을 관심 목록에 추가했는지 확인
    * */
    public CheckInterestDto checkInterestedBy(Long itemId, Long userId){
        Interest interest = findInterestBy(itemId, userId);
        return makeCheckInterestBy(interest);
    }

    public void addInterest(AddInterestDto dto){
        Account user = userRepository.getById(dto.getUserId());
        Item item = itemRepository.getById(dto.getItemId());
        Interest interest = makeInterestBy(user, item);

        interestRepository.save(interest); // throw DataIntegrityViolation
    }

    public void delete(Long interestId) {
        Interest interest = interestRepository.findById(interestId)
                .orElseThrow(() -> new EntityNotFoundException("interest is not found with id : " +interestId));
        interestRepository.delete(interest);
    }

    /*
    * TODO: null 리턴이 좋은 방법은 아닌 것 같음
    *  InterestNotFoundException으로 변경하자.
    * */
    private Interest findInterestBy(Long itemId, Long userId){
        return interestRepository.findByItemIdAndUserId(itemId, userId)
                .orElseThrow(null);
    }

    private Interest makeInterestBy(Account user, Item item){
        return Interest.builder()
                .user(user)
                .item(item)
                .build();
    }
    private CheckInterestDto makeCheckInterestBy(Interest interest) {
        return CheckInterestDto.builder()
                .id(interest.getId())
                .isInterestedBy(interest.getId() != null)
                .build();
    }
}
