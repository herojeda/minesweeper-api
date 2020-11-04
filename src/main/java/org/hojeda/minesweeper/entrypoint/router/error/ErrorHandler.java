package org.hojeda.minesweeper.entrypoint.router.error;

import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.Map;

public class ErrorHandler {

    public void addErrorHandler() {
        Spark.exception(ErrorRequestException.class, this::handleErrorRequestException);
        Spark.exception(Exception.class, this::handleGenericError);
    }

    private void handleGenericError(
        Exception exception,
        Request request,
        Response response
    ) {
        try {
            response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            response.body(JsonMapper.get().writeValueAsString(Map.of("error_message", exception.getMessage())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleErrorRequestException(
        ErrorRequestException exception,
        Request request,
        Response response
    ) {
        try {
            response.status(exception.getStatusCode());
            response.body(JsonMapper.get().writeValueAsString(exception.getResponse()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
