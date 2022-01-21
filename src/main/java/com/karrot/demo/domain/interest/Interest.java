package com.karrot.demo.domain.interest;

import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.user.Account;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"itemId","userId"}
                )
        }
)
public class Interest {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name="itemId")
    @ManyToOne
    private Item item;
    @JoinColumn(name="userId")
    @ManyToOne
    private Account user;
}
