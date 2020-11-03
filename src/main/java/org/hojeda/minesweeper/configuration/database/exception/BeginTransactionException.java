package org.hojeda.minesweeper.configuration.database.exception;

public class BeginTransactionException extends RuntimeException {

    public BeginTransactionException(Throwable t) {
        super(t);
    }
}
