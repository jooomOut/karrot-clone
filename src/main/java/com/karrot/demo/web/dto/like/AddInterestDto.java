package com.karrot.demo.web.dto.like;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddInterestDto {
    private Long id;
    @NotNull private Long itemId;
    @NotNull private Long userId;
}
