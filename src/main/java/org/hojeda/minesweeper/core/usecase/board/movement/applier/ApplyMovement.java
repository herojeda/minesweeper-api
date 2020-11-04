package org.hojeda.minesweeper.core.usecase.board.movement.applier;

import org.hojeda.minesweeper.core.entity.board.field.BoardField;

import java.util.Set;

public interface ApplyMovement {

    Set<BoardField> execute(BoardField boardField);

}
