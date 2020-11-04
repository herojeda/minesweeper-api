package org.hojeda.minesweeper.repository.user;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.user.User;
import org.hojeda.minesweeper.core.repository.user.GetUserByIdRepository;
import org.hojeda.minesweeper.core.repository.user.GetUsersRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetUserByIdDatabaseRepository implements GetUserByIdRepository {

    private final SqlClient sqlClient;

    @Inject
    public GetUserByIdDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public User execute(Long userId) {
        var query = "SELECT u.id, u.name " +
            " FROM USER u " +
            " WHERE u.id = ? ";

        return sqlClient.runQuery(
            query,
            rs -> {
                if (rs.next()) {
                    return User.newBuilder()
                        .withId(rs.getLong("id"))
                        .withName(rs.getString("name"))
                        .build();
                } else return null;
            },
            userId
        );
    }
}
