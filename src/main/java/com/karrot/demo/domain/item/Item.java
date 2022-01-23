package com.karrot.demo.domain.item;

import com.karrot.demo.domain.comment.Comment;
import com.karrot.demo.domain.image.ItemImage;
import com.karrot.demo.domain.interest.Interest;
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
    @Enumerated(EnumType.STRING)
    @NotNull private ItemCategory category;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @NotNull private ItemStatus status = ItemStatus.SALE;
    @NotNull private LocalDateTime whenUploaded;

    @ManyToOne
    @NotNull private Account uploader;

    @Builder.Default
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Interest> interests = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ItemImage> images = new ArrayList<>();

}
