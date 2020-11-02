package org.hojeda.minesweeper.unit.usecase.board;

import org.hojeda.minesweeper.configuration.Context;
import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.usecase.board.CreateBoard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreateBoardTest {

    @BeforeAll
    public static void init() {
        Context.init();
    }

    @Test
    public void test() {
        var givenRowSize = 10;
        var givenColumnSize = 10;
        var givenMines = 10;
        var givenBasicData = BasicBoardData.newBuilder()
            .withBombs(givenMines)
            .withRowSize(givenRowSize)
            .withColumnSize(givenColumnSize)
            .build();

        var target = Context.getInjector().getInstance(CreateBoard.class);

        var result = target.execute(givenBasicData);


    }
}
