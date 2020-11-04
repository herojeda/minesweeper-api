package org.hojeda.minesweeper.core.repository.user;

import org.hojeda.minesweeper.core.entity.user.User;

public interface GetUserByNameRepository {

    User execute(String name);

}
