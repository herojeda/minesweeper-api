package org.hojeda.minesweeper.entrypoint.router.handler.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.net.HttpHeaders;
import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.usecase.board.CreateBoard;
import org.hojeda.minesweeper.entrypoint.router.dto.request.board.PostBoardRequest;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.PostBoardFieldResponse;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.PostBoardResponse;
import org.hojeda.minesweeper.util.entrypoint.ContentType;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class PostBoardHandler implements Route {

    private CreateBoard createBoard;

    @Inject
    public PostBoardHandler(CreateBoard createBoard) {
        this.createBoard = createBoard;
    }

    @Override
    public Object handle(Request request, Response response) throws JsonProcessingException {
        var postBoardRequest = JsonMapper.get().readValue(request.body(), PostBoardRequest.class);
        var board = createBoard.execute(
            BasicBoardData.newBuilder()
                .withBombs(postBoardRequest.getBombs())
                .withColumnSize(postBoardRequest.getColumnSize())
                .withRowSize(postBoardRequest.getRowSize())
                .build()
        );

        response.status(HttpStatus.CREATED_201);
        response.header(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.content());
        return PostBoardResponse.newBuilder()
            .withBombs(board.getBombs())
            .withColumnSize(board.getColumnSize())
            .withCreatedAt(board.getCreatedAt())
            .withStatus(board.getStatus().name().toLowerCase())
            .withId(board.getId())
            .withRowSize(board.getRowSize())
            .withFields(board.getFields().stream()
                .map(boardField -> PostBoardFieldResponse.newBuilder()
                    .withId(boardField.getId())
                    .withColumnNumber(boardField.getColumnNumber())
                    .withRowNumber(boardField.getRowNumber())
                    .withHidden(boardField.getHidden())
                    .withValue(boardField.getValue())
                    .build()
                ).collect(Collectors.toSet())
            )
            .build();
    }

}
