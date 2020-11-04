package org.hojeda.minesweeper.repository.board;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;
import org.hojeda.minesweeper.core.repository.board.GetBoardByIdRepository;

import javax.inject.Inject;
import java.util.UUID;

public class GetBoardByIdDatabaseRepository implements GetBoardByIdRepository {

    private final SqlClient sqlClient;

    @Inject
    public GetBoardByIdDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Board execute(Long id) {
        var query = " SELECT b.id, b.uuid, b.created_at, b.status_id, b.row_size, b.column_size, b.mines " +
            " FROM BOARD b " +
            " WHERE b.id = ? ";

        return sqlClient.runQuery(
            query,
            rs -> {
                if (rs.next()) {
                    return Board.newBuilder()
                        .withId(rs.getLong("id"))
                        .withUuid((UUID) rs.getObject("uuid"))
                        .withCreatedAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .withStatus(BoardStatus.getById(rs.getLong("status_id")))
                        .withRowSize(rs.getInt("row_size"))
                        .withColumnSize(rs.getInt("column_size"))
                        .withMines(rs.getInt("mines"))
                        .build();
                } else return null;
            },
            id
        );
    }

}
