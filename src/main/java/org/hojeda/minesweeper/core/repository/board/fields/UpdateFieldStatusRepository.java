package org.hojeda.minesweeper.core.repository.board.fields;

import org.hojeda.minesweeper.core.entity.board.BoardMovement;

public interface UpdateFieldStatusRepository {

    Integer execute(BoardMovement boardMovement);

}
