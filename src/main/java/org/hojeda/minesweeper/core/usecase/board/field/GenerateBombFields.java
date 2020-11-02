package org.hojeda.minesweeper.core.usecase.board.field;

import org.hojeda.minesweeper.core.entity.board.BasicBoardData;
import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class GenerateBombFields {

    private ThreadLocal<Random> randomThreadLocal = new ThreadLocal<>();

    public Set<MineBoardField> execute(BasicBoardData basicBoardData) {
        var bombs = new HashSet<MineBoardField>();
        IntStream.range(0, basicBoardData.getBombs()).forEach((num) -> generateBomb(basicBoardData, bombs));
        return bombs;
    }

    private Set<MineBoardField> generateBomb(BasicBoardData basicBoardData, Set<MineBoardField> bombs) {
        if (Objects.isNull(randomThreadLocal.get())) randomThreadLocal.set(new Random());
        var random = randomThreadLocal.get();
        var bomb = MineBoardField.newBuilder()
            .withRowNumber(random.nextInt(basicBoardData.getRowSize()))
            .withColumnNumber(random.nextInt(basicBoardData.getColumnSize()))
            .build();

        if (bombs.contains(bomb)) {
            this.generateBomb(basicBoardData, bombs);
        } else {
            bombs.add(bomb);
        }

        return bombs;
    }
}
