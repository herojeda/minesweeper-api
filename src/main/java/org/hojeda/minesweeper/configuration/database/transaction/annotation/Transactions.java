package org.hojeda.minesweeper.configuration.database.transaction.annotation;

public class Transactions {

    public static final String DEFAULT_DATA_SOURCE = "default";

    public static Transactional withDataSource(String name) {
        return new TransactionalImpl(name);
    }

}
