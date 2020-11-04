package org.hojeda.minesweeper.core.entity.board;

import org.hojeda.minesweeper.core.entity.constants.board.MovementType;

import java.util.Objects;

public class BoardMovement {

    private Long boardId;
    private Integer row;
    private Integer column;
    private MovementType movementType;

    private BoardMovement(Builder builder) {
        setBoardId(builder.boardId);
        setRow(builder.row);
        setColumn(builder.column);
        setMovementType(builder.movementType);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BoardMovement copy) {
        Builder builder = new Builder();
        builder.boardId = copy.getBoardId();
        builder.row = copy.getRow();
        builder.column = copy.getColumn();
        builder.movementType = copy.getMovementType();
        return builder;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardMovement)) return false;
        BoardMovement that = (BoardMovement) o;
        return Objects.equals(boardId, that.boardId) &&
            Objects.equals(row, that.row) &&
            Objects.equals(column, that.column) &&
            movementType == that.movementType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, row, column);
    }

    @Override
    public String toString() {
        return "BoardMovement{" +
            "boardId=" + boardId +
            ", row=" + row +
            ", column=" + column +
            ", movementType=" + movementType +
            '}';
    }

    public static final class Builder {
        private Long boardId;
        private Integer row;
        private Integer column;
        private MovementType movementType;

        private Builder() {
        }

        public Builder withBoardId(Long val) {
            boardId = val;
            return this;
        }

        public Builder withRow(Integer val) {
            row = val;
            return this;
        }

        public Builder withColumn(Integer val) {
            column = val;
            return this;
        }

        public Builder withMovementType(MovementType val) {
            movementType = val;
            return this;
        }

        public BoardMovement build() {
            return new BoardMovement(this);
        }
    }
}
