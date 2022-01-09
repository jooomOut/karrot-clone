package com.karrot.demo.domain.item;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class Item {
    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull private String title;
    @NotNull private String mainText;
    @NotNull private Long price;
    @NotNull private String place;
    @NotNull private Long uploaderId;
    @NotNull private ItemStatus status = ItemStatus.SALE;
    @NotNull private LocalDateTime whenUploaded;

}
