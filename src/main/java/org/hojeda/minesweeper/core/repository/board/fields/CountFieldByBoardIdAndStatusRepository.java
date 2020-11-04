package org.hojeda.minesweeper.core.repository.board.fields;

import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;

public interface CountFieldByBoardIdAndStatusRepository {

    Integer execute(Long boardId, BoardFieldStatus status);
}
