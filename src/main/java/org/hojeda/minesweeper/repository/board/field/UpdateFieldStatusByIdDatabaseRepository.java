package org.hojeda.minesweeper.repository.board.field;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.BoardMovement;
import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;
import org.hojeda.minesweeper.core.repository.board.fields.UpdateFieldStatusByIdRepository;
import org.hojeda.minesweeper.core.repository.board.fields.UpdateFieldStatusRepository;

import javax.inject.Inject;

public class UpdateFieldStatusByIdDatabaseRepository implements UpdateFieldStatusByIdRepository {

    private final SqlClient sqlClient;

    @Inject
    public UpdateFieldStatusByIdDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Integer execute(Long fieldId, BoardFieldStatus status) {
        var query = " UPDATE BOARD_FIELD SET status_id = ? " +
            " WHERE id = ? ";

        return sqlClient.runInsertOrUpdate(
            query,
            status.getId(),
            fieldId
        );
    }

}
