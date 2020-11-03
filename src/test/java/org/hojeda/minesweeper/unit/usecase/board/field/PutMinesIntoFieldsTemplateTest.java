package org.hojeda.minesweeper.unit.usecase.board.field;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.*;

import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;
import org.hojeda.minesweeper.core.usecase.board.field.PutMineIntoFieldsTemplate;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PutMinesIntoFieldsTemplateTest {

    @Test
    public void when_execute_with_correct_params_then_should_return_template_with_mines_and_increment_adyacent_field_counter() {
        var givenRowSize = 10;
        var givenColumnSize = 10;
        Map<Integer, Map<Integer, Integer>> givenFieldsTemplate = IntStream.range(0, givenRowSize)
            .boxed()
            .collect(Collectors.toMap(
                rowIdx -> rowIdx,
                rowIdx -> IntStream.range(0, givenColumnSize)
                    .boxed()
                    .collect(Collectors.toMap(columnIdx -> columnIdx, column -> 0))
            ));
        var givenMinesRowIdx = 5;
        var givenMinesColumnIdx = 6;
        var givenMines = MineBoardField.newBuilder()
            .withRowNumber(givenMinesRowIdx)
            .withColumnNumber(givenMinesColumnIdx)
            .build();

        var target = new PutMineIntoFieldsTemplate();

        var result = target.execute(givenMines, givenFieldsTemplate);

        assertThat(result.size(), is(givenFieldsTemplate.size()));
        result.entrySet().stream().forEach(entry -> assertThat(entry.getValue().size(), is(givenFieldsTemplate.get(entry.getKey()).size())));
        assertThat(result.get(givenMinesRowIdx).get(givenMinesColumnIdx), is(MineBoardField.MINE_VALUE));

        // Assert adyacent count
        final var expectedCount = 1;
        assertThat(result.get(givenMinesRowIdx - 1).get(givenMinesColumnIdx - 1), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx - 1).get(givenMinesColumnIdx), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx - 1).get(givenMinesColumnIdx + 1), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx).get(givenMinesColumnIdx - 1), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx).get(givenMinesColumnIdx + 1), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx + 1).get(givenMinesColumnIdx - 1), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx + 1).get(givenMinesColumnIdx ), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx + 1).get(givenMinesColumnIdx + 1), is(expectedCount));
    }

    @Test
    public void when_execute_with_mines_at_start_then_should_return_template_with_mines_and_increment_adyacent_field_counter() {
        var givenRowSize = 10;
        var givenColumnSize = 10;
        Map<Integer, Map<Integer, Integer>> givenFieldsTemplate = IntStream.range(0, givenRowSize)
            .boxed()
            .collect(Collectors.toMap(
                rowIdx -> rowIdx,
                rowIdx -> IntStream.range(0, givenColumnSize)
                    .boxed()
                    .collect(Collectors.toMap(columnIdx -> columnIdx, column -> 0))
            ));
        var givenMinesRowIdx = 0;
        var givenMinesColumnIdx = 0;
        var givenMines = MineBoardField.newBuilder()
            .withRowNumber(givenMinesRowIdx)
            .withColumnNumber(givenMinesColumnIdx)
            .build();

        var target = new PutMineIntoFieldsTemplate();

        var result = target.execute(givenMines, givenFieldsTemplate);

        assertThat(result.size(), is(givenFieldsTemplate.size()));
        result.entrySet().stream().forEach(entry -> assertThat(entry.getValue().size(), is(givenFieldsTemplate.get(entry.getKey()).size())));
        assertThat(result.get(givenMinesRowIdx).get(givenMinesColumnIdx), is(MineBoardField.MINE_VALUE));

        // Assert adyacent count
        final var expectedCount = 1;
        assertThat(result.get(givenMinesRowIdx).get(givenMinesColumnIdx + 1), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx + 1).get(givenMinesColumnIdx ), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx + 1).get(givenMinesColumnIdx + 1), is(expectedCount));
    }

    @Test
    public void when_execute_with_mines_at_end_then_should_return_template_with_mines_and_increment_adyacent_field_counter() {
        var givenRowSize = 10;
        var givenColumnSize = 10;
        Map<Integer, Map<Integer, Integer>> givenFieldsTemplate = IntStream.range(0, givenRowSize)
            .boxed()
            .collect(Collectors.toMap(
                rowIdx -> rowIdx,
                rowIdx -> IntStream.range(0, givenColumnSize)
                    .boxed()
                    .collect(Collectors.toMap(columnIdx -> columnIdx, column -> 0))
            ));
        var givenMinesRowIdx = givenRowSize - 1;
        var givenMinesColumnIdx = givenColumnSize - 1;
        var givenMines = MineBoardField.newBuilder()
            .withRowNumber(givenMinesRowIdx)
            .withColumnNumber(givenMinesColumnIdx)
            .build();

        var target = new PutMineIntoFieldsTemplate();

        var result = target.execute(givenMines, givenFieldsTemplate);

        assertThat(result.size(), is(givenFieldsTemplate.size()));
        result.entrySet().stream().forEach(entry -> assertThat(entry.getValue().size(), is(givenFieldsTemplate.get(entry.getKey()).size())));
        assertThat(result.get(givenMinesRowIdx).get(givenMinesColumnIdx), is(MineBoardField.MINE_VALUE));

        // Assert adyacent count
        final var expectedCount = 1;
        assertThat(result.get(givenMinesRowIdx - 1).get(givenMinesColumnIdx - 1), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx - 1).get(givenMinesColumnIdx), is(expectedCount));
        assertThat(result.get(givenMinesRowIdx).get(givenMinesColumnIdx - 1), is(expectedCount));
    }
}
