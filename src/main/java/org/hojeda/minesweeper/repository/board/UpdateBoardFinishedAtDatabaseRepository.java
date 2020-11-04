package org.hojeda.minesweeper.repository.board;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.repository.board.UpdateBoardFinishedAtRepository;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class UpdateBoardFinishedAtDatabaseRepository implements UpdateBoardFinishedAtRepository {

    private final SqlClient sqlClient;

    @Inject
    public UpdateBoardFinishedAtDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Integer execute(Long boardId, LocalDateTime finishedAt) {
        var query = " UPDATE BOARD SET finished_at = ? " +
            " WHERE id = ? ";

        return sqlClient.runInsertOrUpdate(
            query,
            finishedAt,
            boardId
        );
    }
}
