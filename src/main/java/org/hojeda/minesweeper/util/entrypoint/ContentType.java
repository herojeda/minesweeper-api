package org.hojeda.minesweeper.util.entrypoint;

public enum ContentType {

    APPLICATION_JSON("application/json"),
    TEXT_PLAIN("text/plain");

    private String content;

    ContentType(String content) {
        this.content = content;
    }

    public String content() {
        return content;
    }
}
