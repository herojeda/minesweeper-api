package org.hojeda.minesweeper.configuration;

import com.google.inject.AbstractModule;
import org.hojeda.minesweeper.configuration.model.SystemConfiguration;
import org.hojeda.minesweeper.core.repository.board.SaveBoardRepository;
import org.hojeda.minesweeper.repository.board.SaveBoardDatabaseRepository;

public class AppModule extends AbstractModule {



    @Override
    protected void configure() {
        var config = ConfigParser.read();
        bind(SystemConfiguration.class).toInstance(config.getSystem());

        bind(SaveBoardRepository.class).to(SaveBoardDatabaseRepository.class);
    }
}
