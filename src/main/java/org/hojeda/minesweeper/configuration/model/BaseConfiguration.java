package org.hojeda.minesweeper.configuration.model;

import java.util.Objects;

public class BaseConfiguration {

    private SystemConfiguration system;
    private DatabaseConfiguration database;

    public SystemConfiguration getSystem() {
        return system;
    }

    public void setSystem(SystemConfiguration system) {
        this.system = system;
    }

    public DatabaseConfiguration getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseConfiguration database) {
        this.database = database;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseConfiguration)) return false;
        BaseConfiguration that = (BaseConfiguration) o;
        return Objects.equals(system, that.system) &&
            Objects.equals(database, that.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(system, database);
    }

    @Override
    public String toString() {
        return "BaseConfiguration{" +
            "system=" + system +
            ", database=" + database +
            '}';
    }
}
