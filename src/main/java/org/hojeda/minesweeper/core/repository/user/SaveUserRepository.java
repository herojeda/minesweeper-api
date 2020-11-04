package org.hojeda.minesweeper.core.repository.user;

import org.hojeda.minesweeper.core.entity.user.UserData;

public interface SaveUserRepository {

    Integer execute(UserData userData);

}
