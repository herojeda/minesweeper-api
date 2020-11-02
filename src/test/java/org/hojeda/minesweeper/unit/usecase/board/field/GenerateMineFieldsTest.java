package org.hojeda.minesweeper.unit.usecase.board.field;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.usecase.board.field.GenerateBombFields;
import org.junit.jupiter.api.Test;

public class GenerateMineFieldsTest {

    @Test
    public void when_execute_should_return_the_same_number_of_passed_bombs() {
        var target = new GenerateBombFields();

        var givenRowSize = 10;
        var givenColumnSize = 10;
        var givenBombs = 10;
        var givenBasicData = BasicBoardData.newBuilder()
            .withBombs(givenBombs)
            .withRowSize(givenRowSize)
            .withColumnSize(givenColumnSize)
            .build();

        var result = target.execute(givenBasicData);

        System.out.println(result);

        MatcherAssert.assertThat(result.size(), Matchers.equalTo(givenBombs));
    }
}
