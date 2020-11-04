package org.hojeda.minesweeper.repository.user;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.user.User;
import org.hojeda.minesweeper.core.repository.user.GetUserByIdRepository;
import org.hojeda.minesweeper.core.repository.user.GetUserByNameRepository;

import javax.inject.Inject;

public class GetUserByNameDatabaseRepository implements GetUserByNameRepository {

    private final SqlClient sqlClient;

    @Inject
    public GetUserByNameDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public User execute(String name) {
        var query = "SELECT u.id, u.name " +
            " FROM USER u " +
            " WHERE u.name = ? ";

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
            name
        );
    }
}
