package org.hojeda.minesweeper.unit.usecase.board.field;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.*;
import org.hojeda.minesweeper.configuration.Context;
import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;
import org.hojeda.minesweeper.core.usecase.board.field.GetDataToCreateBoardFields;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

public class GetDataToCreateBoardFieldsTest {

    @BeforeAll
    public static void init() {
        Context.init();
    }

    @Test
    public void when_execute_should_return_an_expeted_fields_according_to_input_data() {

        var givenRowSize = 10;
        var givenColumnSize = 10;
        var givenMines = 10;
        var givenBasicData = BasicBoardData.newBuilder()
            .withMines(givenMines)
            .withRowSize(givenRowSize)
            .withColumnSize(givenColumnSize)
            .build();

        var target = Context.getInjector().getInstance(GetDataToCreateBoardFields.class);

        var result = target.execute(givenBasicData);

        assertThat(result.size(), is(givenColumnSize * givenRowSize));

        var minesInResult = result.stream()
            .filter(field -> MineBoardField.MINE_VALUE.equals(field.getValue()))
            .collect(Collectors.toSet());
        assertThat(minesInResult.size(), is(givenMines));
    }
}
