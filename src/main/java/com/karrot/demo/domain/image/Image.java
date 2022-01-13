package com.karrot.demo.domain.image;

import com.karrot.demo.domain.item.Item;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class Image {
    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull private String fileName;
    @NotNull private String path;

    @NotNull private Long size;

    @ManyToOne
    @NotNull private Item item;

}
