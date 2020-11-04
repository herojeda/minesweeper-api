package org.hojeda.minesweeper.core.repository.board.fields;

import org.hojeda.minesweeper.core.entity.board.field.BoardField;

import java.util.Set;

public interface GetFieldsByBoardIdRepository {

    Set<BoardField> execute(Long boardId);

}
