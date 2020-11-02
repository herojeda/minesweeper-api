package org.hojeda.minesweeper.entrypoint.router.dto.request.board;

import java.util.Objects;

public class PostBoardRequest {

    private Integer rowSize;
    private Integer columnSize;
    private Integer bombs;

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

    public Integer getBombs() {
        return bombs;
    }

    public void setBombs(Integer bombs) {
        this.bombs = bombs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostBoardRequest)) return false;
        PostBoardRequest that = (PostBoardRequest) o;
        return rowSize.equals(that.rowSize) &&
            columnSize.equals(that.columnSize) &&
            bombs.equals(that.bombs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowSize, columnSize, bombs);
    }

    @Override
    public String toString() {
        return "PostBoardRequest{" +
            "rowSize=" + rowSize +
            ", columnSize=" + columnSize +
            ", bombs=" + bombs +
            '}';
    }
}
