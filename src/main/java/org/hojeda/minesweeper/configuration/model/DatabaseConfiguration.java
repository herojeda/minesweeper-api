package org.hojeda.minesweeper.configuration.model;

import java.util.Objects;

public class DatabaseConfiguration {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;
    private Boolean autocommit;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public Boolean getAutocommit() {
        return autocommit;
    }

    public void setAutocommit(Boolean autocommit) {
        this.autocommit = autocommit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatabaseConfiguration)) return false;
        DatabaseConfiguration that = (DatabaseConfiguration) o;
        return jdbcUrl.equals(that.jdbcUrl) &&
            username.equals(that.username) &&
            password.equals(that.password) &&
            driverClassName.equals(that.driverClassName) &&
            autocommit.equals(that.autocommit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jdbcUrl, username, password, driverClassName, autocommit);
    }

    @Override
    public String toString() {
        return "DatabaseConfiguration{" +
            "jdbcUrl='" + jdbcUrl + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", driverClassName='" + driverClassName + '\'' +
            ", autocommit=" + autocommit +
            '}';
    }
}
