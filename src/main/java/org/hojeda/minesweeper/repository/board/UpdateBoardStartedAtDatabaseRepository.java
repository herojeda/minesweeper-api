package org.hojeda.minesweeper.repository.board;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.repository.board.UpdateBoardStartedAtRepository;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class UpdateBoardStartedAtDatabaseRepository implements UpdateBoardStartedAtRepository {

    private final SqlClient sqlClient;

    @Inject
    public UpdateBoardStartedAtDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Integer execute(Long boardId, LocalDateTime startedAt) {
        var query = " UPDATE BOARD SET started_at = ? " +
            " WHERE id = ? ";

        return sqlClient.runInsertOrUpdate(
            query,
            startedAt,
            boardId
        );
    }
}
