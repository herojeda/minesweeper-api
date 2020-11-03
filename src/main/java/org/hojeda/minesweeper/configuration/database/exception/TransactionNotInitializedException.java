package org.hojeda.minesweeper.configuration.database.exception;

public class TransactionNotInitializedException extends RuntimeException {

    public TransactionNotInitializedException() {
        super("The transaction was not initialized");
    }

}
