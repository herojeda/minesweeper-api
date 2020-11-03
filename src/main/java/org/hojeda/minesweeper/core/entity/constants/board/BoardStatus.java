package org.hojeda.minesweeper.core.entity.constants.board;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum BoardStatus {

    CREATED(1L),
    PLAYING(2L),
    WINNED(3L),
    LOST(4L);

    private Long id;

    BoardStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static BoardStatus getById(Long id) {
        return Arrays.stream(BoardStatus.values())
            .filter(boardStatus -> boardStatus.id.equals(id))
            .collect(Collectors.toList())
            .stream().findFirst()
            .orElse(null);

    }

}
