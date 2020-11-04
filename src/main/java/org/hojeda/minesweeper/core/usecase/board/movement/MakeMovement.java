package org.hojeda.minesweeper.core.usecase.board.movement;

import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.board.BoardMovement;
import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;
import org.hojeda.minesweeper.core.entity.constants.board.MovementType;
import org.hojeda.minesweeper.core.repository.board.GetBoardByIdRepository;
import org.hojeda.minesweeper.core.repository.board.UpdateBoardStatusRepository;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldByBoardIdAndRowAndColumnRepository;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldsByBoardIdRepository;
import org.hojeda.minesweeper.core.usecase.board.movement.applier.ApplyMovement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;
import java.util.Set;

public class MakeMovement {

    private static final Logger LOGGER = LoggerFactory.getLogger(MakeMovement.class);

    private final GetBoardByIdRepository getBoardByIdRepository;
    private final Map<MovementType, ApplyMovement> movementAppliers;
    private final GetFieldByBoardIdAndRowAndColumnRepository getFieldByBoardIdAndRowAndColumnRepository;
    private final GetFieldsByBoardIdRepository getFieldsByBoardIdRepository;
    private final UpdateBoardStatusRepository updateBoardStatusRepository;

    @Inject
    public MakeMovement(
        GetBoardByIdRepository getBoardByIdRepository,
        Map<MovementType, ApplyMovement> movementAppliers,
        GetFieldByBoardIdAndRowAndColumnRepository getFieldByBoardIdAndRowAndColumnRepository,
        GetFieldsByBoardIdRepository getFieldsByBoardIdRepository,
        UpdateBoardStatusRepository updateBoardStatusRepository
    ) {
        this.getBoardByIdRepository = getBoardByIdRepository;
        this.movementAppliers = movementAppliers;
        this.getFieldByBoardIdAndRowAndColumnRepository = getFieldByBoardIdAndRowAndColumnRepository;
        this.getFieldsByBoardIdRepository = getFieldsByBoardIdRepository;
        this.updateBoardStatusRepository = updateBoardStatusRepository;
    }

    public Board execute(BoardMovement boardMovement) {

        LOGGER.info("Making movement for -> " + boardMovement);

        Board boardResult;
        Set<BoardField> fieldsResult;

        var board = getBoardByIdRepository.execute(boardMovement.getBoardId());
        if (BoardStatus.LOST.equals(board.getStatus()) || BoardStatus.LOST.equals(board.getStatus())) {
            boardResult = board;
            fieldsResult = getFieldsByBoardIdRepository.execute(board.getId());
        } else {
            var field = getFieldByBoardIdAndRowAndColumnRepository.execute(
                boardMovement.getBoardId(),
                boardMovement.getRow(),
                boardMovement.getColumn()
            );

            if (BoardStatus.CREATED.equals(board.getStatus())) {
                updateBoardStatusRepository.execute(board.getId(), BoardStatus.PLAYING);
            }

            fieldsResult = movementAppliers.get(boardMovement.getMovementType()).execute(field);
            boardResult = getBoardByIdRepository.execute(boardMovement.getBoardId());
        }
        return Board.newBuilder(boardResult)
            .withFields(fieldsResult)
            .build();
    }
}
