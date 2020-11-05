package org.hojeda.minesweeper.core.usecase.user;

import org.hojeda.minesweeper.core.entity.board.Board;
import org.hojeda.minesweeper.core.repository.board.GetBoardsByUserIdRepository;
import org.hojeda.minesweeper.core.repository.board.fields.GetFieldsByBoardIdRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class GetBoardsByUserId {

    private final GetBoardsByUserIdRepository getBoardsByUserIdRepository;
    private final GetFieldsByBoardIdRepository getFieldsByBoardIdRepository;

    @Inject
    public GetBoardsByUserId(
        GetBoardsByUserIdRepository getBoardsByUserIdRepository,
        GetFieldsByBoardIdRepository getFieldsByBoardIdRepository
    ) {
        this.getBoardsByUserIdRepository = getBoardsByUserIdRepository;
        this.getFieldsByBoardIdRepository = getFieldsByBoardIdRepository;
    }

    public List<Board> execute(Long userId) {
        return getBoardsByUserIdRepository.execute(userId).stream()
            .map(board -> Board.newBuilder(board)
                .withFields(getFieldsByBoardIdRepository.execute(board.getId()))
                .build()
            )
            .collect(Collectors.toList());
    }
}
