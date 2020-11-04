package org.hojeda.minesweeper.core.entity.user;

import java.util.Objects;

public class UserData {

    private String name;

    private UserData(Builder builder) {
        setName(builder.name);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserData copy) {
        Builder builder = new Builder();
        builder.name = copy.getName();
        return builder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserData)) return false;
        UserData userData = (UserData) o;
        return Objects.equals(name, userData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "UserData{" +
            "name='" + name + '\'' +
            '}';
    }

    public static final class Builder {
        private String name;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public UserData build() {
            return new UserData(this);
        }
    }
}
