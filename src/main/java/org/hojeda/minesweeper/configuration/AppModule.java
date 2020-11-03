package org.hojeda.minesweeper.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import org.apache.commons.dbutils.QueryRunner;
import org.flywaydb.core.Flyway;
import org.hojeda.minesweeper.configuration.database.DataSourceProvider;
import org.hojeda.minesweeper.configuration.database.migration.FlywayProvider;
import org.hojeda.minesweeper.configuration.database.transaction.TransactionManager;
import org.hojeda.minesweeper.configuration.database.transaction.annotation.Transactions;
import org.hojeda.minesweeper.configuration.model.DatabaseConfiguration;
import org.hojeda.minesweeper.configuration.model.SystemConfiguration;
import org.hojeda.minesweeper.core.repository.board.SaveCompleteBoardRepository;
import org.hojeda.minesweeper.repository.board.SaveCompleteBoardDatabaseRepository;

import javax.sql.DataSource;

public class AppModule extends AbstractModule {



    @Override
    protected void configure() {

        // Configurations
        var config = ConfigParser.read();
        bind(SystemConfiguration.class).toInstance(config.getSystem());
        bind(DatabaseConfiguration.class).toInstance(config.getDatabase());

        // Database
        bind(DataSource.class).toProvider(DataSourceProvider.class).asEagerSingleton();
        bind(Flyway.class).toProvider(FlywayProvider.class).asEagerSingleton();
        bind(QueryRunner.class);

        // Repositories
        bind(SaveCompleteBoardRepository.class).to(SaveCompleteBoardDatabaseRepository.class);

        // Interceptor
        bindInterceptor(
            Matchers.any(),
            Matchers.annotatedWith(Transactions.withDataSource(Transactions.DEFAULT_DATA_SOURCE)),
            TransactionManager.build(new DataSourceProvider(config.getDatabase()).get())
        );
    }
}
