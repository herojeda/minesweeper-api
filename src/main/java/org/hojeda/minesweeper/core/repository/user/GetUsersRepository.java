package org.hojeda.minesweeper.core.repository.user;

import org.hojeda.minesweeper.core.entity.user.User;

import java.util.List;

public interface GetUsersRepository {

    List<User> execute();

}
