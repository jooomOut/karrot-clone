package com.karrot.demo.domain.user;

import com.karrot.demo.domain.comment.Comment;
import com.karrot.demo.domain.image.UserProfileImage;
import com.karrot.demo.domain.item.Item;
import lombok.*;

import javax.persistence.*;
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

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserProfileImage image;

    @OneToMany(mappedBy = "uploader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

    @OneToMany(mappedBy = "commenter", fetch = FetchType.LAZY)
    private List<Comment> comments;

}
