package org.hojeda.minesweeper.core.entity.board.field;

import java.util.Objects;

public class MineBoardField {

    public static final Integer MINE_VALUE = 9;

    private Integer rowNumber;
    private Integer columnNumber;

    private MineBoardField(Builder builder) {
        setRowNumber(builder.rowNumber);
        setColumnNumber(builder.columnNumber);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(MineBoardField copy) {
        Builder builder = new Builder();
        builder.rowNumber = copy.getRowNumber();
        builder.columnNumber = copy.getColumnNumber();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MineBoardField)) return false;
        MineBoardField that = (MineBoardField) o;
        return rowNumber.equals(that.rowNumber) &&
            columnNumber.equals(that.columnNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowNumber, columnNumber);
    }

    @Override
    public String toString() {
        return "BombBoardField{" +
            "rowNumber=" + rowNumber +
            ", columnNumber=" + columnNumber +
            '}';
    }


    public static final class Builder {
        private Integer rowNumber;
        private Integer columnNumber;

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

        public MineBoardField build() {
            return new MineBoardField(this);
        }
    }
}
