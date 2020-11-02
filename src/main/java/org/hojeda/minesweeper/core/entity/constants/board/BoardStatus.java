package org.hojeda.minesweeper.core.entity.constants.board;

public enum BoardStatus {

    CREATED(1);

    private Integer id;

    BoardStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
