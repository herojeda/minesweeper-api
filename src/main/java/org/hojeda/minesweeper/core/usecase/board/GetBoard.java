package org.hojeda.minesweeper.core.usecase.board;

import org.eclipse.jetty.http.HttpStatus;
import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.repository.board.GetBoardByIdRepository;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldsByBoardIdRepository;
import org.hojeda.minesweeper.entrypoint.router.error.ErrorRequestException;

import javax.inject.Inject;

public class GetBoard {

    private final GetBoardByIdRepository getBoardByIdRepository;
    private final GetFieldsByBoardIdRepository getFieldsByBoardIdRepository;

    @Inject
    public GetBoard(GetBoardByIdRepository getBoardByIdRepository, GetFieldsByBoardIdRepository getFieldsByBoardIdRepository) {
        this.getBoardByIdRepository = getBoardByIdRepository;
        this.getFieldsByBoardIdRepository = getFieldsByBoardIdRepository;
    }

    public Board execute(Long boardId) {
        var board = getBoardByIdRepository.execute(boardId);
        if (board == null) throw new ErrorRequestException("Can't find board with id: " + boardId, HttpStatus.NOT_FOUND_404);
        return Board.newBuilder(board)
            .withFields(getFieldsByBoardIdRepository.execute(boardId))
            .build();
    }
}
