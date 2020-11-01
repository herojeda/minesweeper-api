package org.hojeda.minesweeper.configuration.model;

import java.util.Objects;

public class SystemConfiguration {

    private Integer httpPort;
    private String basePath;
    private Integer maxThreads;
    private Integer minThreads;
    private Integer timeout;

    public Integer getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(Integer httpPort) {
        this.httpPort = httpPort;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Integer getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }

    public Integer getMinThreads() {
        return minThreads;
    }

    public void setMinThreads(Integer minThreads) {
        this.minThreads = minThreads;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemConfiguration)) return false;
        SystemConfiguration that = (SystemConfiguration) o;
        return Objects.equals(httpPort, that.httpPort) &&
            Objects.equals(basePath, that.basePath) &&
            Objects.equals(maxThreads, that.maxThreads) &&
            Objects.equals(minThreads, that.minThreads) &&
            Objects.equals(timeout, that.timeout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpPort, basePath, maxThreads, minThreads, timeout);
    }
}
