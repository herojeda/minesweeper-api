package org.hojeda.minesweeper.configuration.database;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.hojeda.minesweeper.configuration.database.transaction.TransactionalDataSource;
import org.hojeda.minesweeper.repository.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;

public class SqlClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlClient.class);

    private DataSource dataSource;
    private QueryRunner queryRunner;

    @Inject
    public SqlClient(DataSource dataSource) {
        this.dataSource = dataSource;
        this.queryRunner = new QueryRunner(dataSource);
    }

    public <T> T runQuery(@Nonnull final String query, @Nonnull final ResultSetHandler<T> handler, Object... args) {
        try {
            if (isOpenTransaction()) {
                return queryRunner.query(dataSource.getConnection(), query, handler, args);
            } else {
                return queryRunner.query(query, handler, args);
            }
        } catch (SQLException throwables) {
            var error = new DatabaseException(query, throwables);
            LOGGER.error(error.getMessage(), error);
            throw error;
        }
    }

    public Integer runInsertOrUpdate(@Nonnull final String query, Object... args) {
        try {
            if (isOpenTransaction()) {
                return queryRunner.execute(dataSource.getConnection(), query, args);
            } else {
                return queryRunner.execute(query, args);
            }
        } catch (SQLException throwables) {
            var error = new DatabaseException(query, throwables);
            LOGGER.error(error.getMessage(), error);
            throw error;
        }
    }

    private Boolean isOpenTransaction() {
        return ((TransactionalDataSource) dataSource).isOpenTransaction();
    }
}
