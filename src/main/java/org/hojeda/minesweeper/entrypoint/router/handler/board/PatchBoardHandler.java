package org.hojeda.minesweeper.entrypoint.router.handler.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.net.HttpHeaders;
import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.core.entity.board.BoardMovement;
import org.hojeda.minesweeper.core.entity.constants.board.MovementType;
import org.hojeda.minesweeper.core.usecase.board.movement.MakeMovement;
import org.hojeda.minesweeper.entrypoint.router.dto.request.board.PatchBoardRequest;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardFieldResponse;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardResponse;
import org.hojeda.minesweeper.util.entrypoint.ContentType;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class PatchBoardHandler implements Route {

    private final MakeMovement makeMovement;

    @Inject
    public PatchBoardHandler(MakeMovement makeMovement) {
        this.makeMovement = makeMovement;
    }

    @Override
    public Object handle(Request request, Response response) throws JsonProcessingException {
        var patchBoardRequest = JsonMapper.get().readValue(request.body(), PatchBoardRequest.class);
        var boardId = request.params(":boardId");
        var board = makeMovement.execute(
            BoardMovement.newBuilder()
                .withBoardId(Long.valueOf(boardId))
                .withColumn(patchBoardRequest.getColumn())
                .withRow(patchBoardRequest.getRow())
                .withMovementType(MovementType.getByName(patchBoardRequest.getMovementType()))
                .build()
        );

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
