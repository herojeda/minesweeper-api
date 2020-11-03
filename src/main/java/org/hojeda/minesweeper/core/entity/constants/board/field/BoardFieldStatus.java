package org.hojeda.minesweeper.core.entity.constants.board.field;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum BoardFieldStatus {

    CLOSED(1L),
    FLAGGED(2L),
    QUESTIONED(3L),
    OPENED(4L);

    private Long id;

    BoardFieldStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static BoardFieldStatus getById(Long id) {
        return Arrays.stream(BoardFieldStatus.values())
            .filter(boardFieldStatus -> boardFieldStatus.id.equals(id))
            .collect(Collectors.toList())
            .stream().findFirst()
            .orElseThrow();
    }
}
