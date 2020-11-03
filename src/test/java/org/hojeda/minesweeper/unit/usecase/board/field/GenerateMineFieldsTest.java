package org.hojeda.minesweeper.unit.usecase.board.field;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.usecase.board.field.GenerateMineFields;
import org.junit.jupiter.api.Test;

public class GenerateMineFieldsTest {

    @Test
    public void when_execute_should_return_the_same_number_of_passed_miness() {
        var target = new GenerateMineFields();

        var givenRowSize = 10;
        var givenColumnSize = 10;
        var givenMiness = 10;
        var givenBasicData = BasicBoardData.newBuilder()
            .withMines(givenMiness)
            .withRowSize(givenRowSize)
            .withColumnSize(givenColumnSize)
            .build();

        var result = target.execute(givenBasicData);

        System.out.println(result);

        MatcherAssert.assertThat(result.size(), Matchers.equalTo(givenMiness));
    }
}
