package org.hojeda.minesweeper.configuration.database.migration;

import org.flywaydb.core.Flyway;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;

public class FlywayProvider implements Provider<Flyway> {

    private DataSource dataSource;

    @Inject
    public FlywayProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Flyway get() {
        return Flyway.configure()
            .dataSource(dataSource)
            .load();
    }

}
