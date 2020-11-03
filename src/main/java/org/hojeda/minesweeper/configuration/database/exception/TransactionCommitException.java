package org.hojeda.minesweeper.configuration.database.exception;

public class TransactionCommitException extends RuntimeException {

    public TransactionCommitException(Throwable t) {
        super(t);
    }
}
