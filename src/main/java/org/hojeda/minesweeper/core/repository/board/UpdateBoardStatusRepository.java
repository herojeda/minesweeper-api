package org.hojeda.minesweeper.core.repository.board;

import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;

public interface UpdateBoardStatusRepository {

    Integer execute(Long boardId, BoardStatus newStatus);

}
