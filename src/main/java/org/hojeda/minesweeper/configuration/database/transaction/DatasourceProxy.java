package org.hojeda.minesweeper.configuration.database.transaction;

import org.hojeda.minesweeper.configuration.database.exception.*;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Objects;
import java.util.logging.Logger;


public class DatasourceProxy implements TransactionalDataSource {

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DatasourceProxy.class);

  private ThreadLocal<Connection> connectionContext;
  private DataSource dataSource;

  private DatasourceProxy(DataSource dataSource) {
    this.connectionContext = new ThreadLocal<>();
    this.dataSource = dataSource;
  }

  public static DataSource build(DataSource dataSource) {
    return new DatasourceProxy(dataSource);
  }

  @Override
  public boolean isOpenTransaction() {
    return connectionContext.get() != null;
  }

  @Override
  public void beginTransaction() {
    try {
      Connection connection = this.dataSource.getConnection();
      connection.setAutoCommit(Boolean.FALSE);
      this.connectionContext.set(connection);
    } catch (SQLException e) {
      throw new BeginTransactionException(e);
    }
  }

  @Override
  public void finalizeTransaction() throws SQLException {
    if (Objects.isNull(connectionContext.get())) {
      throw new TransactionNotInitializedException();
    }
    try {
      connectionContext.get().commit();
    } catch (SQLException e) {
      throw new TransactionCommitException(e);
    }
  }

  @Override
  public void rollback() throws SQLException {
    if (Objects.isNull(connectionContext.get())) {
        logger.error("Can't rollback transaction because doesn't have available connection.");
        throw new TransactionNotInitializedException();
    }

    try {
      if (!connectionContext.get().isClosed()) {
          connectionContext.get().rollback();
      }
    } catch (SQLException e) {
      logger.error("Error when rollback transaction: ", e);
      throw new TransactionRollbackException(e);
    }
  }

  @Override
  public Connection getConnection() throws SQLException {
    Connection conn = connectionContext.get();
    if (conn == null) {
      conn = dataSource.getConnection();
    }
    return conn;
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    Connection conn = connectionContext.get();
    if (conn == null) {
      conn = dataSource.getConnection(username, password);
    }
    return conn;
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return dataSource.unwrap(iface);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return dataSource.isWrapperFor(iface);
  }

  @Override
  public PrintWriter getLogWriter() throws SQLException {
    return dataSource.getLogWriter();
  }

  @Override
  public void setLogWriter(PrintWriter out) throws SQLException {
    dataSource.setLogWriter(out);
  }

  @Override
  public void setLoginTimeout(int seconds) throws SQLException {
    dataSource.setLoginTimeout(seconds);
  }

  @Override
  public int getLoginTimeout() throws SQLException {
    return dataSource.getLoginTimeout();
  }

  @Override
  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return dataSource.getParentLogger();
  }

  @Override
  public void close() {
    try {
      if (isOpenTransaction() && !this.connectionContext.get().isClosed()) {
        logger.info("Closing transactional connection: " + this.connectionContext.get().toString());
        this.connectionContext.get().close();
      }
    } catch (SQLException e) {
      String msg = "Can't close connection: ";
      logger.error(msg, e);
      throw new TransactionCloseException(e);
    }
    this.connectionContext.remove();
  }
}
