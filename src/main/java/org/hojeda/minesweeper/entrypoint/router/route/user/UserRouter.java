package org.hojeda.minesweeper.entrypoint.router.route.user;

import org.hojeda.minesweeper.entrypoint.router.handler.user.GetBoardsByUserIdHandler;
import org.hojeda.minesweeper.entrypoint.router.handler.user.GetUserByIdHandler;
import org.hojeda.minesweeper.entrypoint.router.handler.user.GetUserHandler;
import org.hojeda.minesweeper.entrypoint.router.handler.user.PostUserHandler;
import org.hojeda.minesweeper.configuration.mapper.JsonMapper;
import spark.RouteGroup;
import spark.Spark;

import javax.inject.Inject;

import static org.hojeda.minesweeper.entrypoint.router.route.Routes.*;

public class UserRouter implements RouteGroup {

    private final GetUserHandler getUserHandler;
    private final GetUserByIdHandler getUserByIdHandler;
    private final PostUserHandler postUserHandler;
    private final GetBoardsByUserIdHandler getBoardsByUserIdHandler;

    @Inject
    public UserRouter(
        GetUserHandler getUserHandler,
        GetUserByIdHandler getUserByIdHandler,
        PostUserHandler postUserHandler,
        GetBoardsByUserIdHandler getBoardsByUserIdHandler
    ) {
        this.getUserHandler = getUserHandler;
        this.getUserByIdHandler = getUserByIdHandler;
        this.postUserHandler = postUserHandler;
        this.getBoardsByUserIdHandler = getBoardsByUserIdHandler;
    }

    @Override
    public void addRoutes() {

        Spark.post(USER, postUserHandler, JsonMapper.get()::writeValueAsString);

        Spark.get(USER, getUserHandler, JsonMapper.get()::writeValueAsString);

        Spark.get(USER + USER_ID, getUserByIdHandler, JsonMapper.get()::writeValueAsString);

        Spark.get(USER + USER_ID + BOARD, getBoardsByUserIdHandler, JsonMapper.get()::writeValueAsString);

    }
}
