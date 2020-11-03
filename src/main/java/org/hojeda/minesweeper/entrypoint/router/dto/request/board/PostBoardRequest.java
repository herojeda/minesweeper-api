package org.hojeda.minesweeper.entrypoint.router.dto.request.board;

import java.util.Objects;

public class PostBoardRequest {

    private Integer rowSize;
    private Integer columnSize;
    private Integer mines;

    public PostBoardRequest() {
    }

    public Integer getRowSize() {
        return rowSize;
    }

    public void setRowSize(Integer rowSize) {
        this.rowSize = rowSize;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public Integer getMines() {
        return mines;
    }

    public void setMines(Integer mines) {
        this.mines = mines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostBoardRequest)) return false;
        PostBoardRequest that = (PostBoardRequest) o;
        return rowSize.equals(that.rowSize) &&
            columnSize.equals(that.columnSize) &&
            mines.equals(that.mines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowSize, columnSize, mines);
    }

    @Override
    public String toString() {
        return "PostBoardRequest{" +
            "rowSize=" + rowSize +
            ", columnSize=" + columnSize +
            ", mines=" + mines +
            '}';
    }
}
