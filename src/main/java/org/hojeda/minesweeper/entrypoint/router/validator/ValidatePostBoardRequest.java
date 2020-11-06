package org.hojeda.minesweeper.entrypoint.router.validator;

import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.entrypoint.router.dto.request.board.PostBoardRequest;
import org.hojeda.minesweeper.entrypoint.router.error.ErrorRequestException;

public class ValidatePostBoardRequest {

    private static final Integer ROW_MIN_LIMIT = 10;
    private static final Integer ROW_MAX_LIMIT = 500;
    private static final Integer COLUMN_MIN_LIMIT = 10;
    private static final Integer COLUMN_MAX_LIMIT = 500;

    public void execute(PostBoardRequest request) {



        if (ROW_MIN_LIMIT > request.getRowSize() || ROW_MAX_LIMIT < request.getRowSize())
            throw new ErrorRequestException(
                "ROW_SIZE_LIMIT_EXCEEDED - " +
                    "The limit of rows is MIN: " + ROW_MIN_LIMIT + " - MAX: " + ROW_MAX_LIMIT,
                HttpStatus.BAD_REQUEST_400
            );

        if (COLUMN_MIN_LIMIT > request.getColumnSize() || COLUMN_MAX_LIMIT < request.getColumnSize())
            throw new ErrorRequestException(
                "COLUMN_SIZE_LIMIT_EXCEEDED - " +
                    "The limit of columns is MIN: " + COLUMN_MIN_LIMIT + " - MAX: " + COLUMN_MAX_LIMIT,
                HttpStatus.BAD_REQUEST_400
            );

        if (request.getMines() > (request.getRowSize() * request.getColumnSize()))
            throw new ErrorRequestException(
                "MINES_EXCEEDED_BOARD_SIZE - " +
                    "Can't set more mines than total size of the board. " +
                    " MINES: " + request.getMines() +
                    " BOARD_TOTAL_SIZE: " + (request.getRowSize() * request.getColumnSize()),
                HttpStatus.BAD_REQUEST_400
            );
    }
}
