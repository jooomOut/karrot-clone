package com.karrot.demo.web.dto.item;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ItemEditDto {

    @NotNull private String title;
    @NotNull private String mainText;
    @NotNull private Long price;
    @NotNull private String category;

    private String place;

}
