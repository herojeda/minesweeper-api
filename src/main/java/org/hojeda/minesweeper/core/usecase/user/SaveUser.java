package org.hojeda.minesweeper.core.usecase.user;

import org.hojeda.minesweeper.core.entity.user.User;
import org.hojeda.minesweeper.core.entity.user.UserData;
import org.hojeda.minesweeper.core.repository.user.GetUserByNameRepository;
import org.hojeda.minesweeper.core.repository.user.SaveUserRepository;

import javax.inject.Inject;

public class SaveUser {

    private final SaveUserRepository saveUserRepository;
    private final GetUserByNameRepository getUserByNameRepository;

    @Inject
    public SaveUser(SaveUserRepository saveUserRepository, GetUserByNameRepository getUserByNameRepository) {
        this.saveUserRepository = saveUserRepository;
        this.getUserByNameRepository = getUserByNameRepository;
    }

    public User execute(UserData userData) {
        saveUserRepository.execute(userData);
        return getUserByNameRepository.execute(userData.getName());
    }
}
