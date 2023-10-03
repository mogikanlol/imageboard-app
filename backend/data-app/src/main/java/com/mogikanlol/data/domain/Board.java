package com.mogikanlol.data.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class Board {

    @Id
    private String id;

    private String title;

    @Enumerated(EnumType.STRING)
    private BoardGenre genre;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Thread> threads = new ArrayList<>();

    public enum BoardGenre {
        JAPAN,
        INTERESTS,
        MOVIES,
        MUSIC,
        CREATIVE,
        GAMES
    }
}
