package org.hojeda.minesweeper.repository.board.field;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class GetFieldsByBoardIdDatabaseRepository {

    private final SqlClient sqlClient;

    @Inject
    public GetFieldsByBoardIdDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Set<BoardField> execute(Long boardId) {
        var query = "SELECT bf.id, bf.row_index, bf.column_index, bf.value, bf.status_id" +
            " FROM BOARD_FIELD bf WHERE bf.board_id = ?";

        return sqlClient.runQuery(
            query,
            rs -> {
                var fields = new HashSet<BoardField>();
                while (rs.next()) {
                    fields.add(
                        BoardField.newBuilder()
                            .withId(rs.getLong("id"))
                            .withRowNumber(rs.getInt("row_index"))
                            .withColumnNumber(rs.getInt("column_index"))
                            .withValue(rs.getInt("value"))
                            .withStatus(BoardFieldStatus.getById(rs.getLong("status_id")))
                            .build()
                    );
                }
                return fields;
            },
            boardId
        );

    }


}
