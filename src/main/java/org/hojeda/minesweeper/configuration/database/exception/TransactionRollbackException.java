package org.hojeda.minesweeper.configuration.database.exception;

public class TransactionRollbackException extends RuntimeException {

    public TransactionRollbackException(Throwable t) {
        super(t);
    }

}
