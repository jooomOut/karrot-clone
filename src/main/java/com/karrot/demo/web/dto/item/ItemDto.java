package com.karrot.demo.web.dto.item;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDto {
    @NotNull private String title;
    @NotNull private String mainText;
    @NotNull private Long price;
    @NotNull private String category;

    private Long uploaderId;
    private String place;

}
