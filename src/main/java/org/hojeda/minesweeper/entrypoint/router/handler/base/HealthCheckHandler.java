package org.hojeda.minesweeper.entrypoint.router.handler.base;

import com.google.common.net.HttpHeaders;
import org.hojeda.minesweeper.util.entrypoint.ContentType;
import spark.Request;
import spark.Response;
import spark.Route;

public class HealthCheckHandler implements Route {

    @Override
    public Object handle(Request request, Response response) {
        response.header(HttpHeaders.CONTENT_TYPE, ContentType.TEXT_PLAIN.content());
        return "pong";
    }
}
