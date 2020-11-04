package org.hojeda.minesweeper.entrypoint.router.base;

import org.hojeda.minesweeper.entrypoint.router.route.Routes;
import org.hojeda.minesweeper.entrypoint.router.handler.base.HealthCheckHandler;
import spark.RouteGroup;
import spark.Spark;

import javax.inject.Inject;

public class HealthCheckRouter implements RouteGroup {

    private HealthCheckHandler healthCheckHandler;

    @Inject
    public HealthCheckRouter(HealthCheckHandler healthCheckHandler) {
        this.healthCheckHandler = healthCheckHandler;
    }

    @Override
    public void addRoutes() {
        Spark.get(Routes.HEALTH, healthCheckHandler);
    }

}
