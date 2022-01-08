package com.karrot.demo.domain.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    private String password;
    private String username;
    private String phone;
    private String nickname;

}
