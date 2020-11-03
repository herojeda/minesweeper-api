package org.hojeda.minesweeper.core.entity.board.field;

import org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus;

import java.util.Objects;

public class BoardFieldCreationData {

    private Integer rowNumber;
    private Integer columnNumber;
    private Integer value;
    private BoardFieldStatus status;

    private BoardFieldCreationData(Builder builder) {
        setRowNumber(builder.rowNumber);
        setColumnNumber(builder.columnNumber);
        setValue(builder.value);
        setStatus(builder.status);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BoardFieldCreationData copy) {
        Builder builder = new Builder();
        builder.rowNumber = copy.getRowNumber();
        builder.columnNumber = copy.getColumnNumber();
        builder.value = copy.getValue();
        builder.status = copy.getStatus();
        return builder;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardFieldCreationData)) return false;
        BoardFieldCreationData that = (BoardFieldCreationData) o;
        return rowNumber.equals(that.rowNumber) &&
            columnNumber.equals(that.columnNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowNumber, columnNumber);
    }

    @Override
    public String toString() {
        return "BoardFieldCreationData{" +
            "rowNumber=" + rowNumber +
            ", columnNumber=" + columnNumber +
            ", value=" + value +
            ", status=" + status +
            '}';
    }

    public static final class Builder {
        private Integer rowNumber;
        private Integer columnNumber;
        private Integer value;
        private BoardFieldStatus status;

        private Builder() {
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

        public BoardFieldCreationData build() {
            return new BoardFieldCreationData(this);
        }
    }
}
