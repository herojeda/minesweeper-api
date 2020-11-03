package org.hojeda.minesweeper.configuration.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hojeda.minesweeper.configuration.database.transaction.DatasourceProxy;
import org.hojeda.minesweeper.configuration.model.DatabaseConfiguration;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.sql.DataSource;
import java.util.Objects;

public class DataSourceProvider implements Provider<DataSource> {

    private DatabaseConfiguration configuration;
    private static DataSource dataSource;

    @Inject
    public DataSourceProvider(DatabaseConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public DataSource get() {

        if (Objects.isNull(dataSource)) {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(configuration.getDriverClassName());
            config.setJdbcUrl(configuration.getJdbcUrl());
            config.setUsername(configuration.getUsername());
            config.setPassword(configuration.getPassword());
            config.setAutoCommit(configuration.getAutocommit());

            dataSource = DatasourceProxy.build(new HikariDataSource(config));
        }

        return dataSource;
    }
}
