package org.hojeda.minesweeper.configuration;

import com.google.inject.AbstractModule;
import org.hojeda.minesweeper.configuration.model.SystemConfiguration;

public class AppModule extends AbstractModule {



    @Override
    protected void configure() {
        var config = ConfigParser.read();
        bind(SystemConfiguration.class).toInstance(config.getSystem());

    }
}
