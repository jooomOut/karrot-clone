package com.karrot.demo.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.karrot.demo.domain.comment.Comment;
import com.karrot.demo.domain.image.UserProfileImage;
import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.interest.Interest;
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

    @JsonIgnore
    private String password;
    private String username;

    private String phone;
    private String nickname;

    private String role;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserProfileImage image;

    @OrderBy("id desc") @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Interest> interests;

    @OrderBy("id desc") @JsonIgnore
    @OneToMany(mappedBy = "uploader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

    @OrderBy("id desc") @JsonIgnore
    @OneToMany(mappedBy = "commenter", fetch = FetchType.LAZY)
    private List<Comment> comments;

}
