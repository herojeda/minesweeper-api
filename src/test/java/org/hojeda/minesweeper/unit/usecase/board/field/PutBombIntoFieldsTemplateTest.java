package org.hojeda.minesweeper.unit.usecase.board.field;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.*;

import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;
import org.hojeda.minesweeper.core.usecase.board.field.PutMineIntoFieldsTemplate;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PutBombIntoFieldsTemplateTest {

    @Test
    public void when_execute_with_correct_params_then_should_return_template_with_bomb_and_increment_adyacent_field_counter() {
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
        var givenBombRowIdx = 5;
        var givenBombColumnIdx = 6;
        var givenBomb = MineBoardField.newBuilder()
            .withRowNumber(givenBombRowIdx)
            .withColumnNumber(givenBombColumnIdx)
            .build();

        var target = new PutMineIntoFieldsTemplate();

        var result = target.execute(givenBomb, givenFieldsTemplate);

        assertThat(result.size(), is(givenFieldsTemplate.size()));
        result.entrySet().stream().forEach(entry -> assertThat(entry.getValue().size(), is(givenFieldsTemplate.get(entry.getKey()).size())));
        assertThat(result.get(givenBombRowIdx).get(givenBombColumnIdx), is(MineBoardField.MINE_VALUE));

        // Assert adyacent count
        final var expectedCount = 1;
        assertThat(result.get(givenBombRowIdx - 1).get(givenBombColumnIdx - 1), is(expectedCount));
        assertThat(result.get(givenBombRowIdx - 1).get(givenBombColumnIdx), is(expectedCount));
        assertThat(result.get(givenBombRowIdx - 1).get(givenBombColumnIdx + 1), is(expectedCount));
        assertThat(result.get(givenBombRowIdx).get(givenBombColumnIdx - 1), is(expectedCount));
        assertThat(result.get(givenBombRowIdx).get(givenBombColumnIdx + 1), is(expectedCount));
        assertThat(result.get(givenBombRowIdx + 1).get(givenBombColumnIdx - 1), is(expectedCount));
        assertThat(result.get(givenBombRowIdx + 1).get(givenBombColumnIdx ), is(expectedCount));
        assertThat(result.get(givenBombRowIdx + 1).get(givenBombColumnIdx + 1), is(expectedCount));
    }

    @Test
    public void when_execute_with_bomb_at_start_then_should_return_template_with_bomb_and_increment_adyacent_field_counter() {
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
        var givenBombRowIdx = 0;
        var givenBombColumnIdx = 0;
        var givenBomb = MineBoardField.newBuilder()
            .withRowNumber(givenBombRowIdx)
            .withColumnNumber(givenBombColumnIdx)
            .build();

        var target = new PutMineIntoFieldsTemplate();

        var result = target.execute(givenBomb, givenFieldsTemplate);

        assertThat(result.size(), is(givenFieldsTemplate.size()));
        result.entrySet().stream().forEach(entry -> assertThat(entry.getValue().size(), is(givenFieldsTemplate.get(entry.getKey()).size())));
        assertThat(result.get(givenBombRowIdx).get(givenBombColumnIdx), is(MineBoardField.MINE_VALUE));

        // Assert adyacent count
        final var expectedCount = 1;
        assertThat(result.get(givenBombRowIdx).get(givenBombColumnIdx + 1), is(expectedCount));
        assertThat(result.get(givenBombRowIdx + 1).get(givenBombColumnIdx ), is(expectedCount));
        assertThat(result.get(givenBombRowIdx + 1).get(givenBombColumnIdx + 1), is(expectedCount));
    }

    @Test
    public void when_execute_with_bomb_at_end_then_should_return_template_with_bomb_and_increment_adyacent_field_counter() {
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
        var givenBombRowIdx = givenRowSize - 1;
        var givenBombColumnIdx = givenColumnSize - 1;
        var givenBomb = MineBoardField.newBuilder()
            .withRowNumber(givenBombRowIdx)
            .withColumnNumber(givenBombColumnIdx)
            .build();

        var target = new PutMineIntoFieldsTemplate();

        var result = target.execute(givenBomb, givenFieldsTemplate);

        assertThat(result.size(), is(givenFieldsTemplate.size()));
        result.entrySet().stream().forEach(entry -> assertThat(entry.getValue().size(), is(givenFieldsTemplate.get(entry.getKey()).size())));
        assertThat(result.get(givenBombRowIdx).get(givenBombColumnIdx), is(MineBoardField.MINE_VALUE));

        // Assert adyacent count
        final var expectedCount = 1;
        assertThat(result.get(givenBombRowIdx - 1).get(givenBombColumnIdx - 1), is(expectedCount));
        assertThat(result.get(givenBombRowIdx - 1).get(givenBombColumnIdx), is(expectedCount));
        assertThat(result.get(givenBombRowIdx).get(givenBombColumnIdx - 1), is(expectedCount));
    }
}
