package com.karrot.demo.domain.image;

import com.karrot.demo.domain.item.Item;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("item")
public class ItemImage extends Image{

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull private Item item;

    @Builder
    public ItemImage(Long id, String fileName, String path, Long size, Item item) {
        super(id, fileName,path,size);
        this.item = item;
    }
}
