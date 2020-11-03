package org.hojeda.minesweeper.core.usecase.board.field;

import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.entity.board.field.BoardFieldCreationData;
import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;

import javax.inject.Inject;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GetDataToCreateBoardFields {

    private GenerateMineFields generatemineFields;
    private PutMineIntoFieldsTemplate putMineIntoFieldsTemplate;

    @Inject
    public GetDataToCreateBoardFields(GenerateMineFields generatemineFields, PutMineIntoFieldsTemplate putMineIntoFieldsTemplate) {
        this.generatemineFields = generatemineFields;
        this.putMineIntoFieldsTemplate = putMineIntoFieldsTemplate;
    }

    public Set<BoardFieldCreationData> execute(BasicBoardData basicBoardData) {
        Map<Integer, Map<Integer, Integer>> fieldsTemplate = IntStream.range(0, basicBoardData.getRowSize())
            .boxed()
            .collect(Collectors.toMap(
                rowIdx -> rowIdx,
                rowIdx -> IntStream.range(0, basicBoardData.getColumnSize())
                    .boxed()
                    .collect(Collectors.toMap(columnIdx -> columnIdx, column -> 0))
            ));

        generatemineFields.execute(basicBoardData).stream()
            .forEach((mine) -> putMineIntoFieldsTemplate.execute(mine, fieldsTemplate));

        return fieldsTemplate.entrySet().stream()
            .flatMap(row -> row.getValue().entrySet().stream()
                .map(field -> BoardFieldCreationData.newBuilder()
                    .withRowNumber(row.getKey())
                    .withColumnNumber(field.getKey())
                    .withStatus(BoardFieldStatus.CLOSED)
                    .withValue(field.getValue())
                    .build()
                )
            ).collect(Collectors.toSet());
    }
}
