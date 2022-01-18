package com.karrot.demo.web.dto.comment;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class AddCommentDto {
    @NotNull private String text;
    @NotNull private Long itemId;
    private Long commenterId;
}
