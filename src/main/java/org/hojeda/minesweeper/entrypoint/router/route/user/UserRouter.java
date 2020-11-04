package org.hojeda.minesweeper.entrypoint.router.route.user;

import org.hojeda.minesweeper.entrypoint.router.handler.user.GetUserByIdHandler;
import org.hojeda.minesweeper.entrypoint.router.handler.user.GetUserHandler;
import org.hojeda.minesweeper.entrypoint.router.handler.user.PostUserHandler;
import org.hojeda.minesweeper.entrypoint.router.route.Routes;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import spark.RouteGroup;
import spark.Spark;

import javax.inject.Inject;

public class UserRouter implements RouteGroup {

    private final GetUserHandler getUserHandler;
    private final GetUserByIdHandler getUserByIdHandler;
    private final PostUserHandler postUserHandler;

    @Inject
    public UserRouter(GetUserHandler getUserHandler, GetUserByIdHandler getUserByIdHandler, PostUserHandler postUserHandler) {
        this.getUserHandler = getUserHandler;
        this.getUserByIdHandler = getUserByIdHandler;
        this.postUserHandler = postUserHandler;
    }

    @Override
    public void addRoutes() {

        Spark.get(
            Routes.USER, getUserHandler, JsonMapper.get()::writeValueAsString
        );

        Spark.get(
            Routes.USER + Routes.USER_ID, getUserByIdHandler, JsonMapper.get()::writeValueAsString
        );

        Spark.post(
            Routes.USER, postUserHandler, JsonMapper.get()::writeValueAsString
        );

    }
}
