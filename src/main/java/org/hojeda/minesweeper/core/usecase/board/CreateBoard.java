package org.hojeda.minesweeper.core.usecase.board;

import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.board.BoardCreationData;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;
import org.hojeda.minesweeper.core.repository.board.SaveBoardRepository;
import org.hojeda.minesweeper.core.usecase.board.field.GetDataToCreateBoardFields;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class CreateBoard {

    private GetDataToCreateBoardFields getDataToCreateBoardFields;
    private SaveBoardRepository saveBoardRepository;

    @Inject
    public CreateBoard(GetDataToCreateBoardFields getDataToCreateBoardFields, SaveBoardRepository saveBoardRepository) {
        this.getDataToCreateBoardFields = getDataToCreateBoardFields;
        this.saveBoardRepository = saveBoardRepository;
    }

    public Board execute(BasicBoardData basicBoardData) {

        var creationData = BoardCreationData.newBuilder()
            .withBombs(basicBoardData.getBombs())
            .withColumnSize(basicBoardData.getColumnSize())
            .withRowSize(basicBoardData.getRowSize())
            .withCreatedAt(LocalDate.now())
            .withStatus(BoardStatus.CREATED)
            .withFields(List.copyOf(getDataToCreateBoardFields.execute(basicBoardData)))
            .build();

        return saveBoardRepository.execute(creationData);
    }
}
