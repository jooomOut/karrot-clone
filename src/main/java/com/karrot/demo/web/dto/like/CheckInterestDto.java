package com.karrot.demo.web.dto.like;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CheckInterestDto {
    private Long id;
    boolean isInterestedBy = false;
}
