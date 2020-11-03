package org.hojeda.minesweeper.core.repository.board;

import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.board.BoardCreationData;

public interface SaveCompleteBoardRepository {

    Board execute(BoardCreationData boardCreationData);

}
