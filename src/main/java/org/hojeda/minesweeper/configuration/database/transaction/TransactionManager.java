package org.hojeda.minesweeper.configuration.database.transaction;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hojeda.minesweeper.configuration.database.exception.DataSourceWrongInstanceException;
import org.hojeda.minesweeper.configuration.database.exception.NullDataSourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class TransactionManager implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionManager.class);

    private TransactionalDataSource dataSource;

    private TransactionManager(TransactionalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static TransactionManager build(DataSource dataSource) {
        if (dataSource == null) {
            throw new NullDataSourceException();
        }
        if (!(dataSource instanceof TransactionalDataSource)) {
            throw new DataSourceWrongInstanceException(TransactionalDataSource.class.getName());
        }
        return new TransactionManager((TransactionalDataSource) dataSource);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try (TransactionalDataSource txDataSource = dataSource) {
            LOGGER.info("Beginning transaction for: "
                    + invocation.getThis().getClass().getName() + "."
                    + invocation.getMethod().getName() + "()");

            txDataSource.beginTransaction();
            Long beginTrxTime = System.currentTimeMillis();
            Object o = invocation.proceed();
            txDataSource.finalizeTransaction();

            LOGGER.info("Finalize transaction for: "
                    + invocation.getThis().getClass().getName() + "."
                    + invocation.getMethod().getName() + "() - "
                    + "Time elapsed: [" + (System.currentTimeMillis() - beginTrxTime) + " Milliseconds]"
            );
            return o;
        } catch (Exception e) {
            LOGGER.error(e.getClass().getName() + ": Transaction failed and rollback for: " + e.getMessage(), e);
            dataSource.rollback();
            throw e;
        }
    }

}
