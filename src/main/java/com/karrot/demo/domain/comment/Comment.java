package com.karrot.demo.domain.comment;

import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.user.Account;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class Comment {
    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private String text;

    @ManyToOne
    @NotNull private Account commenter;
    @ManyToOne
    @NotNull private Item item;

    @NotNull private LocalDateTime createdAt;
}
