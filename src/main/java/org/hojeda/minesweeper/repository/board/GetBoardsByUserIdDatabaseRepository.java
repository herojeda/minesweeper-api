package org.hojeda.minesweeper.repository.board;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;
import org.hojeda.minesweeper.core.repository.board.GetBoardsByUserIdRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetBoardsByUserIdDatabaseRepository implements GetBoardsByUserIdRepository {

    private final SqlClient sqlClient;

    @Inject
    public GetBoardsByUserIdDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public List<Board> execute(Long userId) {
        var query = " SELECT b.id, b.uuid, b.user_id, b.created_at, b.status_id, b.row_size, b.column_size, b.mines, b.started_at, b.finished_at " +
            " FROM BOARD b " +
            " WHERE b.user_id = ? ";

        return sqlClient.runQuery(
            query,
            rs -> {
                var boards = new ArrayList<Board>();
                while (rs.next()) {
                    var startedAt = rs.getTimestamp("started_at");
                    var finishedAt = rs.getTimestamp("finished_at");
                    boards.add(Board.newBuilder()
                        .withId(rs.getLong("id"))
                        .withUuid((UUID) rs.getObject("uuid"))
                        .withUserId(rs.getLong("user_id"))
                        .withCreatedAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .withStatus(BoardStatus.getById(rs.getLong("status_id")))
                        .withRowSize(rs.getInt("row_size"))
                        .withColumnSize(rs.getInt("column_size"))
                        .withMines(rs.getInt("mines"))
                        .withStartedAt(startedAt != null ? startedAt.toLocalDateTime() : null)
                        .withFinishedAt(finishedAt != null ? finishedAt.toLocalDateTime() : null)
                        .build()
                    );
                }
                return boards;
            },
            userId
        );
    }

}
