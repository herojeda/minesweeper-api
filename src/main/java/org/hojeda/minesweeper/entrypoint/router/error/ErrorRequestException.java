package org.hojeda.minesweeper.entrypoint.router.error;

import java.util.Map;

public class ErrorRequestException extends RuntimeException{

    private Integer statusCode;
    private Map<String, String> response;

    public ErrorRequestException(String message, Throwable cause, Integer statusCode, Map<String, String> response) {
        super(message, cause);
        this.statusCode = statusCode;
        this.response = response;
    }

    public ErrorRequestException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.response = Map.of("error_message", message);
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getResponse() {
        return response;
    }
}
