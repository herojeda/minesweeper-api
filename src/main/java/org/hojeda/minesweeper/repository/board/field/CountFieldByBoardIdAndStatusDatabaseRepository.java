package org.hojeda.minesweeper.repository.board.field;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;
import org.hojeda.minesweeper.core.repository.board.fields.CountFieldByBoardIdAndStatusRepository;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldByBoardIdAndRowAndColumnRepository;

import javax.inject.Inject;

public class CountFieldByBoardIdAndStatusDatabaseRepository implements CountFieldByBoardIdAndStatusRepository {

    private final SqlClient sqlClient;

    @Inject
    public CountFieldByBoardIdAndStatusDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Integer execute(Long boardId, BoardFieldStatus status) {
        var query = "SELECT count(*) as count" +
            " FROM BOARD_FIELD bf " +
            " WHERE bf.board_id = ? " +
            " AND bf.status_id = ? ";

        return sqlClient.runQuery(
            query,
            rs -> {
                if (rs.next()) {
                    return rs.getInt("count");
                } else return null;
            },
            boardId,
            status.getId()
        );
    }

}
