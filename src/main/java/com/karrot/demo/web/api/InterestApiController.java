package com.karrot.demo.web.api;

import com.karrot.demo.service.InterestService;
import com.karrot.demo.web.dto.like.AddInterestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/interests")
public class InterestApiController {
    private InterestService interestService;
    @Autowired
    public InterestApiController(InterestService interestService) {
        this.interestService = interestService;
    }

    @PostMapping()
    public ResponseEntity addInterest(@ModelAttribute @Validated AddInterestDto interestDto,
                                     BindingResult errors){
        
        interestService.addInterest(interestDto);
        log.info("item is added to interested list : id - " + interestDto.getItemId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{interestId}")
    public ResponseEntity deleteInterest(@PathVariable Long interestId){
        interestService.delete(interestId);
        return ResponseEntity.ok().build();
    }
}
