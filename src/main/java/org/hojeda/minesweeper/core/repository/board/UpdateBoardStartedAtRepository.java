package org.hojeda.minesweeper.core.repository.board;

import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;

import java.time.LocalDateTime;

public interface UpdateBoardStartedAtRepository {

    Integer execute(Long boardId, LocalDateTime startedAt);

}
