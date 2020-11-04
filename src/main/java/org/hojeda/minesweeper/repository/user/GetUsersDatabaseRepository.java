package org.hojeda.minesweeper.repository.user;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.user.User;
import org.hojeda.minesweeper.core.repository.user.GetUsersRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetUsersDatabaseRepository implements GetUsersRepository {

    private final SqlClient sqlClient;

    @Inject
    public GetUsersDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public List<User> execute() {
        var query = "SELECT u.id, u.name from USER u";

        return sqlClient.runQuery(
            query,
            rs -> {
                var users = new ArrayList<User>();
                while (rs.next()) {
                    users.add(
                        User.newBuilder()
                            .withId(rs.getLong("id"))
                            .withName(rs.getString("name"))
                            .build()
                    );
                }
                return users;
            }
        );
    }
}
