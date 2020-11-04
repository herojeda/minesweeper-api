package org.hojeda.minesweeper.core.usecase.board.movement.applier;

import org.hojeda.minesweeper.core.entity.board.BoardMovement;
import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.constants.board.MovementType;
import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldsByBoardIdRepository;
import org.hojeda.minesweeper.core.repository.board.fields.UpdateFieldStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Set;

public class ApplyFlagMovement implements ApplyMovement {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyFlagMovement.class);

    private final UpdateFieldStatusRepository updateFieldStatusRepository;
    private final GetFieldsByBoardIdRepository getFieldsByBoardIdRepository;

    @Inject
    public ApplyFlagMovement(
        UpdateFieldStatusRepository updateFieldStatusRepository,
        GetFieldsByBoardIdRepository getFieldsByBoardIdRepository
    ) {
        this.updateFieldStatusRepository = updateFieldStatusRepository;
        this.getFieldsByBoardIdRepository = getFieldsByBoardIdRepository;
    }

    public Set<BoardField> execute(BoardField boardField) {
        LOGGER.info("Processing FLAG movement for -> " + boardField);
        if (
            !BoardFieldStatus.OPENED.equals(boardField.getStatus()) ||
                !BoardFieldStatus.FLAGGED.equals(boardField.getStatus())
        ) {
            updateFieldStatusRepository.execute(
                BoardMovement.newBuilder()
                    .withBoardId(boardField.getBoardId())
                    .withRow(boardField.getRowNumber())
                    .withColumn(boardField.getColumnNumber())
                    .withMovementType(MovementType.FLAG)
                    .build()
            );
        }
        return getFieldsByBoardIdRepository.execute(boardField.getBoardId());
    }
}
