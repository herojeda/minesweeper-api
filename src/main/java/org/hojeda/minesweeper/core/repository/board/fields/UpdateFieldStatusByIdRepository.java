package org.hojeda.minesweeper.core.repository.board.fields;

import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;

public interface UpdateFieldStatusByIdRepository {

    Integer execute(Long fieldId, BoardFieldStatus status);

}
