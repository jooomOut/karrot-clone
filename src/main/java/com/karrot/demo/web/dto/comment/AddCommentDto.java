package com.karrot.demo.web.dto.comment;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter @Setter
public class AddCommentDto {
    @NotBlank(message = "내용을 비워둘 수 없습니다.")
    private String text;
    @NotNull private Long itemId;
    private Long commenterId;
}
