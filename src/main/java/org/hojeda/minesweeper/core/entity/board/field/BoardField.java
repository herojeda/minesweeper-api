package org.hojeda.minesweeper.core.entity.board.field;

import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;

import java.util.Objects;

public class BoardField {

    private Long id;
    private Long boardId;
    private Integer rowNumber;
    private Integer columnNumber;
    private Integer value;
    private BoardFieldStatus status;

    private BoardField(Builder builder) {
        setId(builder.id);
        setBoardId(builder.boardId);
        setRowNumber(builder.rowNumber);
        setColumnNumber(builder.columnNumber);
        setValue(builder.value);
        setStatus(builder.status);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BoardField copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.boardId = copy.getBoardId();
        builder.rowNumber = copy.getRowNumber();
        builder.columnNumber = copy.getColumnNumber();
        builder.value = copy.getValue();
        builder.status = copy.getStatus();
        return builder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BoardFieldStatus getStatus() {
        return status;
    }

    public void setStatus(BoardFieldStatus status) {
        this.status = status;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardField)) return false;
        BoardField that = (BoardField) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(rowNumber, that.rowNumber) &&
            Objects.equals(columnNumber, that.columnNumber) &&
            Objects.equals(value, that.value) &&
            status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BoardField{" +
            "id=" + id +
            ", boardId=" + boardId +
            ", rowNumber=" + rowNumber +
            ", columnNumber=" + columnNumber +
            ", value=" + value +
            ", status=" + status +
            '}';
    }

    public static final class Builder {
        private Long id;
        private Long boardId;
        private Integer rowNumber;
        private Integer columnNumber;
        private Integer value;
        private BoardFieldStatus status;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withBoardId(Long val) {
            boardId = val;
            return this;
        }

        public Builder withRowNumber(Integer val) {
            rowNumber = val;
            return this;
        }

        public Builder withColumnNumber(Integer val) {
            columnNumber = val;
            return this;
        }

        public Builder withValue(Integer val) {
            value = val;
            return this;
        }

        public Builder withStatus(BoardFieldStatus val) {
            status = val;
            return this;
        }

        public BoardField build() {
            return new BoardField(this);
        }
    }
}
