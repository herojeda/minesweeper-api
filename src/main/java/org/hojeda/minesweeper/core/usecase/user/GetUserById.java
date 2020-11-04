package org.hojeda.minesweeper.core.usecase.user;

import org.hojeda.minesweeper.core.entity.user.User;
import org.hojeda.minesweeper.core.repository.user.GetUserByIdRepository;

import javax.inject.Inject;

public class GetUserById {

    private final GetUserByIdRepository getUserByIdRepository;

    @Inject
    public GetUserById(GetUserByIdRepository getUserByIdRepository) {
        this.getUserByIdRepository = getUserByIdRepository;
    }

    public User execute(Long userId) {
        return getUserByIdRepository.execute(userId);
    }
}
