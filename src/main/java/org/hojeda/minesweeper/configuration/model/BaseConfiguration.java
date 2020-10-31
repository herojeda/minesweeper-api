package org.hojeda.minesweeper.configuration.model;

import java.util.Objects;

public class BaseConfiguration {

    private SystemConfiguration system;

    public SystemConfiguration getSystem() {
        return system;
    }

    public void setSystem(SystemConfiguration system) {
        this.system = system;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseConfiguration)) return false;
        BaseConfiguration that = (BaseConfiguration) o;
        return Objects.equals(system, that.system);
    }

    @Override
    public int hashCode() {
        return Objects.hash(system);
    }
}
