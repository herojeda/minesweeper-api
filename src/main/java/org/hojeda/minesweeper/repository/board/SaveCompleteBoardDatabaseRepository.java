package org.hojeda.minesweeper.repository.board;

import org.hojeda.minesweeper.configuration.database.transaction.annotation.Transactional;
import org.hojeda.minesweeper.configuration.database.transaction.annotation.Transactions;
import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.board.BoardCreationData;
import org.hojeda.minesweeper.core.repository.board.SaveCompleteBoardRepository;
import org.hojeda.minesweeper.repository.board.field.GetFieldsByBoardIdDatabaseRepository;
import org.hojeda.minesweeper.repository.board.field.SaveFieldsBoardDatabaseRepository;

import javax.inject.Inject;

public class SaveCompleteBoardDatabaseRepository implements SaveCompleteBoardRepository {

    private final SaveBoardDatabaseRepository saveBoardDatabaseRepository;
    private final GetBoardByUuidDatabaseRepository getBoardByUuidDatabaseRepository;
    private final SaveFieldsBoardDatabaseRepository saveFieldsBoardDatabaseRepository;
    private final GetFieldsByBoardIdDatabaseRepository getFieldsByBoardIdDatabaseRepository;

    @Inject
    public SaveCompleteBoardDatabaseRepository(
        SaveBoardDatabaseRepository saveBoardDatabaseRepository,
        GetBoardByUuidDatabaseRepository getBoardByUuidDatabaseRepository,
        SaveFieldsBoardDatabaseRepository saveFieldsBoardDatabaseRepository,
        GetFieldsByBoardIdDatabaseRepository getFieldsByBoardIdDatabaseRepository
    ) {
        this.saveBoardDatabaseRepository = saveBoardDatabaseRepository;
        this.getBoardByUuidDatabaseRepository = getBoardByUuidDatabaseRepository;
        this.saveFieldsBoardDatabaseRepository = saveFieldsBoardDatabaseRepository;
        this.getFieldsByBoardIdDatabaseRepository = getFieldsByBoardIdDatabaseRepository;
    }

    @Override
    @Transactional(Transactions.DEFAULT_DATA_SOURCE)
    public Board execute(BoardCreationData boardCreationData) {

        saveBoardDatabaseRepository.execute(boardCreationData);
        var board = getBoardByUuidDatabaseRepository.execute(boardCreationData.getUuid());
        boardCreationData.getFields().stream()
            .forEach(field -> saveFieldsBoardDatabaseRepository.execute(field, board.getId()));
        var fields = getFieldsByBoardIdDatabaseRepository.execute(board.getId());

        return Board.newBuilder(board)
            .withFields(fields)
            .build();

    }
}
