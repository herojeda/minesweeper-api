package org.hojeda.minesweeper.core.repository.board.fields;

import org.hojeda.minesweeper.core.entity.board.field.BoardField;

public interface GetFieldByBoardIdAndRowAndColumnRepository {

    BoardField execute(Long boardId, Integer row, Integer column);

}
