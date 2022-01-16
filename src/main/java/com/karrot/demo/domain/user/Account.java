package com.karrot.demo.domain.user;

import com.karrot.demo.domain.image.UserProfileImage;
import com.karrot.demo.domain.item.Item;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="user")
public class Account {
    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="email", unique = true)
    private String email;

    private String password;
    private String username;

    private String phone;
    private String nickname;

    private String role;

    @OneToOne(mappedBy = "user")
    private UserProfileImage image;

    @OneToMany(mappedBy = "uploader", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

}
