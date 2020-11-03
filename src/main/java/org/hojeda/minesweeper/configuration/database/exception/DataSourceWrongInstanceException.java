package org.hojeda.minesweeper.configuration.database.exception;

public class DataSourceWrongInstanceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataSourceWrongInstanceException(String message) {
        super("The dataSource should be instance of: " + message);
    }
}
