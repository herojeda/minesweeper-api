package org.hojeda.minesweeper.entrypoint.router.dto.request.user;

import java.util.Objects;

public class PostUserRequest {

    private String name;

    public PostUserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostUserRequest)) return false;
        PostUserRequest that = (PostUserRequest) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PostUserRequest{" +
            "name='" + name + '\'' +
            '}';
    }
}
