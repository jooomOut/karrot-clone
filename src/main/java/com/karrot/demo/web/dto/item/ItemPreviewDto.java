package com.karrot.demo.web.dto.item;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.karrot.demo.domain.item.ItemStatus;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @Builder
public class ItemPreviewDto {
    private Long id;

    private String title;
    private String mainText;
    private Long price;
    private String place;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;
    private ItemStatus status;
    @Builder.Default
    private int interestCount = 0;
    @Builder.Default
    private int commentCount = 0;

    private String thumbnailPath;
}
