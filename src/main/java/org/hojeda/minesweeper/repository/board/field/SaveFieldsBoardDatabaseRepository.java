package org.hojeda.minesweeper.repository.board.field;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.field.BoardFieldCreationData;

import javax.inject.Inject;

public class SaveFieldsBoardDatabaseRepository {

    private final SqlClient sqlClient;

    @Inject
    public SaveFieldsBoardDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Integer execute(BoardFieldCreationData fieldCreationData, Long boardId) {

        var query = "INSERT INTO BOARD_FIELD (board_id, row_index, column_index, value, hidden)" +
            "VALUES (?, ?, ?, ?, ?)";

        return sqlClient.runInsertOrUpdate(
            query,
            boardId,
            fieldCreationData.getRowNumber(),
            fieldCreationData.getColumnNumber(),
            fieldCreationData.getValue(),
            fieldCreationData.getHidden()
        );
    }

}
