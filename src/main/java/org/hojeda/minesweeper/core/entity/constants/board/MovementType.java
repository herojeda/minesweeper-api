package org.hojeda.minesweeper.core.entity.constants.board;

import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum MovementType {
    OPEN(BoardFieldStatus.OPENED),
    FLAG(BoardFieldStatus.FLAGGED),
    QUESTION(BoardFieldStatus.QUESTIONED);

    private BoardFieldStatus fieldStatus;

    MovementType(BoardFieldStatus fieldStatus) {
        this.fieldStatus = fieldStatus;
    }

    public BoardFieldStatus getFieldStatus() {
        return fieldStatus;
    }

    public static MovementType getByName(String name) {
        return Arrays.stream(MovementType.values())
            .filter(boardStatus -> boardStatus.name()
                .toLowerCase().equals(name.toLowerCase())
            )
            .collect(Collectors.toList())
            .stream().findFirst()
            .orElseThrow();
    }
}
