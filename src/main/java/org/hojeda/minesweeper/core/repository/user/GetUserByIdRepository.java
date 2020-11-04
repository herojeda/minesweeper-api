package org.hojeda.minesweeper.core.repository.user;

import org.hojeda.minesweeper.core.entity.user.User;

import java.util.List;

public interface GetUserByIdRepository {

    User execute(Long userId);

}
