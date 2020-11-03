package org.hojeda.minesweeper.core.usecase.board.field;

import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class GenerateMineFields {

    private ThreadLocal<Random> randomThreadLocal = new ThreadLocal<>();

    public Set<MineBoardField> execute(BasicBoardData basicBoardData) {
        var mine = new HashSet<MineBoardField>();
        IntStream.range(0, basicBoardData.getMines()).forEach((num) -> generateMine(basicBoardData, mine));
        return mine;
    }

    private Set<MineBoardField> generateMine(BasicBoardData basicBoardData, Set<MineBoardField> mines) {
        if (Objects.isNull(randomThreadLocal.get())) randomThreadLocal.set(new Random());
        var random = randomThreadLocal.get();
        var mine = MineBoardField.newBuilder()
            .withRowNumber(random.nextInt(basicBoardData.getRowSize()))
            .withColumnNumber(random.nextInt(basicBoardData.getColumnSize()))
            .build();

        if (mines.contains(mine)) {
            this.generateMine(basicBoardData, mines);
        } else {
            mines.add(mine);
        }

        return mines;
    }
}
