package com.karrot.demo.web.dto.item;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter @Builder
public class ItemUploadDto {

    @NotBlank(message = "제목을 비워둘 수 없습니다.")
    private String title;
    @NotBlank@NotBlank(message = "본문을 비워둘 수 없습니다.")
    private String mainText;

    @PositiveOrZero(message = "숫자를 다시 입력해주세요.")
    private Long price;
    @NotNull private String category;

    private String place;

}
