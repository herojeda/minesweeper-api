package org.hojeda.minesweeper.core.usecase.user;

import org.hojeda.minesweeper.core.entity.user.User;
import org.hojeda.minesweeper.core.repository.user.GetUsersRepository;

import javax.inject.Inject;
import java.util.List;

public class GetUsers {

    private final GetUsersRepository getUsersRepository;

    @Inject
    public GetUsers(GetUsersRepository getUsersRepository) {
        this.getUsersRepository = getUsersRepository;
    }

    public List<User> execute() {
        return getUsersRepository.execute();
    }
}
