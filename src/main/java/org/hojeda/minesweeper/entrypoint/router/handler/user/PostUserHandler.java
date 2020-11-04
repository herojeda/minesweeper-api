package org.hojeda.minesweeper.entrypoint.router.handler.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.net.HttpHeaders;
import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.core.entity.user.UserData;
import org.hojeda.minesweeper.core.usecase.user.GetUsers;
import org.hojeda.minesweeper.core.usecase.user.SaveUser;
import org.hojeda.minesweeper.entrypoint.router.dto.request.user.PostUserRequest;
import org.hojeda.minesweeper.entrypoint.router.dto.response.user.UserResponse;
import org.hojeda.minesweeper.util.entrypoint.ContentType;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;

public class PostUserHandler implements Route {

    private final SaveUser saveUser;

    @Inject
    public PostUserHandler(SaveUser saveUser) {
        this.saveUser = saveUser;
    }

    @Override
    public Object handle(Request request, Response response) throws JsonProcessingException {
        var userRequest = JsonMapper.get().readValue(request.body(), PostUserRequest.class);
        var user = saveUser.execute(UserData.newBuilder().withName(userRequest.getName()).build());

        response.status(HttpStatus.CREATED_201);
        response.header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.content());

        return UserResponse.newBuilder()
            .withId(user.getId())
            .withName(user.getName())
            .build();
    }
}
