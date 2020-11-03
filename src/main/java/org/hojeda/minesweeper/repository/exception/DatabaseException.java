package org.hojeda.minesweeper.repository.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String query, Throwable cause) {
        super("An error occurred when execute SQL: " + query, cause);
    }
}
