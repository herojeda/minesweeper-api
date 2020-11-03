package org.hojeda.minesweeper.configuration.database.transaction.annotation;

import java.lang.annotation.Annotation;

import static com.google.common.base.Preconditions.checkNotNull;

public class TransactionalImpl implements Transactional {

    private final String value;

    public TransactionalImpl(String value) {
        this.value = checkNotNull(value, "name");
    }

    public int hashCode() {
        // This is specified in java.lang.Annotation.
        return (127 * "value".hashCode()) ^ value.hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Transactional)) {
            return false;
        }

        Transactional other = (Transactional) o;
        return value.equals(other.value());
    }

    public String toString() {
        return "@" + Transactional.class.getName() + "(value=" + value + ")";
    }

    @Override
    public String value() {
        return this.value();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Transactional.class;
    }
}
