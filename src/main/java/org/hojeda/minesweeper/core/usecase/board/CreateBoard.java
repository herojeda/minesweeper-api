package org.hojeda.minesweeper.core.usecase.board;

import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;

import java.util.Collections;

public class CreateBoard {

    public Board execute(BasicBoardData basicBoardData) {



        return Board.newBuilder()
            .withStatus(BoardStatus.CREATED)
            .withFields(Collections.emptySet())
            .build();
    }
}
