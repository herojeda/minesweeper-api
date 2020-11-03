package org.hojeda.minesweeper.configuration.database.exception;

public class TransactionCloseException extends RuntimeException {

    public TransactionCloseException(Throwable t) {
        super("Can't close connection", t);
    }
    
}
