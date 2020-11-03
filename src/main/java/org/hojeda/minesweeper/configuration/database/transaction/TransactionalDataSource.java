package org.hojeda.minesweeper.configuration.database.transaction;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface TransactionalDataSource extends DataSource, AutoCloseable {

    boolean isOpenTransaction();

    void beginTransaction() throws SQLException;

    void finalizeTransaction() throws SQLException;

    void rollback() throws SQLException;

    void close();
}
