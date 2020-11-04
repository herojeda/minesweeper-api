package org.hojeda.minesweeper.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.multibindings.MapBinder;
import org.apache.commons.dbutils.QueryRunner;
import org.flywaydb.core.Flyway;
import org.hojeda.minesweeper.configuration.database.DataSourceProvider;
import org.hojeda.minesweeper.configuration.database.migration.FlywayProvider;
import org.hojeda.minesweeper.configuration.database.transaction.TransactionManager;
import org.hojeda.minesweeper.configuration.database.transaction.annotation.Transactions;
import org.hojeda.minesweeper.configuration.model.DatabaseConfiguration;
import org.hojeda.minesweeper.configuration.model.SystemConfiguration;
import org.hojeda.minesweeper.core.entity.constants.board.MovementType;
import org.hojeda.minesweeper.core.repository.board.*;
import org.hojeda.minesweeper.core.repository.board.fields.*;
import org.hojeda.minesweeper.core.repository.user.GetUserByIdRepository;
import org.hojeda.minesweeper.core.repository.user.GetUserByNameRepository;
import org.hojeda.minesweeper.core.repository.user.GetUsersRepository;
import org.hojeda.minesweeper.core.repository.user.SaveUserRepository;
import org.hojeda.minesweeper.core.usecase.board.movement.applier.ApplyFlagMovement;
import org.hojeda.minesweeper.core.usecase.board.movement.applier.ApplyMovement;
import org.hojeda.minesweeper.core.usecase.board.movement.applier.ApplyOpenMovement;
import org.hojeda.minesweeper.core.usecase.board.movement.applier.ApplyQuestionMovement;
import org.hojeda.minesweeper.repository.board.*;
import org.hojeda.minesweeper.repository.board.field.*;
import org.hojeda.minesweeper.repository.user.GetUserByIdDatabaseRepository;
import org.hojeda.minesweeper.repository.user.GetUserByNameDatabaseRepository;
import org.hojeda.minesweeper.repository.user.GetUsersDatabaseRepository;
import org.hojeda.minesweeper.repository.user.SaveUserDatabaseRepository;

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
        bind(SaveUserRepository.class).to(SaveUserDatabaseRepository.class);
        bind(UpdateFieldStatusRepository.class).to(UpdateFieldStatusDatabaseRepository.class);
        bind(UpdateBoardStatusRepository.class).to(UpdateBoardStatusDatabaseRepository.class);
        bind(UpdateFieldStatusByIdRepository.class).to(UpdateFieldStatusByIdDatabaseRepository.class);
        bind(UpdateBoardStartedAtRepository.class).to(UpdateBoardStartedAtDatabaseRepository.class);
        bind(UpdateBoardFinishedAtRepository.class).to(UpdateBoardFinishedAtDatabaseRepository.class);
        bind(CountFieldByBoardIdAndStatusRepository.class).to(CountFieldByBoardIdAndStatusDatabaseRepository.class);
        bind(GetFieldsToMapByBoardIdRepository.class).to(GetFieldsToMapByBoardIdDatabaseRepository.class);
        bind(GetBoardByIdRepository.class).to(GetBoardByIdDatabaseRepository.class);
        bind(GetFieldByBoardIdAndRowAndColumnRepository.class).to(GetFieldByBoardIdAndRowAndColumnDatabaseRepository.class);
        bind(GetFieldsByBoardIdRepository.class).to(GetFieldsByBoardIdDatabaseRepository.class);
        bind(GetUsersRepository.class).to(GetUsersDatabaseRepository.class);
        bind(GetUserByIdRepository.class).to(GetUserByIdDatabaseRepository.class);
        bind(GetUserByNameRepository.class).to(GetUserByNameDatabaseRepository.class);

        // Movement strategies
        var movementStrategies = MapBinder.newMapBinder(
            binder(),
            MovementType.class,
            ApplyMovement.class
        );
        movementStrategies.addBinding(MovementType.FLAG).to(ApplyFlagMovement.class);
        movementStrategies.addBinding(MovementType.OPEN).to(ApplyOpenMovement.class);
        movementStrategies.addBinding(MovementType.QUESTION).to(ApplyQuestionMovement.class);

        // Interceptor
        bindInterceptor(
            Matchers.any(),
            Matchers.annotatedWith(Transactions.withDataSource(Transactions.DEFAULT_DATA_SOURCE)),
            TransactionManager.build(new DataSourceProvider(config.getDatabase()).get())
        );
    }
}
