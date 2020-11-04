package org.hojeda.minesweeper.entrypoint.router.board;

import org.hojeda.minesweeper.entrypoint.router.Routes;
import org.hojeda.minesweeper.entrypoint.router.handler.board.GetBoardHandler;
import org.hojeda.minesweeper.entrypoint.router.handler.board.PatchBoardHandler;
import org.hojeda.minesweeper.entrypoint.router.handler.board.PostBoardHandler;
import org.hojeda.minesweeper.util.entrypoint.ContentType;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import spark.RouteGroup;
import spark.Spark;

import javax.inject.Inject;

public class BoardRouter implements RouteGroup {

    private final PostBoardHandler postBoardHandler;
    private final PatchBoardHandler patchBoardHandler;
    private final GetBoardHandler getBoardHandler;

    @Inject
    public BoardRouter(PostBoardHandler postBoardHandler, PatchBoardHandler patchBoardHandler, GetBoardHandler getBoardHandler) {
        this.postBoardHandler = postBoardHandler;
        this.patchBoardHandler = patchBoardHandler;
        this.getBoardHandler = getBoardHandler;
    }

    @Override
    public void addRoutes() {
        Spark.post(
            Routes.BOARD, ContentType.APPLICATION_JSON.content(), postBoardHandler, JsonMapper.get()::writeValueAsString
        );

        Spark.patch(
            Routes.BOARD + Routes.BOARD_ID, ContentType.APPLICATION_JSON.content(), patchBoardHandler, JsonMapper.get()::writeValueAsString
        );

        Spark.get(
            Routes.BOARD + Routes.BOARD_ID, getBoardHandler, JsonMapper.get()::writeValueAsString
        );
    }
}
