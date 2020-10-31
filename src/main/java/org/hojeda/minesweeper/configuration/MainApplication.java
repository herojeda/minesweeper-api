package org.hojeda.minesweeper.configuration;

import com.google.inject.Guice;
import org.hojeda.minesweeper.configuration.model.SystemConfiguration;
import org.hojeda.minesweeper.entrypoint.router.base.HealthCheckRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.RouteGroup;
import spark.Spark;
import spark.servlet.SparkApplication;

public class MainApplication implements SparkApplication, RouteGroup {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        new MainApplication().init();
    }

    @Override
    public void addRoutes() {
        Context.getInjector().getInstance(HealthCheckRouter.class).addRoutes();
    }

    @Override
    public void init() {
        LOGGER.info("Waiting for initialization...");
        Context.init();
        var systemConfig = Context.getInjector().getInstance(SystemConfiguration.class);
        configureServer(systemConfig);
        setUpRoutes(systemConfig);
        Spark.awaitInitialization();
        LOGGER.info("READY");
    }

    @Override
    public void destroy() {
        Spark.stop();
    }

    private void configureServer(SystemConfiguration systemConfig) {
        Spark.port(systemConfig.getHttpPort());
        Spark.ipAddress(systemConfig.getAddress());
        Spark.threadPool(
            systemConfig.getMaxThreads(),
            systemConfig.getMinThreads(),
            systemConfig.getTimeout()
        );
        LOGGER.info(
            "Listening in {}:{} using thread pool: [min:{} | max:{} | timeout:{}]",
            systemConfig.getAddress(),
            systemConfig.getHttpPort(),
            systemConfig.getMinThreads(),
            systemConfig.getMaxThreads(),
            systemConfig.getTimeout()
        );
    }

    private void setUpRoutes(SystemConfiguration systemConfig) {
        Spark.path(systemConfig.getBasePath(), this::addRoutes);
    }
}
