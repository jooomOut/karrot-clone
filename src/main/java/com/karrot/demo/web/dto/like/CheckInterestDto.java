package com.karrot.demo.web.dto.like;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CheckInterestDto {
    private Long id;
    boolean isInterestedBy = false;
}
