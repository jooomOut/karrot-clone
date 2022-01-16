package com.karrot.demo.domain.image;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Image {
    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    protected Long id;

    @NotNull protected String fileName;
    @NotNull protected String path;

    @NotNull protected Long size;

}
