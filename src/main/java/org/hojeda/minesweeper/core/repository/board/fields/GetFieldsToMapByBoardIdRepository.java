package org.hojeda.minesweeper.core.repository.board.fields;

import org.hojeda.minesweeper.core.entity.board.field.BoardField;

import java.util.Map;

public interface GetFieldsToMapByBoardIdRepository {

    Map<Integer, Map<Integer, BoardField>> execute(Long boardId);

}
