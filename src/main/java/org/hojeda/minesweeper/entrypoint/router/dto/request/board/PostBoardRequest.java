package org.hojeda.minesweeper.entrypoint.router.dto.request.board;

import java.util.Objects;

public class PostBoardRequest {

    private Integer rowSize;
    private Integer columnSize;
    private Integer mines;
    private Long userId;

    public PostBoardRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return Objects.equals(rowSize, that.rowSize) &&
            Objects.equals(columnSize, that.columnSize) &&
            Objects.equals(mines, that.mines) &&
            Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowSize, columnSize, mines, userId);
    }

    @Override
    public String toString() {
        return "PostBoardRequest{" +
            "rowSize=" + rowSize +
            ", columnSize=" + columnSize +
            ", mines=" + mines +
            ", userId=" + userId +
            '}';
    }
}

