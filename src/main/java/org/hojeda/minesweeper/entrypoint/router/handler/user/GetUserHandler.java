package org.hojeda.minesweeper.entrypoint.router.handler.user;

import com.google.common.net.HttpHeaders;
import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.core.usecase.user.GetUsers;
import org.hojeda.minesweeper.entrypoint.router.dto.response.user.UserResponse;
import org.hojeda.minesweeper.entrypoint.router.ContentType;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class GetUserHandler implements Route {

    private final GetUsers getUsers;

    @Inject
    public GetUserHandler(GetUsers getUsers) {
        this.getUsers = getUsers;
    }

    @Override
    public Object handle(Request request, Response response) {
        var users = getUsers.execute();

        response.status(HttpStatus.OK_200);
        response.header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.content());

        return users.stream()
            .map(user -> UserResponse.newBuilder()
                .withId(user.getId())
                .withName(user.getName())
                .build()
            )
            .collect(Collectors.toList());
    }
}
