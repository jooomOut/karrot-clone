package com.karrot.demo.domain.item;

import com.karrot.demo.domain.image.ItemImage;
import com.karrot.demo.domain.user.Account;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @NotNull private Account uploader;

    @Enumerated(EnumType.STRING)
    @NotNull private ItemCategory category;
    @Enumerated(EnumType.STRING)
    @NotNull private ItemStatus status = ItemStatus.SALE;
    @NotNull private LocalDateTime whenUploaded;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemImage> images = new ArrayList<>();

}
