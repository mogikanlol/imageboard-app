package com.mogikanlol.data.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thread_id_generator")
    @SequenceGenerator(name = "thread_id_generator", sequenceName = "thread_id_seq", allocationSize = 1)
    private Long id;

    private String title;

    private String imageName;

    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "thread", orphanRemoval = true)
    private List<Post> posts;
}
