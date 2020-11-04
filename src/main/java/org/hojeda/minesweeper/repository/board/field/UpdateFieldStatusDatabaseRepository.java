package org.hojeda.minesweeper.repository.board.field;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.BoardMovement;
import org.hojeda.minesweeper.core.repository.board.fields.UpdateFieldStatusRepository;

import javax.inject.Inject;

public class UpdateFieldStatusDatabaseRepository implements UpdateFieldStatusRepository {

    private final SqlClient sqlClient;

    @Inject
    public UpdateFieldStatusDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Integer execute(BoardMovement boardMovement) {
        var query = " UPDATE BOARD_FIELD SET status_id = ? " +
            " WHERE board_id = ? " +
            " AND row_index = ? " +
            " AND column_index = ? ";

        return sqlClient.runInsertOrUpdate(
            query,
            boardMovement.getMovementType().getFieldStatus().getId(),
            boardMovement.getBoardId(),
            boardMovement.getRow(),
            boardMovement.getColumn()
        );
    }

}
