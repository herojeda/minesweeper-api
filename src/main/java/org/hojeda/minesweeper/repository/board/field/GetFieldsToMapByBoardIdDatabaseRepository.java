package org.hojeda.minesweeper.repository.board.field;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldsToMapByBoardIdRepository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class GetFieldsToMapByBoardIdDatabaseRepository implements GetFieldsToMapByBoardIdRepository {

    private final SqlClient sqlClient;

    @Inject
    public GetFieldsToMapByBoardIdDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Map<Integer, Map<Integer, BoardField>> execute(Long boardId) {
        var query = " SELECT bf.id, bf.row_index, bf.column_index, bf.value, bf.status_id " +
            " FROM BOARD_FIELD bf " +
            " WHERE bf.board_id = ? ";

        return sqlClient.runQuery(
            query,
            rs -> {
                var fields = new HashMap<Integer, Map<Integer, BoardField>>();
                while (rs.next()) {
                    var row = rs.getInt("row_index");
                    var column = rs.getInt("column_index");
                    if (!fields.containsKey(row)) fields.put(row, new HashMap<>());
                    fields.get(row).put(
                        column,
                        BoardField.newBuilder()
                            .withId(rs.getLong("id"))
                            .withBoardId(boardId)
                            .withRowNumber(row)
                            .withColumnNumber(column)
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
