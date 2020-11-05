package org.hojeda.minesweeper.core.usecase.board;

import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.board.BoardCreationData;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;
import org.hojeda.minesweeper.core.repository.board.SaveCompleteBoardRepository;
import org.hojeda.minesweeper.core.usecase.board.field.GetDataToCreateBoardFields;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CreateBoard {

    private static final Long DEFAULT_USER_ID = 1L;

    private GetDataToCreateBoardFields getDataToCreateBoardFields;
    private SaveCompleteBoardRepository saveBoardRepository;

    @Inject
    public CreateBoard(GetDataToCreateBoardFields getDataToCreateBoardFields, SaveCompleteBoardRepository saveBoardRepository) {
        this.getDataToCreateBoardFields = getDataToCreateBoardFields;
        this.saveBoardRepository = saveBoardRepository;
    }

    public Board execute(BasicBoardData basicBoardData) {

        var userId = basicBoardData.getUserId() != null ? basicBoardData.getUserId() : DEFAULT_USER_ID;

        var creationData = BoardCreationData.newBuilder()
            .withUuid(UUID.randomUUID())
            .withUserId(userId)
            .withMines(basicBoardData.getMines())
            .withColumnSize(basicBoardData.getColumnSize())
            .withRowSize(basicBoardData.getRowSize())
            .withCreatedAt(LocalDateTime.now())
            .withStatus(BoardStatus.CREATED)
            .withFields(List.copyOf(getDataToCreateBoardFields.execute(basicBoardData)))
            .build();

        return saveBoardRepository.execute(creationData);
    }
}
