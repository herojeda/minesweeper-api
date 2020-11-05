package org.hojeda.minesweeper.core.repository.board;

import org.hojeda.minesweeper.core.entity.board.Board;

import java.util.List;

public interface GetBoardsByUserIdRepository {

    List<Board> execute(Long userId);

}
