package org.hojeda.minesweeper.entrypoint.router.handler.base;

import com.google.common.net.HttpHeaders;
import spark.Request;
import spark.Response;
import spark.Route;

public class HealthCheckHandler implements Route {

    @Override
    public Object handle(Request request, Response response) {
        response.header(HttpHeaders.CONTENT_TYPE, "text/plain");
        return "pong";
    }
}
