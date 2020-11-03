package org.hojeda.minesweeper.core.entity.board;

import java.util.Objects;

public class BasicBoardData {

    private Integer rowSize;
    private Integer columnSize;
    private Integer mines;

    private BasicBoardData(Builder builder) {
        setRowSize(builder.rowSize);
        setColumnSize(builder.columnSize);
        setMines(builder.mines);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BasicBoardData copy) {
        Builder builder = new Builder();
        builder.rowSize = copy.getRowSize();
        builder.columnSize = copy.getColumnSize();
        builder.mines = copy.getMines();
        return builder;
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
        if (!(o instanceof BasicBoardData)) return false;
        BasicBoardData that = (BasicBoardData) o;
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
        return "BasicBoardData{" +
            "rowSize=" + rowSize +
            ", columnSize=" + columnSize +
            ", mines=" + mines +
            '}';
    }


    public static final class Builder {
        private Integer rowSize;
        private Integer columnSize;
        private Integer mines;

        private Builder() {
        }

        public Builder withRowSize(Integer val) {
            rowSize = val;
            return this;
        }

        public Builder withColumnSize(Integer val) {
            columnSize = val;
            return this;
        }

        public Builder withMines(Integer val) {
            mines = val;
            return this;
        }

        public BasicBoardData build() {
            return new BasicBoardData(this);
        }
    }
}
