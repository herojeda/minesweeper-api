package org.hojeda.minesweeper.core.repository.board;

import java.time.LocalDateTime;

public interface UpdateBoardFinishedAtRepository {

    Integer execute(Long boardId, LocalDateTime finishedAt);

}
