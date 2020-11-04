package org.hojeda.minesweeper.repository.board.field;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldByBoardIdAndRowAndColumnRepository;

import javax.inject.Inject;

public class GetFieldByBoardIdAndRowAndColumnDatabaseRepository implements GetFieldByBoardIdAndRowAndColumnRepository {

    private final SqlClient sqlClient;

    @Inject
    public GetFieldByBoardIdAndRowAndColumnDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public BoardField execute(Long boardId, Integer row, Integer column) {
        var query = "SELECT bf.id, bf.row_index, bf.column_index, bf.value, bf.status_id" +
            " FROM BOARD_FIELD bf " +
            " WHERE bf.board_id = ? " +
            " AND bf.row_index = ? " +
            " AND bf.column_index = ? ";

        return sqlClient.runQuery(
            query,
            rs -> {
                if (rs.next()) {
                    return BoardField.newBuilder()
                        .withId(rs.getLong("id"))
                        .withBoardId(boardId)
                        .withRowNumber(rs.getInt("row_index"))
                        .withColumnNumber(rs.getInt("column_index"))
                        .withValue(rs.getInt("value"))
                        .withStatus(BoardFieldStatus.getById(rs.getLong("status_id")))
                        .build();
                } else return null;
            },
            boardId,
            row,
            column
        );
    }

}
