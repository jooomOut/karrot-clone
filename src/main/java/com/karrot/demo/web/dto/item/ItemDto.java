package com.karrot.demo.web.dto.item;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDto {
    @NotNull private String title;
    @NotNull private String mainText;
    @NotNull private String category;
    private Long price;
    @NotNull private String place;
    @NotNull private Long uploaderId;
}
