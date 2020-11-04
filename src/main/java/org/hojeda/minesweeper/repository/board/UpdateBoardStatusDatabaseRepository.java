package org.hojeda.minesweeper.repository.board;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;
import org.hojeda.minesweeper.core.repository.board.UpdateBoardStatusRepository;

import javax.inject.Inject;

public class UpdateBoardStatusDatabaseRepository implements UpdateBoardStatusRepository {

    private final SqlClient sqlClient;

    @Inject
    public UpdateBoardStatusDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Integer execute(Long boardId, BoardStatus newStatus) {
        var query = " UPDATE BOARD SET status_id = ? " +
            " WHERE id = ? ";

        return sqlClient.runInsertOrUpdate(
            query,
            newStatus.getId(),
            boardId
        );
    }
}
