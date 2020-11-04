package org.hojeda.minesweeper.core.usecase.board.movement.applier;

import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;
import org.hojeda.minesweeper.core.repository.board.UpdateBoardFinishedAtRepository;
import org.hojeda.minesweeper.core.repository.board.UpdateBoardStatusRepository;
import org.hojeda.minesweeper.core.repository.board.fields.CountFieldByBoardIdAndStatusRepository;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldsByBoardIdRepository;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldsToMapByBoardIdRepository;
import org.hojeda.minesweeper.core.repository.board.fields.UpdateFieldStatusByIdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static org.hojeda.minesweeper.core.entity.constants.board.BoardStatus.LOST;
import static org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus.OPENED;

public class ApplyOpenMovement implements ApplyMovement {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyOpenMovement.class);
    private static final Integer ADYACENT_FIELDS = 3;

    private final GetFieldsToMapByBoardIdRepository getFieldsToMapByBoardIdRepository;
    private final GetFieldsByBoardIdRepository getFieldsByBoardIdRepository;
    private final UpdateBoardStatusRepository updateBoardStatusRepository;
    private final UpdateFieldStatusByIdRepository updateFieldStatusByIdRepository;
    private final UpdateBoardFinishedAtRepository updateBoardFinishedAtRepository;

    @Inject
    public ApplyOpenMovement(
        GetFieldsToMapByBoardIdRepository getFieldsToMapByBoardIdRepository,
        GetFieldsByBoardIdRepository getFieldsByBoardIdRepository,
        UpdateBoardStatusRepository updateBoardStatusRepository,
        UpdateFieldStatusByIdRepository updateFieldStatusByIdRepository,
        UpdateBoardFinishedAtRepository updateBoardFinishedAtRepository
    ) {
        this.getFieldsToMapByBoardIdRepository = getFieldsToMapByBoardIdRepository;
        this.getFieldsByBoardIdRepository = getFieldsByBoardIdRepository;
        this.updateFieldStatusByIdRepository = updateFieldStatusByIdRepository;
        this.updateBoardStatusRepository = updateBoardStatusRepository;
        this.updateBoardFinishedAtRepository = updateBoardFinishedAtRepository;
    }

    public Set<BoardField> execute(BoardField boardField) {

        LOGGER.info("Processing OPEN movement for -> " + boardField);

        if (MineBoardField.MINE_VALUE.equals(boardField.getValue())) {
            updateBoardStatusRepository.execute(boardField.getBoardId(), LOST);
            updateBoardFinishedAtRepository.execute(boardField.getBoardId(), LocalDateTime.now());
            updateFieldStatusByIdRepository.execute(boardField.getId(), OPENED);
        } else {
            var fields = getFieldsToMapByBoardIdRepository.execute(boardField.getBoardId());
            openField(boardField, fields);
        }

        return getFieldsByBoardIdRepository.execute(boardField.getBoardId());
    }

    private void openField(BoardField fieldToOpen, Map<Integer, Map<Integer, BoardField>> fields) {

        LOGGER.info("Process open field -> " + fieldToOpen);

        if (!OPENED.equals(fieldToOpen.getStatus())) {
            updateFieldStatusByIdRepository.execute(fieldToOpen.getId(), OPENED);
            fields.get(fieldToOpen.getRowNumber()).get(fieldToOpen.getColumnNumber()).setStatus(OPENED);
        }

        if (fieldToOpen.getValue() == 0) {
            var firstAdyacentRow = fieldToOpen.getRowNumber() - 1;
            var firstAdyacentColumn = fieldToOpen.getColumnNumber() - 1;

            IntStream.range(
                Math.max(0, firstAdyacentRow),
                Math.min(firstAdyacentRow + ADYACENT_FIELDS, fields.size())
            ).forEach(rowIdx -> IntStream.range(
                Math.max(0, firstAdyacentColumn),
                Math.min(firstAdyacentColumn + ADYACENT_FIELDS, fields.get(rowIdx).size())
            ).forEach(columnIdx -> {
                    var nextField = fields.get(rowIdx).get(columnIdx);
                    if (!OPENED.equals(nextField.getStatus())) {
                        openField(nextField, fields);
                    }
                }
            ));
        }
    }
}
