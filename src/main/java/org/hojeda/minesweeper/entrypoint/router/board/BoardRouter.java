package org.hojeda.minesweeper.entrypoint.router.board;

import org.hojeda.minesweeper.entrypoint.router.Routes;
import org.hojeda.minesweeper.entrypoint.router.handler.board.PostBoardHandler;
import org.hojeda.minesweeper.util.entrypoint.ContentType;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import spark.RouteGroup;
import spark.Spark;

import javax.inject.Inject;

public class BoardRouter implements RouteGroup {

    private PostBoardHandler postBoardHandler;

    @Inject
    public BoardRouter(PostBoardHandler postBoardHandler) {
        this.postBoardHandler = postBoardHandler;
    }

    @Override
    public void addRoutes() {
        Spark.post(Routes.BOARD, ContentType.APPLICATION_JSON.content(), postBoardHandler, JsonMapper.get()::writeValueAsString);
    }
}
