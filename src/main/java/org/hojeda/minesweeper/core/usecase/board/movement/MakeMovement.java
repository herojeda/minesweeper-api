package org.hojeda.minesweeper.core.usecase.board.movement;

import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.board.BoardMovement;
import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;
import org.hojeda.minesweeper.core.entity.constants.board.MovementType;
import org.hojeda.minesweeper.core.repository.board.GetBoardByIdRepository;
import org.hojeda.minesweeper.core.repository.board.UpdateBoardFinishedAtRepository;
import org.hojeda.minesweeper.core.repository.board.UpdateBoardStartedAtRepository;
import org.hojeda.minesweeper.core.repository.board.UpdateBoardStatusRepository;
import org.hojeda.minesweeper.core.repository.board.fields.CountFieldByBoardIdAndStatusRepository;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldByBoardIdAndRowAndColumnRepository;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldsByBoardIdRepository;
import org.hojeda.minesweeper.core.usecase.board.movement.applier.ApplyMovement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import static org.hojeda.minesweeper.core.entity.constants.board.BoardStatus.*;
import static org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus.OPENED;

public class MakeMovement {

    private static final Logger LOGGER = LoggerFactory.getLogger(MakeMovement.class);

    private final GetBoardByIdRepository getBoardByIdRepository;
    private final Map<MovementType, ApplyMovement> movementAppliers;
    private final GetFieldByBoardIdAndRowAndColumnRepository getFieldByBoardIdAndRowAndColumnRepository;
    private final GetFieldsByBoardIdRepository getFieldsByBoardIdRepository;
    private final UpdateBoardStatusRepository updateBoardStatusRepository;
    private final UpdateBoardStartedAtRepository updateBoardStartedAtRepository;
    private final CountFieldByBoardIdAndStatusRepository countFieldByBoardIdAndStatusRepository;
    private final UpdateBoardFinishedAtRepository updateBoardFinishedAtRepository;

    @Inject
    public MakeMovement(
        GetBoardByIdRepository getBoardByIdRepository,
        Map<MovementType, ApplyMovement> movementAppliers,
        GetFieldByBoardIdAndRowAndColumnRepository getFieldByBoardIdAndRowAndColumnRepository,
        GetFieldsByBoardIdRepository getFieldsByBoardIdRepository,
        UpdateBoardStatusRepository updateBoardStatusRepository,
        UpdateBoardStartedAtRepository updateBoardStartedAtRepository,
        CountFieldByBoardIdAndStatusRepository countFieldByBoardIdAndStatusRepository,
        UpdateBoardFinishedAtRepository updateBoardFinishedAtRepository
    ) {
        this.getBoardByIdRepository = getBoardByIdRepository;
        this.movementAppliers = movementAppliers;
        this.getFieldByBoardIdAndRowAndColumnRepository = getFieldByBoardIdAndRowAndColumnRepository;
        this.getFieldsByBoardIdRepository = getFieldsByBoardIdRepository;
        this.updateBoardStatusRepository = updateBoardStatusRepository;
        this.updateBoardStartedAtRepository = updateBoardStartedAtRepository;
        this.countFieldByBoardIdAndStatusRepository = countFieldByBoardIdAndStatusRepository;
        this.updateBoardFinishedAtRepository = updateBoardFinishedAtRepository;
    }

    public Board execute(BoardMovement boardMovement) {

        LOGGER.info("Making movement for -> " + boardMovement);

        Board boardResult;
        Set<BoardField> fieldsResult;

        var board = getBoardByIdRepository.execute(boardMovement.getBoardId());
        if (WON.equals(board.getStatus()) || LOST.equals(board.getStatus())) {
            boardResult = board;
            fieldsResult = getFieldsByBoardIdRepository.execute(board.getId());
        } else {
            var field = getFieldByBoardIdAndRowAndColumnRepository.execute(
                boardMovement.getBoardId(),
                boardMovement.getRow(),
                boardMovement.getColumn()
            );

            if (CREATED.equals(board.getStatus())) {
                updateBoardStatusRepository.execute(board.getId(), PLAYING);
                updateBoardStartedAtRepository.execute(board.getId(), LocalDateTime.now());
            }

            fieldsResult = movementAppliers.get(boardMovement.getMovementType()).execute(field);

            if (!MineBoardField.MINE_VALUE.equals(field.getValue())) {
                var openFieldsCount = countFieldByBoardIdAndStatusRepository.execute(board.getId(), OPENED);
                var requiredOpenFields = (board.getColumnSize() * board.getRowSize()) - board.getMines();
                if (openFieldsCount.equals(requiredOpenFields)) {
                    updateBoardStatusRepository.execute(board.getId(), WON);
                    updateBoardFinishedAtRepository.execute(board.getId(), LocalDateTime.now());
                }
            }

            boardResult = getBoardByIdRepository.execute(boardMovement.getBoardId());
        }
        return Board.newBuilder(boardResult)
            .withFields(fieldsResult)
            .build();
    }
}
