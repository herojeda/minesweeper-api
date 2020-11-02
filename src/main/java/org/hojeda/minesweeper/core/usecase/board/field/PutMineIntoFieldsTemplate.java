package org.hojeda.minesweeper.core.usecase.board.field;

import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;

import java.util.Map;
import java.util.stream.IntStream;

public class PutMineIntoFieldsTemplate {

    private static final Integer ADYACENT_FIELDS = 3;

    public Map<Integer,Map<Integer, Integer>> execute(MineBoardField bomb, Map<Integer,Map<Integer, Integer>> fieldsTemplate) {
        fieldsTemplate.get(bomb.getRowNumber()).put(bomb.getColumnNumber(), MineBoardField.MINE_VALUE);
        var firstAdyacentRow = bomb.getRowNumber() - 1;
        var firstAdyacentColumn = bomb.getColumnNumber() - 1;
        return incrementAdyacentCounter(fieldsTemplate, firstAdyacentRow, firstAdyacentColumn);
    }

    private Map<Integer,Map<Integer, Integer>> incrementAdyacentCounter(Map<Integer,Map<Integer, Integer>> fieldsTemplate, Integer firstRow, Integer firstColumn) {
        IntStream.range(firstRow, firstRow + ADYACENT_FIELDS).forEach(rowIdx -> {
            if (rowIdx >= 0 && rowIdx < fieldsTemplate.size()) {
                IntStream.range(firstColumn, firstColumn + ADYACENT_FIELDS).forEach(columnIdx -> {
                    if (columnIdx >= 0
                        && columnIdx < fieldsTemplate.get(rowIdx).size()
                        && fieldsTemplate.get(rowIdx).get(columnIdx) != MineBoardField.MINE_VALUE
                    ) {
                        var value = fieldsTemplate.get(rowIdx).get(columnIdx);
                        fieldsTemplate.get(rowIdx).put(columnIdx, value + 1);
                    }
                });
            }
        });
        return fieldsTemplate;
    }
}
