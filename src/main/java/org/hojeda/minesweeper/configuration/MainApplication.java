package org.hojeda.minesweeper.configuration;

import org.apache.commons.lang3.RandomStringUtils;
import org.hojeda.minesweeper.configuration.model.SystemConfiguration;
import org.hojeda.minesweeper.entrypoint.router.base.HealthCheckRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import spark.RouteGroup;
import spark.Spark;
import spark.servlet.SparkApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

public class MainApplication implements SparkApplication, RouteGroup {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);
    private static final String TRACE_ID = "trace_id";
    private static final Integer TRACE_SIZE = 16;

    public static void main(String[] args) {
        new MainApplication().init();
    }

    @Override
    public void addRoutes() {
        Context.getInjector().getInstance(HealthCheckRouter.class).addRoutes();
    }

    @Override
    public void init() {
        MDC.put(TRACE_ID, generateTraceId());
        try {
            LOGGER.info("BOOT_INIT: Waiting for initialization...");
            Context.init();
            var systemConfig = Context.getInjector().getInstance(SystemConfiguration.class);
            configureServer(systemConfig);
            setUpRoutes(systemConfig);
            Spark.awaitInitialization();
            LOGGER.info("BOOT_READY");
        } catch (UnknownHostException e) {
            LOGGER.error("BOOT_ERROR: " + e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
        Spark.stop();
    }

    private void configureServer(SystemConfiguration systemConfig) throws UnknownHostException {
        final var address = InetAddress.getLocalHost().getHostAddress();
        final var port = Objects.nonNull(System.getenv("PORT"))
            ? Integer.valueOf(System.getenv("PORT"))
            : systemConfig.getHttpPort();

        Spark.port(port);
        Spark.ipAddress(address);
        Spark.threadPool(
            systemConfig.getMaxThreads(),
            systemConfig.getMinThreads(),
            systemConfig.getTimeout()
        );
        LOGGER.info(
            "Listening in {}:{} using thread pool: [min:{} | max:{} | timeout:{}]",
            address,
            systemConfig.getHttpPort(),
            systemConfig.getMinThreads(),
            systemConfig.getMaxThreads(),
            systemConfig.getTimeout()
        );
    }

    private void setUpRoutes(SystemConfiguration systemConfig) {
        Spark.path(systemConfig.getBasePath(), this::addRoutes);
        Spark.before((request, response) -> MDC.put(TRACE_ID, generateTraceId()));
        Spark.after((request, response) -> MDC.clear());
    }

    private static String generateTraceId() {
        return RandomStringUtils.randomAlphanumeric(TRACE_SIZE);
    }
}
