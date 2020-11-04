package org.hojeda.minesweeper.core.repository.board;

import org.hojeda.minesweeper.core.entity.board.Board;

public interface GetBoardByIdRepository {

    Board execute(Long id);

}
