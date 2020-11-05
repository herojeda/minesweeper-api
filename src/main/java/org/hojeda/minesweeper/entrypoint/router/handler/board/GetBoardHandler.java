package org.hojeda.minesweeper.entrypoint.router.handler.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.net.HttpHeaders;
import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.core.usecase.board.GetBoard;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardFieldResponse;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardResponse;
import org.hojeda.minesweeper.entrypoint.router.ContentType;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class GetBoardHandler implements Route {

    private final GetBoard getBoard;

    @Inject
    public GetBoardHandler(GetBoard getBoard) {
        this.getBoard = getBoard;
    }

    @Override
    public Object handle(Request request, Response response) throws JsonProcessingException {
        var boardId = request.params(":boardId");
        var board = getBoard.execute(Long.valueOf(boardId));

        response.status(HttpStatus.OK_200);
        response.header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.content());
        return BoardResponse.newBuilder()
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
            .build();
    }

}
