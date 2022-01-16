package com.karrot.demo.web.dto.item;

import com.karrot.demo.domain.image.ItemImage;
import com.karrot.demo.domain.user.Account;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.*;

@Getter @Setter @Builder
public class ItemDto {
    private Long id;

    @NotNull private String title;
    @NotNull private String mainText;
    @NotNull private Long price;
    @NotNull private String category;
    @NotNull private String status;

    private Long uploaderId;
    private Account uploader;

    private String place;
    private String whenUploaded;

    private List<ItemImage> images;

    public void setWhenUploaded(LocalDateTime whenUploaded){
        LocalDateTime now = LocalDateTime.now();

        Long between = SECONDS.between(whenUploaded, now);
        if (between < 60){ // 60초
            this.whenUploaded = between +"초 전";
        } else if (between < 60 * 60){ // 60분
            this.whenUploaded = MINUTES.between(whenUploaded, now) + "분 전";
        } else if (between < 60 * 60 * 24){ // 24시간
            this.whenUploaded = HOURS.between(whenUploaded, now) + "시간 전";
        } else if (between < 60 * 60 * 24 * 30) { // 30일
            this.whenUploaded = DAYS.between(whenUploaded, now) + "일 전";
        } else if (between < 60 * 60 * 24 * 30) { // 12 개월
            this.whenUploaded = MONTHS.between(whenUploaded, now) + "개월 전";
        } else {
            this.whenUploaded = YEARS.between(whenUploaded, now) + "년 전";
        }

    }
}
