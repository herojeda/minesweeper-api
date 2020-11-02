package org.hojeda.minesweeper.repository.board;

import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.board.BoardCreationData;
import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.repository.board.SaveBoardRepository;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SaveBoardDatabaseRepository implements SaveBoardRepository {

    @Override
    public Board execute(BoardCreationData boardCreationData) {
        var fields = IntStream.range(0, boardCreationData.getFields().size())
            .boxed()
            .map(idx -> {
                    var creationField = boardCreationData.getFields().get(idx);
                    return BoardField.newBuilder()
                        .withId(idx + 1L)
                        .withColumnNumber(creationField.getColumnNumber())
                        .withRowNumber(creationField.getRowNumber())
                        .withHidden(creationField.getHidden())
                        .withValue(creationField.getValue())
                        .build();
                }
            )
            .collect(Collectors.toSet());

        return Board.newBuilder()
            .withId(1L)
            .withStatus(boardCreationData.getStatus())
            .withBombs(boardCreationData.getBombs())
            .withColumnSize(boardCreationData.getColumnSize())
            .withCreatedAt(boardCreationData.getCreatedAt())
            .withRowSize(boardCreationData.getRowSize())
            .withFields(fields)
            .build();
    }
}
