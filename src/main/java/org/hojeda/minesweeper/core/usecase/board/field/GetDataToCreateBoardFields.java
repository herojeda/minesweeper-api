package org.hojeda.minesweeper.core.usecase.board.field;

import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.entity.board.field.BoardFieldCreationData;

import javax.inject.Inject;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GetDataToCreateBoardFields {

    private GenerateBombFields generateBombFields;
    private PutMineIntoFieldsTemplate putBombIntoFieldsTemplate;

    @Inject
    public GetDataToCreateBoardFields(GenerateBombFields generateBombFields, PutMineIntoFieldsTemplate putBombIntoFieldsTemplate) {
        this.generateBombFields = generateBombFields;
        this.putBombIntoFieldsTemplate = putBombIntoFieldsTemplate;
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

        Stream.generate(() -> Stream.generate(() -> 0)
            .limit(basicBoardData.getColumnSize())
            .collect(Collectors.toList()))
            .limit(basicBoardData.getRowSize())
            .collect(Collectors.toList());

        generateBombFields.execute(basicBoardData).stream()
            .forEach((bomb) -> putBombIntoFieldsTemplate.execute(bomb, fieldsTemplate));

        return fieldsTemplate.entrySet().stream()
            .flatMap(row -> row.getValue().entrySet().stream()
                .map(field -> BoardFieldCreationData.newBuilder()
                    .withRowNumber(row.getKey())
                    .withColumnNumber(field.getKey())
                    .withHidden(Boolean.TRUE)
                    .withValue(field.getValue())
                    .build()
                )
            ).collect(Collectors.toSet());
    }
}
