package org.hojeda.minesweeper.entrypoint.router.handler.user;

import com.google.common.net.HttpHeaders;
import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.core.usecase.user.GetUserById;
import org.hojeda.minesweeper.core.usecase.user.GetUsers;
import org.hojeda.minesweeper.entrypoint.router.dto.response.user.UserResponse;
import org.hojeda.minesweeper.entrypoint.router.error.ErrorRequestException;
import org.hojeda.minesweeper.util.entrypoint.ContentType;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;

public class GetUserByIdHandler implements Route {

    private final GetUserById getUserById;

    @Inject
    public GetUserByIdHandler(GetUserById getUserById) {
        this.getUserById = getUserById;
    }

    @Override
    public Object handle(Request request, Response response) {
        var userId = Long.valueOf(request.params(":userId"));
        var user = getUserById.execute(userId);
        if (user == null) throw new ErrorRequestException("USER_NOT_FOUND - User id: " + userId, HttpStatus.NOT_FOUND_404);

        response.status(HttpStatus.OK_200);
        response.header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.content());

        return UserResponse.newBuilder()
            .withId(user.getId())
            .withName(user.getName())
            .build();
    }
}
