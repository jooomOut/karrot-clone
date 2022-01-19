package com.karrot.demo.domain.interest;

import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.user.Account;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Interest {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Account user;
}
