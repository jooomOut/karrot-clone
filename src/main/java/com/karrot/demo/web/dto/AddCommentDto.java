package com.karrot.demo.web.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddCommentDto {
    @NotNull private String text;
    @NotNull private Long itemId;
    private Long commenterId;
}
