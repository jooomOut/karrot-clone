package com.karrot.demo.domain.user;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(
        name="user",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"email","phone"}
                )
        }
)
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="email")
    private String email;
    private String password;
    private String username;
    @Column(name="phone")
    private String phone;
    private String nickname;

}
