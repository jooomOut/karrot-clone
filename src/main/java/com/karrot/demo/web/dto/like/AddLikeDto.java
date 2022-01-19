package com.karrot.demo.web.dto.like;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class AddLikeDto {
    private Long id;
    @NotNull private Long itemId;
    @NotNull private Long userId;
}
