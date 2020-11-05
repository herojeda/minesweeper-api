package org.hojeda.minesweeper.repository.board;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.BoardCreationData;

import javax.inject.Inject;

public class SaveBoardDatabaseRepository {

    private final SqlClient sqlClient;

    @Inject
    public SaveBoardDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Integer execute(BoardCreationData boardCreationData) {
        var query = " INSERT INTO BOARD(UUID, USER_ID, CREATED_AT, STATUS_ID, ROW_SIZE, COLUMN_SIZE, MINES) " +
            " VALUES (?, ?, ?, ?, ?, ?, ?) ";

        return sqlClient.runInsertOrUpdate(
            query,
            boardCreationData.getUuid(),
            boardCreationData.getUserId(),
            boardCreationData.getCreatedAt(),
            boardCreationData.getStatus().getId(),
            boardCreationData.getRowSize(),
            boardCreationData.getColumnSize(),
            boardCreationData.getMines()
        );
    }
}