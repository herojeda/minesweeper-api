package org.hojeda.minesweeper.core.usecase.board.field;

import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;

import java.util.Map;
import java.util.stream.IntStream;

public class PutMineIntoFieldsTemplate {

    private static final Integer ADYACENT_FIELDS = 3;

    public Map<Integer, Map<Integer, Integer>> execute(MineBoardField mine, Map<Integer, Map<Integer, Integer>> fieldsTemplate) {
        fieldsTemplate.get(mine.getRowNumber()).put(mine.getColumnNumber(), MineBoardField.MINE_VALUE);
        var firstAdyacentRow = mine.getRowNumber() - 1;
        var firstAdyacentColumn = mine.getColumnNumber() - 1;
        return incrementAdyacentCounter(fieldsTemplate, firstAdyacentRow, firstAdyacentColumn);
    }

    private Map<Integer, Map<Integer, Integer>> incrementAdyacentCounter(
        Map<Integer, Map<Integer, Integer>> fieldsTemplate,
        Integer firstAdyacentRow,
        Integer firstAdyacentColumn
    ) {
        IntStream.range(
            Math.max(0, firstAdyacentRow),
            Math.min(firstAdyacentRow + ADYACENT_FIELDS, fieldsTemplate.size())
        ).forEach(rowIdx -> IntStream.range(
            Math.max(0, firstAdyacentColumn),
            Math.min(firstAdyacentColumn + ADYACENT_FIELDS, fieldsTemplate.get(rowIdx).size())
        ).forEach(columnIdx -> {
            if (!fieldsTemplate.get(rowIdx).get(columnIdx).equals(MineBoardField.MINE_VALUE)) {
                var value = fieldsTemplate.get(rowIdx).get(columnIdx);
                fieldsTemplate.get(rowIdx).put(columnIdx, value + 1);
            }
        }));
        return fieldsTemplate;
    }
}
