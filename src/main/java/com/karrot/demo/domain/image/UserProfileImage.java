package com.karrot.demo.domain.image;

import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.user.Account;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("user_profile")
public class UserProfileImage extends Image{

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull private Account user;

    @Builder
    public UserProfileImage(Long id, String fileName, String path, Long size, Account user) {
        super(id, fileName,path,size);
        this.user = user;
    }

}
