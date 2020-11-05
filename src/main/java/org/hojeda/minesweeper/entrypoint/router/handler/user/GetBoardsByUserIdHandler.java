package org.hojeda.minesweeper.entrypoint.router.handler.user;

import com.google.common.net.HttpHeaders;
import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.core.usecase.user.GetBoardsByUserId;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardFieldResponse;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardResponse;
import org.hojeda.minesweeper.util.entrypoint.ContentType;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class GetBoardsByUserIdHandler implements Route {

    private final GetBoardsByUserId getBoardsByUserId;

    @Inject
    public GetBoardsByUserIdHandler(GetBoardsByUserId getBoardsByUserId) {
        this.getBoardsByUserId = getBoardsByUserId;
    }

    @Override
    public Object handle(Request request, Response response) {
        var userId = Long.valueOf(request.params(":userId"));
        var boards = getBoardsByUserId.execute(userId);

        response.status(HttpStatus.OK_200);
        response.header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.content());

        return boards.stream()
            .map(board -> BoardResponse.newBuilder()
                .withUserId(board.getUserId())
                .withMines(board.getMines())
                .withColumnSize(board.getColumnSize())
                .withCreatedAt(board.getCreatedAt())
                .withStatus(board.getStatus().name().toLowerCase())
                .withId(board.getId())
                .withRowSize(board.getRowSize())
                .withStartedAt(board.getStartedAt())
                .withFinishedAt(board.getFinishedAt())
                .withFields(board.getFields().stream()
                    .map(boardField -> BoardFieldResponse.newBuilder()
                        .withId(boardField.getId())
                        .withColumn(boardField.getColumnNumber())
                        .withRow(boardField.getRowNumber())
                        .withStatus(boardField.getStatus().name().toLowerCase())
                        .withValue(boardField.getValue())
                        .build()
                    ).collect(Collectors.toSet())
                )
                .build()
            )
            .collect(Collectors.toList());
    }
}
