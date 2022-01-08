package com.karrot.demo.domain.user;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
/*@Table(
        name="user",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"email","phone"}
                )
        }
)*/
public class User {
    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="email", unique = true)
    private String email;

    private String password;
    private String username;

    private String phone;
    private String nickname;

}
