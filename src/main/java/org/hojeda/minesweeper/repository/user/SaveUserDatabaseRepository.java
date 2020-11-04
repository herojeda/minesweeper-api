package org.hojeda.minesweeper.repository.user;

import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.user.User;
import org.hojeda.minesweeper.core.entity.user.UserData;
import org.hojeda.minesweeper.core.repository.user.GetUsersRepository;
import org.hojeda.minesweeper.core.repository.user.SaveUserRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SaveUserDatabaseRepository implements SaveUserRepository {

    private final SqlClient sqlClient;

    @Inject
    public SaveUserDatabaseRepository(SqlClient sqlClient) {
        this.sqlClient = sqlClient;
    }

    public Integer execute(UserData userData) {
        var query = "INSERT INTO USER (name) VALUES (?)";

        return sqlClient.runInsertOrUpdate(query, userData.getName());
    }
}
