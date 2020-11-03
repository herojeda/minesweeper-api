package org.hojeda.minesweeper.configuration.database.exception;

public class NullDataSourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NullDataSourceException() {
        super("The dataSource parameter can't be null.");
    }
}
